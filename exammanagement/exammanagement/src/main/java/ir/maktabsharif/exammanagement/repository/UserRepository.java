package ir.maktabsharif.exammanagement.repository;

import ir.maktabsharif.exammanagement.model.dto.userDTO.UserResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.User;
import ir.maktabsharif.exammanagement.model.enums.Role;
import ir.maktabsharif.exammanagement.model.enums.Status;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByNationalCode(String nationalCode);

    List<User> getAllByStatus(Status status);

    void deleteByNationalCode(String nationalCode);

    List<User> findAll(Specification<User> specification);

}

