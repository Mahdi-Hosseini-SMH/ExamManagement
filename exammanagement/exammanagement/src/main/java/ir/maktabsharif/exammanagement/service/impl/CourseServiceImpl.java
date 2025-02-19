package ir.maktabsharif.exammanagement.service.impl;

import ir.maktabsharif.exammanagement.model.dto.courseDTO.CourseRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.courseDTO.CourseResponseDTO;
import ir.maktabsharif.exammanagement.model.dto.teacherDTO.TeacherRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.teacherDTO.TeacherResponseDTO;
import ir.maktabsharif.exammanagement.model.dto.userDTO.UserResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.Course;
import ir.maktabsharif.exammanagement.model.entity.Student;
import ir.maktabsharif.exammanagement.model.entity.Teacher;
import ir.maktabsharif.exammanagement.model.entity.User;
import ir.maktabsharif.exammanagement.repository.CourseRepository;
import ir.maktabsharif.exammanagement.repository.StudentRepository;
import ir.maktabsharif.exammanagement.repository.TeacherRepository;
import ir.maktabsharif.exammanagement.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public Course register(CourseRequestDTO courseRequestDTO) {
        Course course = new Course();
        course.setIdentifier(course.getIdentifier());
        course.setTitle(courseRequestDTO.getTitle());
        course.setStartDate(courseRequestDTO.getStartDate());
        course.setEndDate(courseRequestDTO.getEndDate());
        return courseRepository.save(course);
    }

    @Override
    public List<Course> findAll() {
        return List.of();
    }

    @Override
    public Boolean update(UUID uuid, CourseRequestDTO courseRequestDTO) {
        Course course = courseRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + uuid));

        course.setTitle(courseRequestDTO.getTitle());
        course.setStartDate(courseRequestDTO.getStartDate());
        course.setEndDate(courseRequestDTO.getEndDate());

        courseRepository.save(course);

        return true;
    }

    @Override
    public void delete(UUID uuid) {
        Course course = courseRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + uuid));
        courseRepository.delete(course);
    }

    @Override
    public Optional<Course> findById(UUID uuid) {
        return Optional.empty();
    }

    public Course addStudentToCourse(UUID courseID, UUID studentID) {

        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentID));

        // پیدا کردن دوره
        Course course = courseRepository.findById(courseID)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseID));

        List<Student> students = course.getStudents();
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
        course.setStudents(students);

        return courseRepository.save(course);
    }


    public Course addTeacherToCourse(String identifier, UUID teacherID) {

        Teacher teacher = teacherRepository.findById(teacherID)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Course course = courseRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTeacher(teacher);
        return courseRepository.save(course);
    }

    public List<CourseResponseDTO> getAllTeachers() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<CourseResponseDTO> getCoursesWithoutTeacher() {
        List<Course> courses = courseRepository.findByTeacherIsNull();
        return courses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CourseResponseDTO convertToDTO(Course course) {
        return new CourseResponseDTO(
                course.getTitle(),
                course.getStartDate(),
                course.getEndDate()
        );
    }


}
