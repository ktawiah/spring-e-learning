package dev.practice.elearning.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class File extends Resource{
    private String type;
}
