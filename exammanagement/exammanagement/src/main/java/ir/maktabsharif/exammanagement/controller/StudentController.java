package ir.maktabsharif.exammanagement.controller;

import ir.maktabsharif.exammanagement.model.dto.studentDTO.StudentRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.studentDTO.StudentResponseDTO;
import ir.maktabsharif.exammanagement.model.dto.teacherDTO.TeacherResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.Student;
import ir.maktabsharif.exammanagement.service.StudentService;
import ir.maktabsharif.exammanagement.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(/*@Valid*/ @RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.ok(studentService.register(studentRequestDTO));
    }

    @PutMapping("/{id}")
    private ResponseEntity<String> updateStudent(/*@Valid*/ @PathVariable UUID id, @RequestBody StudentRequestDTO studentRequestDTO) {
        boolean isUpdate = studentService.update(id, studentRequestDTO);
        if (isUpdate) {
            return ResponseEntity.ok("The student update was successful.");
        } else {
            return ResponseEntity.status(404).body("not found id");
        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentResponseDTO>> findAll() {
        List<StudentResponseDTO> teacherResponseDTOList = studentService.getAllStudent();
        return ResponseEntity.ok(teacherResponseDTOList);
    }

}
