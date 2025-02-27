package ir.maktabsharif.exammanagement;

import ir.maktabsharif.exammanagement.service.ExamService;
import ir.maktabsharif.exammanagement.service.TeacherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class ExammanagementApplication {


    public static void main(String[] args) {
		SpringApplication.run(ExammanagementApplication.class, args);
	}

}