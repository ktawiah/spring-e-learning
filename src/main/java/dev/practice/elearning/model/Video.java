package dev.practice.elearning.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@EqualsAndHashCode(callSuper = true)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video extends Resource {
    private int length;
}
