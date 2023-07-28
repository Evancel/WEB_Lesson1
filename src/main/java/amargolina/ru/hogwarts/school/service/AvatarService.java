package amargolina.ru.hogwarts.school.service;

import amargolina.ru.hogwarts.school.dto.AvatarDto;
import amargolina.ru.hogwarts.school.model.Avatar;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile avatar) throws IOException;
    Avatar findAvatar(Long studentId);
    List<AvatarDto> findAllPreviews(Integer pageNumber,Integer pageSize);
}
