package dev.practice.elearning.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Text extends Resource{
    private String content;
}
