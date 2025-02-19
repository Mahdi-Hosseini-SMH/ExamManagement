package ir.maktabsharif.exammanagement.model.entity;

import ir.maktabsharif.exammanagement.model.baseentity.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Course extends BaseModel {
    @Column(unique = true)
    private String identifier;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToOne
    private Teacher teacher;

    @ManyToMany
    List<Student> students;
}
