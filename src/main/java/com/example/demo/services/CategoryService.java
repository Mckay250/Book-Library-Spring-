package com.example.demo.services;

import com.example.demo.Models.Category;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public String addCategory(Category category) {
        Optional<Category> existingCategory = repository.findByName(category.getName());
        if (existingCategory.isPresent()) {
            return "Category already exist";
        }
        else {
            repository.save(category);
        }
        return "Category has been added";
    }

    public List<Category> addCategories(List<Category> categories) {
        return repository.saveAll(categories);
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public Category getCategoryByName(String category) {
        return repository.findByName(category).orElse(null);
    }

    public Category updateCategory(Category category) {
        Category existingCategory = repository.findById(category.getId()).orElse(null);
        existingCategory.setName(category.getName());
        return repository.save(existingCategory);
    }

    public String deleteCategory(Integer id) {
        repository.deleteById(id);
        return "Category has been deleted";
    }
}
