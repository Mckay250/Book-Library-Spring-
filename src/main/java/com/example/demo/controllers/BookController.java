package com.example.demo.controllers;

import com.example.demo.Models.Book;
import com.example.demo.Models.Category;
import com.example.demo.services.BookService;
import com.example.demo.services.CategoryService;
import com.example.demo.services.FileStorageService;
import com.example.demo.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    public BookController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

//    @PostMapping("/api/admin/{category}/addBook")
//    public String addBook(@NotNull @Valid @RequestBody Book book, @PathVariable String category) {
//        Category thisCategory = categoryService.getCategoryByName(category);
//        if (thisCategory != null) {
//            book.setCategory(thisCategory);}
//        else {
//            book.setCategory(new Category(category));
//        }
//        return bookService.addBook(book);
//    }

//    @PostMapping("/api/admin/{category}/addBook")
//    public String addBook(
//            @RequestParam(value = "book") MultipartFile book,
//            @RequestParam(value = "cover") MultipartFile cover,
//            @RequestParam(value = "name") String name,
//            @RequestParam(value = "description") String description,
//            @RequestParam(value = "author") String author,
//            @PathVariable String category) throws IOException {

//        void bookName = fileStorageService.storeFile(book);
//        void coverName = fileStorageService.storeFile(cover);
//        String bookDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH)
//                .path(bookName).toUriString();
//        String coverDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH)
//                .path(coverName).toUriString();
//        Book newBook = new Book();
//        newBook.setFile(bookDownloadUri);
//        newBook.setCover(coverDownloadUri);
//        newBook.setName(name);
//        newBook.setAuthor(author);
//        newBook.setDescription(description);
//        Category newCategory = categoryService.getCategoryByName(category);
//        newBook.setCategory(newCategory);
//        return bookService.addBook(newBook);
//    }

//    @GetMapping("/api/open/{category}/books")
//    public List<Book> getBookByCategory(@PathVariable String category) {
//        return bookService.getBookByCategory(category);
//    }
//
//    @GetMapping("/api/open/books/search/")
//    public List<Book> getProductByName(@RequestParam("contains") String name) {
//        return bookService.searchBookByName(name);
//    }
//
//    @GetMapping("/api/open/category/book/{id}")
//    public Book getBookById(@PathVariable("id") Integer id) {
//        return bookService.getBookById(id);
//    }
//
//
//    @GetMapping("/api/open/allBooks")
//    public List<Book> getAllBooks() {
//        return bookService.getAllBooks();
//    }
//
//    @DeleteMapping("/api/open/admin/deleteBook/{id}")
//    public String deleteProductById(@PathVariable Integer id) {
//        return bookService.deleteBook(id);
//    }
//
//    @PutMapping("/api/open/admin/updateBook")
//    public String updateProduct(@RequestBody Book bookToUpdate) {
//        return bookService.updateBook(bookToUpdate);
//    }
}
