package com.example.demo.controllers;

import com.example.demo.Models.Book;
import com.example.demo.Models.Favourite;
import com.example.demo.Models.FavouriteRequestBody;
import com.example.demo.services.FavouriteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class FavouriteController {

    private final FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }


    @PostMapping("/api/open/favourite/{favouritesId}/addBook")
    public String addToFavourite(@RequestBody Book book, @PathVariable Integer favouriteId) {
        return favouriteService.addFavouriteBook(book, favouriteId);
    }

    @GetMapping("/api/open/user/fav/{favouritesId}")
    public List<Book> getFavouriteBooks(@PathVariable Integer favouritesId) {
        return favouriteService.getFavouriteBooksById(favouritesId);
    }

    @DeleteMapping("/api/open/user/delete/")
    public String deleteFavouriteByName(@RequestBody Book book, @RequestBody Favourite favourite) {
        return favouriteService.removeBookFromFavourite(book, favourite);
    }

}
