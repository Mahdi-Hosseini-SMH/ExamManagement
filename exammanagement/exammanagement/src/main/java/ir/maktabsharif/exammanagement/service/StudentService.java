package ir.maktabsharif.exammanagement.service;

import ir.maktabsharif.exammanagement.model.dto.studentDTO.StudentRequestDTO;
import ir.maktabsharif.exammanagement.model.entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentService extends BaseService<Student , StudentRequestDTO>{
    @Override
    Student register(StudentRequestDTO studentRequestDTO);

    @Override
    List<Student> findAll();

    @Override
    Boolean update(UUID uuid, StudentRequestDTO studentRequestDTO);

    @Override
    void delete(UUID uuid);

    @Override
    Optional<Student> findById(UUID uuid);
}
