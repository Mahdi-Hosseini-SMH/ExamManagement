package ir.maktabsharif.exammanagement.repository;

import ir.maktabsharif.exammanagement.model.entity.Course;
import ir.maktabsharif.exammanagement.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    List<Course> findByTeacherIsNull();

    Optional<Course> findByIdentifier(String identifier);

    List<Course> findByTeacherId(UUID teacherId); // یا

}
