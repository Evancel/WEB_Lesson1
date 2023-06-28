package amargolina.ru.hogwarts.school.service;

import amargolina.ru.hogwarts.school.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService{
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long lastId = 0;

    @Override
    public Faculty createFaculty(Faculty faculty){
        faculty.setId(++lastId);
        faculties.put(lastId,faculty);
        return faculty;
    }
    @Override
    public Faculty findFaculty(long id){
        return faculties.get(id);
    }
    @Override
    public Faculty updateFaculty(Faculty faculty){
        if(!faculties.containsKey(faculty.getId())){
            createFaculty(faculty);
        }

        faculties.put(faculty.getId(), faculty);
        return faculty;
    }
    @Override
    public Faculty deleteFaculty(long id){
        return faculties.remove(id);
    }
    @Override
    public Collection<Faculty> getAllFaculties() {
        return faculties.values();
    }
    @Override
    public Collection<Faculty> getFacultiesWithColor(String color) {
        Collection<Faculty> facultiesList =  getAllFaculties();
       return facultiesList.stream()
                .filter(e->e.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
