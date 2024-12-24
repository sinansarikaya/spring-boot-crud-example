package com.github.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksRequest {

    @NotBlank(message = "Title can not be null")
    @Size(max = 255, message = "Your title must be a maximum of {max} characters.")
    private String title;

    @NotBlank(message = "Author can not be null")
    @Size(max = 255, message = "Your author must be a maximum of {max} characters.")
    private String author;

    @NotBlank(message = "ISBN can not be null")
    @Size(max = 255, message = "Your ISBN must be a maximum of {max} characters.")
    private String isbn;

    @NotBlank(message = "Description can not be null")
    @Size(max = 1000, message = "Your description must be a maximum of {max} characters.")
    private String description;

    @NotNull(message = "Price can not be null")
    private double price;
}
