package amargolina.ru.hogwarts.school.service;

import amargolina.ru.hogwarts.school.model.Faculty;
import amargolina.ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private Map<Long, Faculty> faculties = new HashMap<>();
    private long lastId = 0;

    public Faculty createFaculty(Faculty faculty){
        faculty.setId(++lastId);
        faculties.put(lastId,faculty);
        return faculty;
    }

    public Faculty findFaculty(Long id){
        return faculties.get(id);
    }

    public Faculty updateFaculty(Faculty faculty){
        if(!faculties.containsKey(faculty.getId())){
            createFaculty(faculty);
        }

        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(Long id){
        return faculties.remove(id);
    }

    public Collection<Faculty> getAllFaculties() {
        return faculties.values();
    }

    public Collection<Faculty> getFacultiesWithColor(String color) {
        Collection<Faculty> facultiesList =  getAllFaculties();
        Collection<Faculty> facultiesWithColor = facultiesList.stream()
                .filter(e->e.getColor()==color)
                .collect(Collectors.toList());
        return facultiesWithColor;
    }
}
