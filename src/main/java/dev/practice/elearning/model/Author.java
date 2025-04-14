package dev.practice.elearning.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.practice.elearning.model.helper.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(updatable = false)
    private LocalDateTime dateOfBirth;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Course> courses;

    public int getAge() {
        return Period.between(this.dateOfBirth.toLocalDate(), LocalDate.now()).getYears();
    }

}
