package ir.maktabsharif.exammanagement.service;

import ir.maktabsharif.exammanagement.model.dto.courseDTO.CourseRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.courseDTO.CourseResponseDTO;
import ir.maktabsharif.exammanagement.model.dto.studentDTO.StudentResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.Course;
import ir.maktabsharif.exammanagement.model.entity.Student;
import ir.maktabsharif.exammanagement.model.entity.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseService extends BaseService<Course, CourseRequestDTO> {
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

    Course addTeacherToCourse(String identifier, String nationalCode);

    List<CourseResponseDTO> getAllTeachers();

    List<CourseResponseDTO> getCoursesWithoutTeacher();

    Course addStudentToCourse(String identifier, String nationalCode);

    Course updateTeacherInCourse(String identifier, Teacher teacher);

    Course removeStudentFromCourse(UUID courseID, UUID studentID);

    void removeTeacherFromCourse(String identifier);

    Course getCourseWithParticipants(UUID courseID);

    List<Course> getCoursesByTeacherId(UUID teacherId);

    Optional<Course> findByIdentifier(String identifier);

    CourseResponseDTO convertToDTO(Course course);

}
