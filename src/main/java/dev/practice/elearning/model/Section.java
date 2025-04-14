package dev.practice.elearning.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Section extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private int sectionOrder;

    @ManyToOne
    @JsonManagedReference
    private Course course;

    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Lecture> lectures;
}
