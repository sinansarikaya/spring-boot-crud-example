package com.github.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
@Builder(toBuilder = true)
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Title can not be null")
    @Size(max = 255, message = "Your title must be a maximum of {max} characters.")
    @Column(nullable = false, length = 255)
    private String title;

    @NotBlank(message = "Author can not be null")
    @Size(max = 255, message = "Your author must be a maximum of {max} characters.")
    @Column(nullable = false, length = 255)
    private String author;

    @NotBlank(message = "ISBN can not be null")
    @Size(max = 255, message = "Your ISBN must be a maximum of {max} characters.")
    @Column(nullable = false, length = 255)
    private String isbn;

    @NotBlank(message = "Description can not be null")
    @Size(max = 1000, message = "Your description must be a maximum of {max} characters.")
    @Column(nullable = false, length = 1000)
    private String description;

    @NotNull(message = "Price can not be null")
    @Positive(message = "Price must be positive")
    @Column(nullable = false)
    private double price;

    @Setter(AccessLevel.NONE)
    @Column(nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "UTC")
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
