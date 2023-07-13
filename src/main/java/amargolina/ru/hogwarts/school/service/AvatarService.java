package amargolina.ru.hogwarts.school.service;

import amargolina.ru.hogwarts.school.model.Avatar;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile avatar) throws IOException;
    Avatar findAvatar(Long studentId);
    List<Avatar> findAllAvatars(Integer pageNumber, Integer pageSize);
}
