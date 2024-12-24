package com.github.service;

import com.github.domain.Books;
import com.github.dto.BooksRequest;
import com.github.dto.BooksResponse;
import com.github.dto.RequestParams;
import com.github.exception.ResourceNotFoundException;
import com.github.mapper.BooksMapper;
import com.github.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BooksService {

    private final BooksRepository booksRepository;
    private final BooksMapper booksMapper;

    public ResponseEntity<BooksResponse> save(BooksRequest booksRequest) {
        Books books = booksMapper.requestToBooks(booksRequest);
        Books saveData = booksRepository.save(books);
        return ResponseEntity.ok(booksMapper.booksToResponse(saveData));
    }

    public Books getById(Long id) {
        return booksRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Books not found with id: " + id)
        );
    }

    public Page<BooksResponse> getAll(RequestParams params) {
        Pageable pageable = createPageable(params);
        return booksRepository.findAll(pageable).map(booksMapper::booksToResponse);
    }


    public ResponseEntity<BooksResponse> update(Long id, BooksRequest booksRequest) {
        Books existingBook = getById(id);

        Books updatedBook = booksMapper.requestToBooks(booksRequest).toBuilder()
                .id(existingBook.getId())
                .createdAt(existingBook.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        booksRepository.save(updatedBook);

        return ResponseEntity.ok(booksMapper.booksToResponse(updatedBook));
    }

    public String deleteById(Long id) {
        Books existingBook = getById(id);
        booksRepository.delete(existingBook);
        return "Book deleted with id: " + id;
    }

    private Pageable createPageable(RequestParams params) {
        String sortField = params.getSort() != null ? params.getSort() : "id"; // Varsayılan alan
        String direction = params.getDirection() != null ? params.getDirection() : "ASC"; // Varsayılan sıralama
        Sort sort = Sort.by(sortField);
        sort = direction.equalsIgnoreCase("DESC") ? sort.descending() : sort.ascending();
        return PageRequest.of(params.getPage(), params.getSize(), sort);
    }



}
