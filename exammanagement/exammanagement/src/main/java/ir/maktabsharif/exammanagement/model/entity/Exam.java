package ir.maktabsharif.exammanagement.model.entity;

import ir.maktabsharif.exammanagement.model.baseentity.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Exam extends BaseModel {
    @NotBlank
    @Size(min = 2, max = 15, message = "The title must be between 2 and 15 characters.")
    private String title;
    @NotBlank
    @Size(min = 2, max = 25, message = "The description must be between 2 and 25 characters.")
    private String description;
    @Min(value = 30 , message = "The minimum time is 30 minutes.")
    @Max(value = 180 , message = "The maximum time is 180 minutes.")
    private int duration;

    @ManyToOne
    private Course course;

}
