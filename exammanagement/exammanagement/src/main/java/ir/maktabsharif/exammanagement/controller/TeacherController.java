package ir.maktabsharif.exammanagement.controller;

import ir.maktabsharif.exammanagement.model.dto.teacherDTO.TeacherRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.teacherDTO.TeacherResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.Teacher;
import ir.maktabsharif.exammanagement.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
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
            return ResponseEntity.ok("آپدیت با موفقیت انجام شد");
        } else {
            return ResponseEntity.status(404).body("ایدی یافت نشد");
        }
    }

    @GetMapping("/teachers")
    public List<TeacherResponseDTO> findAllUser() {
        return teacherService.getAllTeachers();
    }

}
