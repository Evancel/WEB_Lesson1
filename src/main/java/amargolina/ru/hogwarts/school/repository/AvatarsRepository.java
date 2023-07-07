package amargolina.ru.hogwarts.school.repository;

import amargolina.ru.hogwarts.school.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarsRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar>findByStudentId(Long studentId);

}
