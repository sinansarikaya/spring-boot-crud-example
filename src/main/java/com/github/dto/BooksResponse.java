package com.github.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BooksResponse {

    @Setter(AccessLevel.NONE)
    private Long id;

    private String title;

    private String author;

    private String isbn;

    private String description;

    private double price;

    @Setter(AccessLevel.NONE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "UTC")
    private LocalDateTime createdAt;

    @Setter(AccessLevel.NONE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "UTC")
    private LocalDateTime updatedAt;

}
