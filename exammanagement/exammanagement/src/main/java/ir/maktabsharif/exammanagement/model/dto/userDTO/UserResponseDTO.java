package ir.maktabsharif.exammanagement.model.dto.userDTO;

import ir.maktabsharif.exammanagement.model.enums.Role;
import ir.maktabsharif.exammanagement.model.enums.Status;

import java.time.LocalDate;

public class UserResponseDTO {
    private String firstName;
    private String lastName;
    private String nationalCode;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private Status status;
    private String email;
    private Role role;

    public UserResponseDTO(String firstName, String lastName, String nationalCode, LocalDate dateOfBirth, String phoneNumber, Status status, Role role,String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.role = role;
        this.email = email;
    }

    public UserResponseDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
