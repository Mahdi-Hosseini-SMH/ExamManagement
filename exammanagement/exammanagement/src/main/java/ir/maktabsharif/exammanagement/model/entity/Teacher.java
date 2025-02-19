package ir.maktabsharif.exammanagement.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class Teacher extends User {
    @OneToMany
    List<Course> courses;
}
