package com.bookstore.service;

import com.bookstore.dto.BookDto;
import com.bookstore.dto.CreateBookRequestDto;
import com.bookstore.exception.EntityNotFoundException;
import com.bookstore.mapper.BookMapper;
import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public Page<BookDto> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Can't find book by id: " + id
                        ));
    }

    @Override
    public BookDto createBook(CreateBookRequestDto bookDto) {
        return bookMapper.toDto(
                bookRepository.save(bookMapper.toModel(bookDto))
        );
    }

    @Override
    public BookDto updateBook(Long id, CreateBookRequestDto bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Can't find book by id: " + id
                        ));

        bookMapper.updateBookFromDto(bookDto, book);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Can't find book by id: " + id
            );
        }
        bookRepository.deleteById(id);
    }
}
