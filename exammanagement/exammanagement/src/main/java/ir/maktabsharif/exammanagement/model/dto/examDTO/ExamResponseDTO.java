package ir.maktabsharif.exammanagement.model.dto.examDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ExamResponseDTO {

    private String title;
    private String description;
    private int duration;

}
