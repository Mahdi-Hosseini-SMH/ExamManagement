package ir.maktabsharif.exammanagement.service.impl;

import ir.maktabsharif.exammanagement.model.dto.courseDTO.CourseRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.courseDTO.CourseResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.Course;
import ir.maktabsharif.exammanagement.model.entity.Student;
import ir.maktabsharif.exammanagement.model.entity.Teacher;
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
        course.setIdentifier(courseRequestDTO.getIdentifier());
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

    public Course addStudentToCourse(String identifier, String nationalCode) {

        Student student = studentRepository.findByNationalCode(nationalCode)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + nationalCode));

        Course course = courseRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + identifier));

        List<Student> students = course.getStudents();
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
        course.setStudents(students);

        return courseRepository.save(course);
    }


    public Course addTeacherToCourse(String identifier, String nationalCode) {

        Teacher teacher = teacherRepository.findByNationalCode(nationalCode)
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
                course.getIdentifier(),
                course.getTitle(),
                course.getStartDate(),
                course.getEndDate()
        );
    }

    public Course updateTeacherInCourse(String identifier, Teacher updatedTeacher) {
        Course course = courseRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new EntityNotFoundException("دوره با این شناسه یکتا یافت نشد: " + identifier));
        course.setTeacher(updatedTeacher);
        return courseRepository.save(course);

    }

    public Course removeStudentFromCourse(UUID courseID, UUID studentID) {
        Course course = courseRepository.findById(courseID)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseID));

        List<Student> students = course.getStudents();
        if (students != null) {
            students.removeIf(student -> student.getId().equals(studentID)); // حذف دانشجو
        }
        course.setStudents(students);
        return courseRepository.save(course);
    }

    public void removeTeacherFromCourse(String identifier) {
        Course course = courseRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new EntityNotFoundException("شناسه یکتا یافت نشد: " + identifier));

        course.setTeacher(null);
        courseRepository.save(course);
    }


    public Course getCourseWithParticipants(UUID courseID) {
        Course course = courseRepository.findById(courseID)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseID));

        Teacher teacher = course.getTeacher();
        List<Student> students = course.getStudents();
        return course;
    }

}