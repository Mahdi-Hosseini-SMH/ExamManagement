package ir.maktabsharif.exammanagement.model.dto.studentDTO;

import ir.maktabsharif.exammanagement.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {
    private String firstName;
    private String lastName;
    private String nationalCode;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private Status status;
}
