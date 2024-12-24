package com.github.mapper;

import com.github.domain.Books;
import com.github.dto.BooksRequest;
import com.github.dto.BooksResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BooksMapper {

    // Request --> POJO
    public Books requestToBooks(BooksRequest booksRequest) {
        return Books.builder()
                .title(booksRequest.getTitle())
                .author(booksRequest.getAuthor())
                .isbn(booksRequest.getIsbn())
                .description(booksRequest.getDescription())
                .price(booksRequest.getPrice())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    // Pojo --> Response
    public BooksResponse booksToResponse (Books books) {
        return BooksResponse.builder()
                .id(books.getId())
                .title(books.getTitle())
                .author(books.getAuthor())
                .isbn(books.getIsbn())
                .description(books.getDescription())
                .price(books.getPrice())
                .createdAt(books.getCreatedAt())
                .updatedAt(books.getUpdatedAt())
                .build();
    }
}
