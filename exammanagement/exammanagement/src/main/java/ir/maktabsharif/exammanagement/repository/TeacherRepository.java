package ir.maktabsharif.exammanagement.repository;

import ir.maktabsharif.exammanagement.model.entity.Student;
import ir.maktabsharif.exammanagement.model.entity.Teacher;
import ir.maktabsharif.exammanagement.model.entity.User;
import ir.maktabsharif.exammanagement.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    List<Teacher> getAllByStatus(Status status);
    Optional<Teacher> findByNationalCode(String nationalCode);

}
