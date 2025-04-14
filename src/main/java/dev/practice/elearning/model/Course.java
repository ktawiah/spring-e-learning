package dev.practice.elearning.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.practice.elearning.model.helper.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
 // Order does not have any reference to User (no @ManyToOne or @JoinColumn here)
    // This makes the relationship unidirectional

    private String title;

    private String description;

//    @ManyToMany(mappedBy = "courses")
//    @JsonBackReference
//    private List<Author> authors;
}
