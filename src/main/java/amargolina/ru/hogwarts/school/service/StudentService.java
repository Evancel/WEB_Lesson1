package amargolina.ru.hogwarts.school.service;

import amargolina.ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {
    Student createStudent(Student student);
    Student findStudent(long id);
    Student updateStudent(Student student);
    Student deleteStudent(long id);
    Collection<Student> getAllStudents();
    Collection<Student> getStudentsWithAge(int age);
}
