package amargolina.ru.hogwarts.school.controller;

import amargolina.ru.hogwarts.school.model.Faculty;
import amargolina.ru.hogwarts.school.model.Student;
import amargolina.ru.hogwarts.school.service.impl.StudentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentServiceImpl studentService;
    public StudentController(StudentServiceImpl service){
        this.studentService  = service;
    }

    @PostMapping()
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        if(student==null){
            return ResponseEntity.notFound().build();
        }
        Student addedStudent = studentService.createStudent(student);
        return ResponseEntity.ok(addedStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        Student student = studentService.findStudent(id);
        if(student==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping()
    public ResponseEntity<Collection<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<Collection<Student>> getStudentsWithAge(@PathVariable int age){
        return ResponseEntity.ok(studentService.getStudentsWithAge(age));
    }

    @GetMapping("age_between")
    public ResponseEntity<Collection<Student>> getStudentsWithAgeBetween(@RequestParam int minAge,
                                                                         @RequestParam int maxAge){
        return ResponseEntity.ok(studentService.getStudentsBetweenAge(minAge, maxAge));
    }

    @GetMapping("/{studentsId}/faculty")
    public Faculty getFacultyOfStudent(@PathVariable Long studentsId){
        return studentService.getFacultyOfStudent(studentsId);
    }

    @GetMapping("count-of-all-students")
    public int getCountOfAllStudents(){
        return studentService.getCountOfAllStudents();
    }

    @GetMapping("avg_age-of-all-students")
    public int getAverageAgeOfAllStudents(){
        return studentService.getAverageAgeOfAllStudents();
    }

    @GetMapping("last-five-students")
    public Collection<Student> getLastFiveStudents(){
        return studentService.getLastFiveStudents();
    }

    @GetMapping("stream-names-starts-with-A")
    public Collection<String> getNamesStartingFromA(){
        return studentService.getNamesStartingFromA();
    }

    @GetMapping("stream-avg-age-of-students")
    public ResponseEntity<Double> getAvgAgeOfStudents(){
        return ResponseEntity.ok(studentService.getAverageAgeOfAllStudentsStream());
    }

    @GetMapping("threads-all_students")
    public ResponseEntity getAllStudentsWithThreads(){
        studentService.getAllStudentsWithThreads();
        return ResponseEntity.ok().build();
    }

    @GetMapping("synchronized_threads-all_students")
    public ResponseEntity getAllStudentsWithThreadsSynchronized(){
        studentService.getAllStudentsWithThreadsSynchronized();
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<Student> editStudent(@RequestBody Student student){
        Student editedStudent = studentService.updateStudent(student);
        if(student==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/parallelt-stream")
    public ResponseEntity<Integer> getNumber(){
        return ResponseEntity.ok(studentService.getIntegerNumber());
    }

}
