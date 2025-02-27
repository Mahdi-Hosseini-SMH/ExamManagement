package ir.maktabsharif.exammanagement.model.dto.examDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamRequestDTO {

    @NotBlank
    @Size(min = 2, max = 15, message = "The title must be between 2 and 15 characters.")
    private String title;
    @NotBlank
    @Size(min = 2, max = 25, message = "The description must be between 2 and 25 characters.")
    private String description;
    @Min(value = 30 , message = "The minimum time is 30 minutes.")
    @Max(value = 180 , message = "The maximum time is 180 minutes.")
    private int duration;

}
