package ir.maktabsharif.exammanagement.model.dto.courseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO {
    private String identifier;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

}
