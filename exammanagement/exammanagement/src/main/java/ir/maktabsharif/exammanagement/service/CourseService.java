package ir.maktabsharif.exammanagement.service;

import ir.maktabsharif.exammanagement.model.dto.courseDTO.CourseRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.courseDTO.CourseResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.Course;
import ir.maktabsharif.exammanagement.model.entity.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseService extends BaseService<Course, CourseRequestDTO>{
    @Override
    Course register(CourseRequestDTO courseRequestDTO);

    @Override
    List<Course> findAll();

    @Override
    Boolean update(UUID uuid, CourseRequestDTO courseRequestDTO);

    @Override
    void delete(UUID uuid);

    @Override
    Optional<Course> findById(UUID uuid);

    public Course addTeacherToCourse(String identifier , UUID teacherId);

    public List<CourseResponseDTO> getAllTeachers();

    public List<CourseResponseDTO> getCoursesWithoutTeacher();

    public Course addStudentToCourse(UUID courseID, UUID studentID);

}
