package amargolina.ru.hogwarts.school.repository;

import amargolina.ru.hogwarts.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Student, Long> {
}
