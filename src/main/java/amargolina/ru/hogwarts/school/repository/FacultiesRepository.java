package amargolina.ru.hogwarts.school.repository;

import amargolina.ru.hogwarts.school.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultiesRepository extends JpaRepository<Faculty,Long> {
    List<Faculty> findByColorIgnoreCase(String color);
    List<Faculty> findByNameContainingIgnoreCase(String name);
}
