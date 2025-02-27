package ir.maktabsharif.exammanagement.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import ir.maktabsharif.exammanagement.model.dto.teacherDTO.TeacherRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.teacherDTO.TeacherResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.Course;
import ir.maktabsharif.exammanagement.model.entity.Teacher;
import ir.maktabsharif.exammanagement.service.CourseService;
import ir.maktabsharif.exammanagement.service.TeacherService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.time.Duration;
import java.util.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;
    private final CourseService courseService;

    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @PostMapping("/register")
    public ResponseEntity<Teacher> registerTeacher(/*@Valid*/ @RequestBody TeacherRequestDTO teacherRequestDTO) {
        return ResponseEntity.ok(teacherService.register(teacherRequestDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherResponseDTO>> findAll() {
        List<TeacherResponseDTO> teacherResponseDTOList = teacherService.getAllTeachers();
        return ResponseEntity.ok(teacherResponseDTOList);
    }

    @PutMapping("/{id}")
    private ResponseEntity<String> updateTeacher(/*@Valid*/ @PathVariable UUID id, @RequestBody TeacherRequestDTO teacherRequestDTO) {
        boolean isUpdate = teacherService.update(id, teacherRequestDTO);
        if (isUpdate) {
            return ResponseEntity.ok("The update was successful.");
        } else {
            return ResponseEntity.status(404).body("ID not found");
        }
    }

    @GetMapping("/teachers")
    public List<TeacherResponseDTO> findAllUser() {
        return teacherService.getAllTeachers();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Optional<Teacher> teacher = teacherService.loginTeacher(password, username);
        if (teacher.isPresent()) {
            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            String jwt = Jwts.builder()
                    .setSubject(teacher.get().getId().toString())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                    .claim("teacherId", teacher.get().getId().toString())
                    .signWith(key)
                    .compact();

            return ResponseEntity.ok(jwt);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    @GetMapping("/courses/{teacherId}")
    public ResponseEntity<List<Course>> getCoursesByTeacher(@PathVariable UUID teacherId) {
        List<Course> courses = courseService.getCoursesByTeacherId(teacherId);
        return ResponseEntity.ok(courses);
    }
}
