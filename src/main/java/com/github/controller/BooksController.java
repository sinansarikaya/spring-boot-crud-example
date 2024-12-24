package com.github.controller;

import com.github.domain.Books;
import com.github.dto.BooksRequest;
import com.github.dto.BooksResponse;
import com.github.dto.RequestParams;
import com.github.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books") // http://localhost:8080/books
public class BooksController {

    private final BooksService booksService;

    @PostMapping("/save") // http://localhost:8080/books/save + POST + JSON
    public ResponseEntity<BooksResponse> save(@RequestBody BooksRequest booksRequest) {
        return booksService.save(booksRequest);
    }

    @GetMapping("/getById/{id}") // http://localhost:8080/books/getById/1 + GET
    public Books getById(@PathVariable Long id) {
        return booksService.getById(id);
    }

    @GetMapping("/getAll") // http://localhost:8080/books/getAll + GET
    public Page<BooksResponse> getAll(RequestParams params) {
        return booksService.getAll(params);
    }

    @PutMapping("/update/{id}") // http://localhost:8080/books/update/1 + PUT + JSON
    public ResponseEntity<BooksResponse> update(@PathVariable Long id, @RequestBody BooksRequest booksRequest) {
        return booksService.update(id, booksRequest);
    }

    @DeleteMapping("/delete/{id}") // http://localhost:8080/books/delete/1 + DELETE
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(booksService.deleteById(id));
    }


}
