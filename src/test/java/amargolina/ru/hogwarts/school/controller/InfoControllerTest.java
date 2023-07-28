package amargolina.ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("info")
@RestController
public class InfoControllerTest {
    @GetMapping
    public ResponseEntity getInfoAboutUser(){
        return ResponseEntity.ok("User is a great person!");
    }
}
