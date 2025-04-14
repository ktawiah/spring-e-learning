package dev.practice.elearning.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.practice.elearning.model.helper.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lecture extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private Section section;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private Resource resource;
}
