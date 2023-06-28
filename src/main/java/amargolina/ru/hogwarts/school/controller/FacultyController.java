package amargolina.ru.hogwarts.school.controller;

import amargolina.ru.hogwarts.school.model.Faculty;
import amargolina.ru.hogwarts.school.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;
    public FacultyController(FacultyService service){
        this.facultyService = service;
    }

    @PostMapping()
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty){
        if(faculty==null){
            return ResponseEntity.notFound().build();
        }
        Faculty addedFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(addedFaculty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id){
        Faculty faculty = facultyService.findFaculty(id);
        if(faculty==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping()
    public ResponseEntity<Collection<Faculty>> getAllFaculties(){
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @GetMapping("/{color}")
    public ResponseEntity<Collection<Faculty>> getFacultiesWithColor(@PathVariable String color){
        return ResponseEntity.ok(facultyService.getFacultiesWithColor(color));
    }

    @PutMapping()
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty){
        Faculty editedFaculty = facultyService.updateFaculty(faculty);
        if(editedFaculty==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editedFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id){
        Faculty deletedFaculty = facultyService.deleteFaculty(id);
        if(deletedFaculty==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedFaculty);
    }
}
