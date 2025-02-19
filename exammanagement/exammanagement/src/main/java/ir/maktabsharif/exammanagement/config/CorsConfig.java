package ir.maktabsharif.exammanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // اجازه دسترسی به همه مسیرها
                .allowedOrigins("*") // اجازه دسترسی از همه دامنه‌ها
                .allowedMethods("GET", "POST", "PUT", "DELETE") // اجازه دسترسی به متدهای خاص
                .allowedHeaders("*"); // اجازه دسترسی به همه هدرها
    }
}