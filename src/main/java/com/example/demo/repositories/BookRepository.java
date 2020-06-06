package com.example.demo.repositories;

import com.example.demo.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByNameContaining(String name);

    List<Book> findByAuthorContaining(String author);

    List<Book> findByCategoryName(String name);
}
