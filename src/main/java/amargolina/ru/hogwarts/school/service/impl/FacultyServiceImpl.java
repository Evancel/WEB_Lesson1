package amargolina.ru.hogwarts.school.service.impl;

import amargolina.ru.hogwarts.school.model.Faculty;
import amargolina.ru.hogwarts.school.model.Student;
import amargolina.ru.hogwarts.school.repository.FacultiesRepository;
import amargolina.ru.hogwarts.school.service.FacultyService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultiesRepository facultiesRepository;

    public FacultyServiceImpl(FacultiesRepository facultiesRepository) {
        this.facultiesRepository = facultiesRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty){
        return facultiesRepository.save(faculty);
    }
    @Override
    public Faculty findFaculty(long id){
        return facultiesRepository.findById(id).orElse(null);
    }
    @Override
    public Faculty updateFaculty(Faculty faculty){
        return facultiesRepository.save(faculty);
    }
    @Override
    public void deleteFaculty(long id){
        facultiesRepository.deleteById(id);
    }
    @Override
    public Collection<Faculty> getAllFaculties() {
        return facultiesRepository.findAll();
    }
    @Override
    public Collection<Faculty> getFacultiesByColor(String color) {
        return facultiesRepository.findByColorIgnoreCase(color);
    }
    @Override
    public Collection<Faculty> getFacultiesByName(String name) {
        return facultiesRepository.findByNameContainingIgnoreCase(name);
    }

    public Collection<Student> getStudentsOfTheFaculty(Long id) {
        return facultiesRepository.findById(id).get().getStudentCollection();
    }
}
