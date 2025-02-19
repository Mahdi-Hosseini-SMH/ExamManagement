package ir.maktabsharif.exammanagement.repository;

import ir.maktabsharif.exammanagement.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
}
