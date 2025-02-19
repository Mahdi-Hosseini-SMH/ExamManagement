package ir.maktabsharif.exammanagement.service.impl;

import ir.maktabsharif.exammanagement.config.AdminConfig;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminConfig adminConfig;

    public AdminService(AdminConfig adminConfig) {
        this.adminConfig = adminConfig;
    }

    public Boolean loginAdmin(String username, String password) {
        return username.equals(adminConfig.getAdminUsername()) && password.equals(
                adminConfig.getAdminPassword());
    }
}
