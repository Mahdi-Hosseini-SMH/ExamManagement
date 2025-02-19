package ir.maktabsharif.exammanagement.model.dto.courseDTO;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CourseRequestDTO {
    private String identifier;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
}
