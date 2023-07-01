package amargolina.ru.hogwarts.school.controller;

import amargolina.ru.hogwarts.school.model.Faculty;
import amargolina.ru.hogwarts.school.service.impl.FacultyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyServiceImpl facultyService;
    public FacultyController(FacultyServiceImpl service){
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
    public ResponseEntity<Collection<Faculty>> getFacultiesWithColor(
            @RequestParam (required = false) String color,
            @RequestParam(required = false) String name){
        if(color!=null && !color.isBlank()){
            return ResponseEntity.ok(facultyService.getFacultiesByColor(color));
        }
        if((name!=null && !name.isBlank())){
            return ResponseEntity.ok(facultyService.getFacultiesByName(name));
        }
        return ResponseEntity.ok(facultyService.getAllFaculties());
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
    public ResponseEntity deleteFaculty(@PathVariable Long id){
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
}
