package amargolina.ru.hogwarts.school.service.impl;

import amargolina.ru.hogwarts.school.model.Faculty;
import amargolina.ru.hogwarts.school.model.Student;
import amargolina.ru.hogwarts.school.repository.StudentsRepository;
import amargolina.ru.hogwarts.school.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentsRepository studentsRepository;

    public StudentServiceImpl(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentsRepository.save(student);
    }

    @Override
    public Student findStudent(long id) {
        logger.info("Finding a student with id {}",id);
        return studentsRepository.findById(id).orElse(null);
    }

    @Override
    public Student updateStudent(Student student) {
        logger.warn("Was invoked method for update student");
        return studentsRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Deleting a student with id {}",id);
        studentsRepository.deleteById(id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method for getting all students");
        return studentsRepository.findAll();
    }

    @Override
    public Collection<Student> getStudentsWithAge(int age) {
        logger.info("Was invoked method for getting all students with age = {}",age);
        return studentsRepository.findByAge(age);
    }

    @Override
    public Collection<Student> getStudentsBetweenAge(int minAge, int maxAge) {
        logger.info("Was invoked method for getting all students with age between {} and {}",minAge,maxAge);
        return studentsRepository.findByAgeBetween(minAge,maxAge);
    }

    public Faculty getFacultyOfStudent(Long id) {
        logger.info("Finding the faculty of the student with id {}",id);
        return studentsRepository.findById(id).orElseGet(null).getFaculty();
    }

    @Override
    public int getCountOfAllStudents(){
        logger.info("Getting the count of all students");
        return studentsRepository.findCountOfStudents();
    }

    @Override
    public int getAverageAgeOfAllStudents() {
        logger.debug("Getting average age of all students");
        return studentsRepository.findAverageAgeOfAllStudents();
    }

    @Override
    public Collection<Student> getLastFiveStudents() {
        logger.info("Getting last five students");
        return studentsRepository.findLastFiveStudents();
    }


    public Collection<String> getNamesStartingFromA() {
        Collection<String> namesStartingFromA = studentsRepository.findAll()
                .stream()
                .map(s->s.getName().toUpperCase())
                .filter(s->s.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
        return namesStartingFromA;
    }

    public Double getAverageAgeOfAllStudentsStream() {
        Double averageAge = studentsRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElseThrow(NullPointerException::new);

        return averageAge;
    }

    public Integer getIntegerNumber() {

        //AS_IS
//        Integer sum = Stream
//                .iterate(1, a -> a +1)
//                .parallel()
//                .limit(1_000_000)
//                .reduce(0, (a, b) -> a + b);

        //TO_BE
        Integer sum=0;
        for(int i=1;i<=1_000_000;i++){
            sum+=i;
        }
        System.out.println(sum);
        return sum;
    }
}
