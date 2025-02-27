package ir.maktabsharif.exammanagement.controller;


import ir.maktabsharif.exammanagement.model.dto.userDTO.UserRequestDTO;
import ir.maktabsharif.exammanagement.model.dto.userDTO.UserResponseDTO;
import ir.maktabsharif.exammanagement.model.entity.User;
import ir.maktabsharif.exammanagement.model.enums.Role;
import ir.maktabsharif.exammanagement.service.impl.AdminService;
import ir.maktabsharif.exammanagement.service.impl.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestParam String username, @RequestParam String password) {
        if (adminService.loginAdmin(username, password)) {
            return ResponseEntity.ok("Login was successful.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("The username or password is incorrect.");
        }
    }

    @GetMapping("/pending-users")
    public ResponseEntity<List<UserResponseDTO>> getPendingUsers() {
        List<UserResponseDTO> pendingUsers = userService.getPendingUsers();
        return ResponseEntity.ok(pendingUsers);
    }

    @GetMapping("/approved-users")
    public ResponseEntity<List<UserResponseDTO>> getApprovedUsers() {
        List<UserResponseDTO> approvedUsers = userService.getApprovedUsers();
        return ResponseEntity.ok(approvedUsers);
    }


    @PostMapping("/approve-user/{nationalCode}")
    public ResponseEntity<User> approveUser(@PathVariable String nationalCode) {
        User user = userService.approveUser(nationalCode);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/users/{nationalCode}")
    public ResponseEntity<String> updateByNationalCode(@Valid @PathVariable String nationalCode, @RequestBody UserRequestDTO userRequestDTO) {
        boolean isUpdate = userService.updateByNationalCode(nationalCode, userRequestDTO);
        if (isUpdate) {
            return ResponseEntity.ok("The update was successful.");
        } else {
            return ResponseEntity.status(404).body("ID not found");
        }
    }

    @DeleteMapping("/delete-user/{nationalCode}")
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable String nationalCode) {
        userService.deleteByNationalCode(nationalCode);
        return ResponseEntity.ok("User deleted.");
    }

    @GetMapping("users/filter")
    public List<User> filterUsers(
            @RequestParam(required = false) String nationalCode,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String role) {
        return userService.filterUsers(nationalCode, firstName, lastName, role);
    }

    @GetMapping("find-users/{nationalCode}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable String nationalCode, @RequestBody UserResponseDTO userResponseDTO) {
        Optional<User> user = userService.findUserByNationalCode(nationalCode, userResponseDTO);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
