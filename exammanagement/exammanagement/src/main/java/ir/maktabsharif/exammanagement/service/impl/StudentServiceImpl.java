package ir.maktabsharif.exammanagement.service.impl;

import ir.maktabsharif.exammanagement.model.dto.courseDTO.CourseResponseDTO;
import ir.maktabsharif.exammanagement.model.dto.studentDTO.StudentRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.studentDTO.StudentResponseDTO;
import ir.maktabsharif.exammanagement.model.dto.teacherDTO.TeacherResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.Course;
import ir.maktabsharif.exammanagement.model.entity.Student;
import ir.maktabsharif.exammanagement.model.entity.Teacher;
import ir.maktabsharif.exammanagement.model.enums.Role;
import ir.maktabsharif.exammanagement.model.enums.Status;
import ir.maktabsharif.exammanagement.repository.StudentRepository;
import ir.maktabsharif.exammanagement.service.StudentService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Student register(StudentRequestDTO studentRequestDTO) {
        String hashedPassword = passwordEncoder.encode(studentRequestDTO.getPassword());
        Student student = new Student();
        student.setFirstName(studentRequestDTO.getFirstName());
        student.setLastName(studentRequestDTO.getLastName());
        student.setUsername(studentRequestDTO.getUsername());
        student.setPassword(hashedPassword);
        student.setNationalCode(studentRequestDTO.getNationalCode());
        student.setPhoneNumber(studentRequestDTO.getPhoneNumber());
        student.setDateOfBirth(studentRequestDTO.getDateOfBirth());
        student.setEmail(studentRequestDTO.getEmail());
        student.setStatus(Status.PENDING);
        student.setRole(Role.STUDENT);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Boolean update(UUID uuid, StudentRequestDTO studentRequestDTO) {
        Optional<Student> optionalStudent = studentRepository.findById(uuid);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setFirstName(studentRequestDTO.getFirstName());
            student.setLastName(studentRequestDTO.getLastName());
            student.setUsername(studentRequestDTO.getUsername());
            student.setPassword(studentRequestDTO.getPassword());
            student.setEmail(studentRequestDTO.getEmail());
            student.setRole(studentRequestDTO.getRole());
            student.setDateOfBirth(studentRequestDTO.getDateOfBirth());
            student.setPhoneNumber(studentRequestDTO.getPhoneNumber());
            student.setNationalCode(studentRequestDTO.getNationalCode());
            studentRepository.save(student);
            return true;
        }
        return false;
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<Student> findById(UUID uuid) {
        return studentRepository.findById(uuid);
    }

    @Override
    public List<StudentResponseDTO> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO convertToDTO(Student student) {
        return new StudentResponseDTO(
                student.getFirstName(),
                student.getLastName(),
                student.getNationalCode(),
                student.getDateOfBirth(),
                student.getPhoneNumber(),
                student.getStatus()
        );
    }


}

