package amargolina.ru.hogwarts.school.service.impl;

import amargolina.ru.hogwarts.school.dto.AvatarDto;
import amargolina.ru.hogwarts.school.mapper.AvatarMapper;
import amargolina.ru.hogwarts.school.model.Avatar;
import amargolina.ru.hogwarts.school.model.Student;
import amargolina.ru.hogwarts.school.repository.AvatarsRepository;
import amargolina.ru.hogwarts.school.service.AvatarService;
import amargolina.ru.hogwarts.school.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
//@Transactional
public class AvatarServiceImpl implements AvatarService {
    @Value("${path.to.avatars.folder}")
    private String pathToAvatar;
    private final AvatarsRepository avatarsRepository;
    private final StudentService studentService;
    private final AvatarMapper avatarMapper;

    AvatarServiceImpl (AvatarsRepository avatarRep, StudentService studentServ, AvatarMapper avatarMapper){
        this.avatarsRepository=avatarRep;
        this.studentService=studentServ;
        this.avatarMapper = avatarMapper;
    }

    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        Student student = studentService.findStudent(studentId);

        Path filePath = Path.of(pathToAvatar,studentId + "." +getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        //сохранение в файл на Локальный диск
        try(InputStream is = file.getInputStream();
            OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
            BufferedInputStream bis = new BufferedInputStream(is,1024);
            BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ){
            bis.transferTo(bos);
        }


        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());

        //создание уменьшенной копии
        avatar.setData(generateImagePreview(filePath));
    //    avatar.setData(file.getBytes());
        //сохранение в БД
        avatarsRepository.save(avatar);
    }

    private String getExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }

    public Avatar findAvatar(Long studentId){
        return avatarsRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    @Override
    public List<AvatarDto> findAllPreviews(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber-1,pageSize);
        return avatarsRepository.findAll(pageRequest)
                .stream()
                .map(avatar -> avatarMapper.mapToAvatarDto(avatar))
                .collect(Collectors.toList());
    }

    private byte[] generateImagePreview(Path filePath) throws IOException{
        try(InputStream is = Files.newInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }

}
