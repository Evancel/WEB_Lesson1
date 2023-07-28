package amargolina.ru.hogwarts.school.repository;

import amargolina.ru.hogwarts.school.model.Avatar;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AvatarsRepository extends JpaRepository<Avatar, Long>
{
    Optional<Avatar>findByStudentId(Long studentId);

}
