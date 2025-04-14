package dev.angelzhang.reviewservice.entities;

import dev.angelzhang.reviewservice.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(nullable = false)
    private Integer rating;
    @Column(length = 2048)
    private String review;
    private Instant createdAt;
    private Instant updatedAt;

}
