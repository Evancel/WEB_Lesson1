package amargolina.ru.hogwarts.school;

import amargolina.ru.hogwarts.school.controller.AvatarController;
import amargolina.ru.hogwarts.school.controller.FacultyController;
import amargolina.ru.hogwarts.school.controller.StudentController;
import amargolina.ru.hogwarts.school.model.Faculty;
import amargolina.ru.hogwarts.school.repository.AvatarsRepository;
import amargolina.ru.hogwarts.school.repository.FacultiesRepository;
import amargolina.ru.hogwarts.school.repository.StudentsRepository;
import amargolina.ru.hogwarts.school.service.impl.AvatarServiceImpl;
import amargolina.ru.hogwarts.school.service.impl.FacultyServiceImpl;
import amargolina.ru.hogwarts.school.service.impl.StudentServiceImpl;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
public class SchoolApplicationWebMVCTest {
   @Autowired
    private MockMvc mockMvc;

   @MockBean
    private FacultiesRepository facultiesRepository;
   @MockBean
   private AvatarsRepository avatarsRepository;

   @MockBean
   private StudentsRepository studentsRepository;

   @SpyBean
    private FacultyServiceImpl facultyService;
   @SpyBean
   private AvatarServiceImpl avatarService;

   @SpyBean
   private StudentServiceImpl studentService;

   @InjectMocks
    private FacultyController facultyController;

   @InjectMocks
   private AvatarController avatarController;

   @InjectMocks
   private StudentController studentController;

//   @Test
//    public void saveFacultyTest() throws Exception{
//       Long facultyId=1L;
//       String name = "Finance";
//       String color = "blue";
//
//       JSONObject facultyObject = new JSONObject();
//       facultyObject.put("id",facultyId);
//       facultyObject.put("name",name);
//       facultyObject.put("color",color);
//
//       Faculty faculty = new Faculty(facultyId,name,color);
//
//       when(facultiesRepository.save(any(Faculty.class))).thenReturn(faculty);
//       when(facultiesRepository.findByColorIgnoreCase("blue")).thenReturn(List.of(faculty));
//
//       mockMvc.perform(MockMvcRequestBuilders
//                       .post("/faculties") //send
//                       .content(facultyObject.toString())
//                       .contentType(MediaType.APPLICATION_JSON)
//                       .accept(MediaType.APPLICATION_JSON))
//               .andExpect(status().isOk()) //receive
//               .andExpect(jsonPath("$.id").value(facultyId))
//               .andExpect(jsonPath("$.name").value(name))
//               .andExpect(jsonPath("$.color").value(color));
//   }
//
//   @Test
//   public void getFacultyTest() throws Exception{
//      Long facultyId=1L;
//      String name = "Finance";
//      String color = "blue";
//
//      Faculty faculty = new Faculty(facultyId,name,color);
//
//      when(facultiesRepository.findById(facultyId)).thenReturn(Optional.of(faculty));
//
//      mockMvc.perform(MockMvcRequestBuilders
//                      .get("/faculties") //send
//                      .content(facultyId.toString())
//                      .accept(MediaType.APPLICATION_JSON))
//              .andExpect(status().isOk()); //receive
//   }
//
//   @Test
//   public void changeFacultyTest() throws Exception{
//      Long facultyId=1L;
//      String name = "Finance Management";
//      String color = "violet";
//
//      JSONObject facultyObject = new JSONObject();
//      facultyObject.put("id",facultyId);
//      facultyObject.put("name",name);
//      facultyObject.put("color",color);
//
//      Faculty faculty = new Faculty(facultyId,name,color);
//
//      when(facultiesRepository.save(any(Faculty.class))).thenReturn(faculty);
//      when(facultiesRepository.findById(facultyId)).thenReturn(Optional.of(faculty));
//
//      mockMvc.perform(MockMvcRequestBuilders
//                      .put("/faculties") //send
//                      .content(facultyObject.toString())
//                      .contentType(MediaType.APPLICATION_JSON)
//                      .accept(MediaType.APPLICATION_JSON))
//              .andExpect(status().isOk()) //receive
//              .andExpect(jsonPath("$.id").value(facultyId))
//              .andExpect(jsonPath("$.name").value(name))
//              .andExpect(jsonPath("$.color").value(color));
//   }

//   @Test
//   public void deleteFacultyTest() throws Exception{
//      Long facultyId=1L;
//      String name = "Finance Management";
//      String color = "violet";
//
//     mockMvc.perform(MockMvcRequestBuilders
//                      .delete("/faculties") //send
//                      .content(facultyId.toString())
//                      .accept(MediaType.APPLICATION_JSON))
//              .andExpect(status().isOk()); //receive
//   }
}
