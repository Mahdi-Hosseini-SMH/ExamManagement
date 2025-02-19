package ir.maktabsharif.exammanagement.model.dto.userDTO;

import ir.maktabsharif.exammanagement.model.enums.Role;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String nationalCode;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;
    private Role role;
}
