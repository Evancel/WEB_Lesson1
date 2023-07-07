package amargolina.ru.hogwarts.school.controller;

import amargolina.ru.hogwarts.school.model.Avatar;
import amargolina.ru.hogwarts.school.service.impl.AvatarServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/avatars")
public class AvatarController {
    private final AvatarServiceImpl avatarService;
    public AvatarController(AvatarServiceImpl avatarServ){
        this.avatarService=avatarServ;
    }

    @PostMapping(value="/{studentId}/avatar", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long studentId,
                                               @RequestParam MultipartFile avatar) throws IOException {
        if(avatar.getSize()>1024*3000){
            return ResponseEntity.badRequest().body("The image is too big!");
        }

        //add a verification about the extension of the file: .jpg, .png
    //    if(avatar.getContentType()=)
        avatarService.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{studentId}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatarPreview(@PathVariable Long studentId){
        Avatar avatar = avatarService.findAvatar(studentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping("/{studentId}/avatar")
    public void downloadAvatar(@PathVariable Long studentId, HttpServletResponse response) throws IOException{
        Avatar avatar = avatarService.findAvatar(studentId);

        Path path = Path.of(avatar.getFilePath());

        try(InputStream is = Files.newInputStream(path);
            OutputStream os = response.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            BufferedOutputStream bos = new BufferedOutputStream(os, 1024)){
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());

            bis.transferTo(bos);
        }
    }

}
