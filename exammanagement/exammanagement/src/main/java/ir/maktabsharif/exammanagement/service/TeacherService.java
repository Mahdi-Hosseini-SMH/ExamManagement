package ir.maktabsharif.exammanagement.service;

import ir.maktabsharif.exammanagement.model.dto.teacherDTO.TeacherRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.teacherDTO.TeacherResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeacherService extends BaseService<Teacher, TeacherRequestDTO> {
    @Override
    Teacher register(TeacherRequestDTO teacherRequestDTO);

    @Override
    Boolean update(UUID uuid, TeacherRequestDTO teacherRequestDTO);

    @Override
    void delete(UUID uuid);

    @Override
    Optional<Teacher> findById(UUID uuid);

    List<TeacherResponseDTO> getAllTeachers();

    public TeacherResponseDTO convertToDTO(Teacher teacher);
}
