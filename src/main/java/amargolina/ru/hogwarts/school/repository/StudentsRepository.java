package amargolina.ru.hogwarts.school.repository;

import amargolina.ru.hogwarts.school.model.Faculty;
import amargolina.ru.hogwarts.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentsRepository extends JpaRepository<Student, Long>
{
    List<Student> findByAge(int age);
    List<Student> findByAgeBetween(int minAge,int maxAge);
    List<Student> findAllByFaculty_id(Long id);

    @Query(value = "SELECT COUNT(id) FROM student", nativeQuery = true)
    int findCountOfStudents();

    @Query(value="SELECT AVG(age) FROM student", nativeQuery = true)
    int findAverageAgeOfAllStudents();

    @Query(value="SELECT * FROM student ORDER BY id desc LIMIT 5",nativeQuery = true)
    List<Student> findLastFiveStudents();
}
