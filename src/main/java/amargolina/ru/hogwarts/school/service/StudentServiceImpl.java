package amargolina.ru.hogwarts.school.service;

import amargolina.ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{
    private final Map<Long, Student> students = new HashMap<>();
    private long lastId = 0;
    @Override
    public Student createStudent(Student student){
        student.setId(++lastId);
        students.put(lastId,student);
        return student;
    }
    @Override
    public Student findStudent(long id){
            return students.get(id);
    }
    @Override
    public Student updateStudent(Student student){
        if(!students.containsKey(student.getId())) {
            createStudent(student);
        }
        students.put(student.getId(), student);
        return student;
    }
    @Override
    public Student deleteStudent(long id){
        return students.remove(id);
    }
    @Override
    public Collection<Student> getAllStudents() {
        return students.values();
    }
    @Override
    public Collection<Student> getStudentsWithAge(int age) {
        Collection<Student> studentsList = getAllStudents();
        return studentsList.stream()
                .filter(e->e.getAge()==age)
                .collect(Collectors.toList());
    }
}
