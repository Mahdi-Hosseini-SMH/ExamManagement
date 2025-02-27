package ir.maktabsharif.exammanagement.service.impl;

import ir.maktabsharif.exammanagement.model.dto.examDTO.ExamRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.examDTO.ExamResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.Course;
import ir.maktabsharif.exammanagement.model.entity.Exam;
import ir.maktabsharif.exammanagement.repository.ExamRepository;
import ir.maktabsharif.exammanagement.service.CourseService;
import ir.maktabsharif.exammanagement.service.ExamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final CourseService courseService;

    public ExamServiceImpl(ExamRepository examRepository, CourseService courseService) {
        this.examRepository = examRepository;
        this.courseService = courseService;
    }

    @Override
    public Exam createExam(ExamRequestDTO examRequestDTO, String identifier) {
        Course course = courseService.findByIdentifier(identifier)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Exam exam = new Exam();
        exam.setTitle(examRequestDTO.getTitle());
        exam.setDescription(examRequestDTO.getDescription());
        exam.setDuration(examRequestDTO.getDuration());
        exam.setCourse(course);
        return examRepository.save(exam);

    }


    @Override
    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Boolean update(UUID uuid, ExamRequestDTO examRequestDTO) {
        Optional<Exam> optionalExam = examRepository.findById(uuid);
        if (optionalExam.isPresent()) {
            Exam exam = optionalExam.get();
            exam.setTitle(examRequestDTO.getTitle());
            exam.setDescription(examRequestDTO.getDescription());
            exam.setDuration(examRequestDTO.getDuration());
            examRepository.save(exam);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void delete(UUID uuid) {
        Exam exam = examRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("exam not found with id: " + uuid));
        examRepository.delete(exam);
    }

    @Override
    public Optional<Exam> findById(UUID uuid) {
        return examRepository.findById(uuid);
    }


    @Override
    public Exam register(ExamRequestDTO examRequestDTO) {
        return null;
    }

    @Override
    public List<Exam> findAllByCourseId(UUID id) {
       return examRepository.getAllByCourse_Id(id);
    }

    @Override
    public ExamResponseDTO convertToDTO(Exam exam) {
        return new  ExamResponseDTO(
                exam.getTitle(),
                exam.getDescription(),
                exam.getDuration()
        );
    }

    @Override
    public List<ExamResponseDTO> getAllExamByCourse_Id(UUID id) {
        List<Exam> exams = examRepository.getAllByCourse_Id(id);
        return exams.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
