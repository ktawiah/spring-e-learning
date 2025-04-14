package dev.practice.elearning.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.practice.elearning.model.helper.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Resource extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private int size;

    private String url;

    @OneToOne(mappedBy = "resource")
    @JsonBackReference
    private Lecture lecture;
}
