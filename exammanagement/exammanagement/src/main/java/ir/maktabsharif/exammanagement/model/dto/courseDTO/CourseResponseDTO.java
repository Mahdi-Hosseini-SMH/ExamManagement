package ir.maktabsharif.exammanagement.model.dto.courseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter

public class CourseResponseDTO {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    public CourseResponseDTO(String title, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
