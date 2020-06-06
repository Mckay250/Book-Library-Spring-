package com.example.demo.services;

import com.example.demo.Models.Book;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public String addBook(Book book) {
        try {
            repository.save(book);
        } catch (IllegalArgumentException e) {
            return "illegal Argument: " + e;
        }
        return "Book has been added to repository";
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<Book> getBookByAuthor(String author) {
        return new ArrayList<>(repository.findByAuthorContaining(author));
    }

    public List<Book> getBookByCategory(String name) {
        return new ArrayList<>(repository.findByCategoryName(name));
    }

    public List<Book> searchBookByName(String name) {
        return new ArrayList<>(repository.findByNameContaining(name));
    }

    public String updateBook(Book book) {
        Book existingBook = repository.findById(book.getId()).orElse(null);
        existingBook.setAuthor(book.getAuthor());
        existingBook.setCategory(book.getCategory());
        existingBook.setCover(book.getCover());
        existingBook.setDescription(book.getDescription());
        existingBook.setName(book.getName());
        repository.save(existingBook);
        return "Book updated Successfully";
    }

    public String deleteBook(Integer id) {
        repository.deleteById(id);
        return "Book has been removed from the database";
    }

}
