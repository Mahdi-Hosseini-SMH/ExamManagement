package ir.maktabsharif.exammanagement.model.dto.studentDTO;

import ir.maktabsharif.exammanagement.model.enums.Role;
import ir.maktabsharif.exammanagement.model.enums.Status;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequestDTO {
//    @NotBlank(message = "نام نمی‌تواند خالی باشد")
//    @Size(min = 2, max = 50, message = "نام باید بین 2 تا 20 کاراکتر باشد")
    private String firstName;

//    @NotBlank(message = "نام خانوادگی نمی‌تواند خالی باشد")
//    @Size(min = 2, max = 50, message = "نام خانوادگی باید بین 2 تا 20 کاراکتر باشد")
    private String lastName;

//    @NotBlank(message = "نام کاربری نمی‌تواند خالی باشد")
//    @Size(min = 4, max = 20, message = "نام کاربری باید بین 4 تا 20 کاراکتر باشد")
    @Column(unique = true)
    private String username;

//    @NotBlank(message = "رمز عبور نمی‌تواند خالی باشد")
//    @Size(min = 8, max = 20, message = "رمز عبور باید بین 8 تا 20 کاراکتر باشد")
    private String password;

//    @NotBlank(message = "کد ملی نمی‌تواند خالی باشد")
//    @Pattern(regexp = "\\d{10}", message = "کد ملی باید 10 رقم باشد")
    private String nationalCode;

//    @Past(message = "تاریخ تولد باید در گذشته باشد")
    private LocalDate dateOfBirth;

//    @NotBlank(message = "شماره تلفن نمی‌تواند خالی باشد")
//    @Pattern(regexp = "\\d{10}", message = "شماره تلفن باید 10رقم باشد")
    private String phoneNumber;

//    @NotBlank(message = "ایمیل نمی‌تواند خالی باشد")
//    @Email(message = "ایمیل معتبر نیست")
    private String email;

//    @NotNull(message = "وضعیت نمی‌تواند خالی باشد")
    private Status status;
    private Role role;

}
