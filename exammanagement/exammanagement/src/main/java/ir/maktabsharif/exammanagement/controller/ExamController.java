package ir.maktabsharif.exammanagement.controller;

import ir.maktabsharif.exammanagement.model.dto.examDTO.ExamRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.examDTO.ExamResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.Exam;
import ir.maktabsharif.exammanagement.service.ExamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }


    @PostMapping("/create-exam/{identifier}")
    public ResponseEntity<Exam> createExam(@PathVariable String identifier, @RequestBody @Valid ExamRequestDTO examRequestDTO) {
        Exam exam = examService.createExam(examRequestDTO, identifier);
        return ResponseEntity.status(HttpStatus.CREATED).body(exam);
    }

    @GetMapping("/all/{courseId}")
    public ResponseEntity<List<ExamResponseDTO>> getAllByCourseId(@PathVariable UUID courseId) {
        List<ExamResponseDTO> allByCourseId = examService.getAllExamByCourse_Id(courseId);
        return ResponseEntity.ok(allByCourseId);
    }

    @PutMapping("/update/{examId}")
    public ResponseEntity<String> updateExam(@PathVariable UUID examId, @RequestBody @Valid ExamRequestDTO examRequestDTO) {
        Boolean isUpdate = examService.update(examId, examRequestDTO);
        if (isUpdate) {
            return ResponseEntity.ok("The exam update was successful");
        } else {
            return ResponseEntity.status(404).body("not found id");
        }
    }

    @DeleteMapping("delete/{courseId}")
    public ResponseEntity<String> deleteExam(@PathVariable UUID courseId) {
        examService.delete(courseId);
        return ResponseEntity.ok("Deleted successfully.");
    }
}
