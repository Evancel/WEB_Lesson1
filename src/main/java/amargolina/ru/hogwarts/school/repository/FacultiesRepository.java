package amargolina.ru.hogwarts.school.repository;

import amargolina.ru.hogwarts.school.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultiesRepository extends JpaRepository<Faculty,Long> {
}
