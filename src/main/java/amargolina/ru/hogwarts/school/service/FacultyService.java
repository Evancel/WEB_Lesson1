package amargolina.ru.hogwarts.school.service;

import amargolina.ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {
    Faculty createFaculty(Faculty faculty);
    Faculty findFaculty(long id);
    Faculty updateFaculty(Faculty faculty);
    void deleteFaculty(long id);
    Collection<Faculty> getAllFaculties();
    Collection<Faculty> getFacultiesWithColor(String color);
}
