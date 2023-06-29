package amargolina.ru.hogwarts.school.service.impl;

import amargolina.ru.hogwarts.school.model.Student;
import amargolina.ru.hogwarts.school.repository.StudentsRepository;
import amargolina.ru.hogwarts.school.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentsRepository studentsRepository;

    public StudentServiceImpl(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentsRepository.save(student);
    }

    @Override
    public Student findStudent(long id) {
        return studentsRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentsRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        studentsRepository.deleteById(id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return studentsRepository.findAll();
    }

    @Override
    public Collection<Student> getStudentsWithAge(int age) {
        Collection<Student> studentsList = getAllStudents();
        return studentsList.stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
    }
}
