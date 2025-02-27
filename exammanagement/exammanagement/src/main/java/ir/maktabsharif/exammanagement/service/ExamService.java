package ir.maktabsharif.exammanagement.service;

import ir.maktabsharif.exammanagement.model.dto.examDTO.ExamRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.examDTO.ExamResponseDTO;
import ir.maktabsharif.exammanagement.model.dto.studentDTO.StudentResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.Course;
import ir.maktabsharif.exammanagement.model.entity.Exam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExamService extends BaseService<Exam , ExamRequestDTO> {

    Exam createExam(ExamRequestDTO examRequestDTO , String identifier);

    @Override
    List<Exam> findAll();

    @Override
    Boolean update(UUID uuid, ExamRequestDTO examRequestDTO);

    @Override
    void delete(UUID uuid);

    @Override
    Optional<Exam> findById(UUID uuid);

    List<Exam> findAllByCourseId(UUID id);

    ExamResponseDTO convertToDTO(Exam exam);

    List<ExamResponseDTO> getAllExamByCourse_Id(UUID id);
}
