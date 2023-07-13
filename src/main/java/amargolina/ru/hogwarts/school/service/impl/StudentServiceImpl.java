package amargolina.ru.hogwarts.school.service.impl;

import amargolina.ru.hogwarts.school.model.Faculty;
import amargolina.ru.hogwarts.school.model.Student;
import amargolina.ru.hogwarts.school.repository.StudentsRepository;
import amargolina.ru.hogwarts.school.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
        return studentsRepository.findById(id).orElse(null);
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
        return studentsRepository.findByAge(age);
    }

    @Override
    public Collection<Student> getStudentsBetweenAge(int minAge, int maxAge) {
        return studentsRepository.findByAgeBetween(minAge,maxAge);
    }

    public Faculty getFacultyOfStudent(Long id) {
        return studentsRepository.findById(id).get().getFaculty();
    }

    @Override
    public int getCountOfAllStudents(){
        return studentsRepository.findCountOfStudents();
    }

    @Override
    public int getAverageAgeOfAllStudents() {
        return studentsRepository.findAverageAgeOfAllStudents();
    }

    @Override
    public Collection<Student> getLastFiveStudents() {
        return studentsRepository.findLastFiveStudents();
    }


}
