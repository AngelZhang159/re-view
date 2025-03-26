package dev.angelzhang.reviewservice.entities;

import dev.angelzhang.reviewservice.enums.Type;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Type type;
    private Instant createdAt;
    private Instant updatedAt;

}
