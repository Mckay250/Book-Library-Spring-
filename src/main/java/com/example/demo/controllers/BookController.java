package com.example.demo.controllers;

import com.example.demo.Models.Book;
import com.example.demo.Models.Category;
import com.example.demo.services.BookService;
import com.example.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    public BookController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @PostMapping("/api/admin/{category}/addBook")
    public String addBook(@NotNull @Valid @RequestBody Book book, @PathVariable String category) {
        Category thisCategory = categoryService.getCategoryByName(category);
        if (thisCategory != null) {
            book.setCategory(thisCategory);}
        else {
            book.setCategory(new Category(category));
        }
        return bookService.addBook(book);
    }

    @GetMapping("/api/open/{category}/books")
    public List<Book> getBookByCategory(@PathVariable String category) {
        return bookService.getBookByCategory(category);
    }

    @GetMapping("/api/open/books/search/")
    public List<Book> getProductByName(@RequestParam("contains") String name) {
        return bookService.searchBookByName(name);
    }

    @GetMapping("/api/open/category/book/{id}")
    public Book getBookById(@PathVariable("id") Integer id) {
        return bookService.getBookById(id);
    }


    @GetMapping("/api/open/allBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @DeleteMapping("/api/admin/deleteBook/{id}")
    public String deleteProductById(@PathVariable Integer id) {
        return bookService.deleteBook(id);
    }

    @PutMapping("/api/admin/updateBook")
    public String updateProduct(@RequestBody Book bookToUpdate) {
        return bookService.updateBook(bookToUpdate);
    }
}
