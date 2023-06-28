package amargolina.ru.hogwarts.school.service;

import amargolina.ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private long lastId = 0;

    public Student createStudent(Student student){
        student.setId(++lastId);
        students.put(lastId,student);
        return student;
    }

    public Student findStudent(Long id){
            return students.get(id);
    }

    public Student updateStudent(Student student){
        if(!students.containsKey(student.getId())) {
            createStudent(student);
        }
        students.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(Long id){
        return students.remove(id);
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public Collection<Student> getStudentsWithAge(int age) {
        Collection<Student> studentsList = getAllStudents();
        Collection<Student> studentWithAge = studentsList.stream()
                .filter(e->e.getAge()==age)
                .collect(Collectors.toList());
        return studentWithAge;
    }
}
