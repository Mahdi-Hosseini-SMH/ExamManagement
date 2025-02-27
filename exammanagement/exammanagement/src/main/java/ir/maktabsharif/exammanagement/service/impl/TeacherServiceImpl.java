package ir.maktabsharif.exammanagement.service.impl;

import ir.maktabsharif.exammanagement.model.dto.teacherDTO.TeacherRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.teacherDTO.TeacherResponseDTO;
import ir.maktabsharif.exammanagement.model.dto.userDTO.UserResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.Course;
import ir.maktabsharif.exammanagement.model.entity.Teacher;
import ir.maktabsharif.exammanagement.model.entity.User;
import ir.maktabsharif.exammanagement.model.enums.Role;
import ir.maktabsharif.exammanagement.model.enums.Status;
import ir.maktabsharif.exammanagement.repository.CourseRepository;
import ir.maktabsharif.exammanagement.repository.TeacherRepository;
import ir.maktabsharif.exammanagement.service.TeacherService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final CourseRepository courseRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Teacher register(TeacherRequestDTO teacherRequestDTO) {
        String hashedPassword = passwordEncoder.encode(teacherRequestDTO.getPassword());
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherRequestDTO.getFirstName());
        teacher.setLastName(teacherRequestDTO.getLastName());
        teacher.setUsername(teacherRequestDTO.getUsername());
        teacher.setPassword(hashedPassword);
        teacher.setNationalCode(teacherRequestDTO.getNationalCode());
        teacher.setPhoneNumber(teacherRequestDTO.getPhoneNumber());
        teacher.setDateOfBirth(teacherRequestDTO.getDateOfBirth());
        teacher.setEmail(teacherRequestDTO.getEmail());
        teacher.setStatus(Status.PENDING);
        teacher.setRole(Role.TEACHER);
        return teacherRepository.save(teacher);
    }

    @Override
    public TeacherResponseDTO convertToDTO(Teacher teacher) {
        return new TeacherResponseDTO(
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getNationalCode(),
                teacher.getDateOfBirth(),
                teacher.getPhoneNumber(),
                teacher.getStatus()
        );
    }

    @Override
    public Optional<Teacher> loginTeacher(String username, String password) {
        Optional<Teacher> optionalTeacher = teacherRepository.findByUsername(username);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            if (passwordEncoder.matches(password, teacher.getPassword())) {
                return Optional.of(teacher);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<TeacherResponseDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAllByStatus(Status.APPROVED);
        return teachers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Boolean update(UUID uuid, TeacherRequestDTO teacherRequestDTO) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(uuid);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setFirstName(teacherRequestDTO.getFirstName());
            teacher.setLastName(teacherRequestDTO.getLastName());
            teacher.setUsername(teacherRequestDTO.getUsername());
            teacher.setPassword(teacherRequestDTO.getPassword());
            teacher.setEmail(teacherRequestDTO.getEmail());
            teacher.setRole(teacherRequestDTO.getRole());
            teacher.setDateOfBirth(teacherRequestDTO.getDateOfBirth());
            teacher.setPhoneNumber(teacherRequestDTO.getPhoneNumber());
            teacher.setNationalCode(teacherRequestDTO.getNationalCode());
            teacherRepository.save(teacher);
            return true;

        } else {
            return false;
        }

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<Teacher> findById(UUID uuid) {
        return Optional.empty();
    }

    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    public List<Course> getCoursesByTeacher(UUID teacherId){
        return courseRepository.findByTeacherId(teacherId);
    }

}
