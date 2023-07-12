package amargolina.ru.hogwarts.school;

import amargolina.ru.hogwarts.school.controller.StudentController;
import amargolina.ru.hogwarts.school.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationRestTemplatesTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testUser() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/info", String.class))
                .isEqualTo("User is a great person!");
    }

	@Test
	public void testGetStudentById() throws Exception {
		Long studentId=11L;
		Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students" + studentId, String.class))
				.isNotNull();
	}

    @Test
    public void testGetAllStudents() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students", String.class))
                .isNotNull();
    }

	@Test
	public void testGetStudentsWithAge() throws Exception{
		int age = 24;
		Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/age/" + age, String.class))
				.isNotNull();
	}


    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student(111, "Padma Patil", 26);
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students",
                        student,
                        String.class))
                .isNotNull();
    }

    @Test
    public void testChangeStudent() throws Exception {
        Student student = new Student(11, "Ronny Weasley", 27);
        ResponseEntity<Student> request = ResponseEntity.ok(student);
        Assertions.assertThat(this.restTemplate.exchange("http://localhost:" + port + "/students",
														HttpMethod.PUT,
														request,
														String.class))
                .isNotNull();
    }
}
