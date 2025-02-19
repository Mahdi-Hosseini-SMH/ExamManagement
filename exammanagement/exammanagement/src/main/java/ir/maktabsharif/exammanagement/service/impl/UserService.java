package ir.maktabsharif.exammanagement.service.impl;

import ir.maktabsharif.exammanagement.model.dto.teacherDTO.TeacherResponseDTO;
import ir.maktabsharif.exammanagement.model.dto.userDTO.UserRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.userDTO.UserResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.Teacher;
import ir.maktabsharif.exammanagement.model.entity.User;
import ir.maktabsharif.exammanagement.model.enums.Role;
import ir.maktabsharif.exammanagement.model.enums.Status;
import ir.maktabsharif.exammanagement.repository.UserRepository;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getPendingUsers() {
        return userRepository.getAllByStatus(Status.PENDING)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserResponseDTO convertToDTO(User user) {
        return new UserResponseDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getNationalCode(),
                user.getDateOfBirth(),
                user.getPhoneNumber(),
                user.getStatus(),
                user.getRole(),
                user.getEmail()
        );
    }

    public List<UserResponseDTO> getApprovedUsers() {
        return userRepository.getAllByStatus(Status.APPROVED)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    public User approveUser(String nationalCode) {
        return userRepository.findByNationalCode(nationalCode)
                .map(user -> {
                    user.setStatus(Status.APPROVED);
                    return userRepository.save(user);
                }).orElse(null);
    }

    public boolean updateByNationalCode(String nationalCode, UserRequestDTO userRequestDTO) {
        Optional<User> optionalUser = userRepository.findByNationalCode(nationalCode);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(userRequestDTO.getFirstName());
            user.setLastName(userRequestDTO.getLastName());
            user.setUsername(userRequestDTO.getUsername());
            user.setPassword(userRequestDTO.getPassword());
            user.setDateOfBirth(userRequestDTO.getDateOfBirth());
            user.setEmail(userRequestDTO.getEmail());
            user.setRole(userRequestDTO.getRole());
            user.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void deleteByNationalCode(String nationalCode) {
        userRepository.deleteByNationalCode(nationalCode);
    }

    public List<User> findAllUsers() {
        return userRepository.getAllByStatus(Status.APPROVED);
    }

    public List<User> filterUsers(String nationalCode, String firstName, String lastName, String role) {
        return userRepository.findAll(createSpecification(nationalCode, firstName, lastName, role));
    }

    private Specification<User> createSpecification(String nationalCode, String firstName, String lastName, String role) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (nationalCode != null && !nationalCode.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("nationalCode"), nationalCode));
            }
            if (firstName != null && !firstName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"));
            }
            if (lastName != null && !lastName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%"));
            }
            if (role != null && !role.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("role"), role));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Optional<User> findUserByNationalCode(String nationalCode, UserResponseDTO userResponseDTO) {
        Optional<User> optionalUser = userRepository.findByNationalCode(nationalCode);
        return optionalUser;
    }
}

