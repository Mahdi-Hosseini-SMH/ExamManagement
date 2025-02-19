package ir.maktabsharif.exammanagement.model.dto.teacherDTO;

import ir.maktabsharif.exammanagement.model.enums.Role;
import ir.maktabsharif.exammanagement.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
public class TeacherResponseDTO {
    private String firstName;
    private String lastName;
    private String nationalCode;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private Status status;

    public TeacherResponseDTO(String firstName, String lastName, String nationalCode, LocalDate dateOfBirth, String phoneNumber, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public TeacherResponseDTO(String firstName, String lastName, String nationalCode, LocalDate dateOfBirth, String phoneNumber, Status status, Role role, String email) {
    }
}

