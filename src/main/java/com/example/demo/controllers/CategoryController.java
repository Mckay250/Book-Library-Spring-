package com.example.demo.controllers;

import com.example.demo.Models.Category;
import com.example.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/api/admin/addCategory")
    public String addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @GetMapping("/api/open/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping("/api/admin/updateCategory")
    public String updateCategory(@RequestBody Category categoryToUpdate) {
        categoryService.updateCategory(categoryToUpdate);
        return "Category has been updated";
    }

    @DeleteMapping("/api/admin/delete/{categoryId}")
    public String deleteCategoryByName(@PathVariable Integer categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

}
