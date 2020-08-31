package com.example.demo.controllers;

import com.example.demo.Exception.MyFileNotFoundException;
import com.example.demo.Models.Book;
import com.example.demo.Models.BookRequest;
import com.example.demo.Models.Category;
import com.example.demo.services.BookService;
import com.example.demo.services.CategoryService;
import com.example.demo.services.FileStorageService;
import com.example.demo.services.FileStorageServiceImpl;
import com.example.demo.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@RestController
public class NewBookController {

    private final FileStorageService storageService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public NewBookController(FileStorageServiceImpl storageService,
                             CategoryService categoryService,
                             BookService bookService) {
        this.storageService = storageService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @GetMapping("api/open/files/{filename}")
    @ResponseBody
    public Resource serveFile(@PathVariable String filename) {
        Resource file = storageService.loadFileAsResource(filename);
        return file;
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename= \"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("api/open/addBook")
    public String addBook(
            @RequestParam("book") MultipartFile book,
            @RequestParam("cover") MultipartFile cover,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("author") String author,
            @RequestParam("category")String category) throws IOException {

        String bookPath = storageService.storeFile(book);
        String coverPath = storageService.storeFile(cover);
        Book newBook = new Book();
        newBook.setFile(bookPath);
        newBook.setCover(coverPath);
        newBook.setName(name);
        newBook.setAuthor(author);
        newBook.setDescription(description);
        Category newCategory = categoryService.getCategoryByName(category);
        if (newCategory == null) {
            Category tempCategory = new Category(name);
            categoryService.addCategory(tempCategory);
            newBook.setCategory(tempCategory);
        }
        else {
            newBook.setCategory(newCategory);
        }
        return bookService.addBook(newBook);
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

    @DeleteMapping("/api/open/admin/deleteBook/{id}")
    public String deleteBookById(@PathVariable Integer id) {
        return bookService.deleteBook(id);
    }

    @PutMapping("/api/open/admin/updateBook")
    public String updateProduct(@RequestBody Book bookToUpdate) {
        return bookService.updateBook(bookToUpdate);
    }

    @ExceptionHandler(MyFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(MyFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
