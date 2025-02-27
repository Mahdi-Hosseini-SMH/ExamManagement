package ir.maktabsharif.exammanagement.repository;

import ir.maktabsharif.exammanagement.model.entity.Course;
import ir.maktabsharif.exammanagement.model.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExamRepository extends JpaRepository<Exam , UUID> {

    List<Exam> getAllByCourse_Id(UUID courseId);
}
