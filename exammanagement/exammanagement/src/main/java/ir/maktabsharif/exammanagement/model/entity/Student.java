package ir.maktabsharif.exammanagement.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Student extends User{

    @OneToMany
    private List<Course> courses;

}
