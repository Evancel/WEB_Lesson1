package amargolina.ru.hogwarts.school.controller;

import amargolina.ru.hogwarts.school.model.Student;
import amargolina.ru.hogwarts.school.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService service){
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

    @GetMapping("age/{age}")
    public ResponseEntity<Collection<Student>> getStudentsWithAge(@PathVariable int age){
        return ResponseEntity.ok(studentService.getStudentsWithAge(age));
    }

    @PutMapping()
    public ResponseEntity<Student> editStudent(@RequestBody Student student){
        Student editedStudent = studentService.updateStudent(student);
        if(student==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        Student deletedStudent = studentService.deleteStudent(id);
        if(deletedStudent==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedStudent);
    }

}
