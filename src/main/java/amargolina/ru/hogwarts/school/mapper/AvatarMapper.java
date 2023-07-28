package amargolina.ru.hogwarts.school.mapper;

import amargolina.ru.hogwarts.school.dto.AvatarDto;
import amargolina.ru.hogwarts.school.model.Avatar;
import org.springframework.stereotype.Service;

@Service
public class AvatarMapper {
    //из entity в dto
    public AvatarDto mapToAvatarDto(Avatar avatar){
        AvatarDto avatarDto = new AvatarDto();
        avatarDto.setFılePath(avatar.getFilePath());
        avatarDto.setFileSize(avatar.getFileSize());
        avatarDto.setMediaType(avatar.getMediaType());
        avatarDto.setStudentId(avatar.getStudent().getId());

        return avatarDto;
    }

    //из dto в entity
}
