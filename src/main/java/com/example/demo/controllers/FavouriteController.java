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


    @PostMapping("/api/user/addToFavourite/{favouriteId}")
    public String addToFavourite(@RequestBody FavouriteRequestBody request) {
        return favouriteService.addFavouriteBook(request.getBook(), request.getFavourite().getId());
    }

    @PostMapping("/api/user/fav/{favId}")
    public List<Book> getFavouriteBooks(@PathVariable Integer favId) {
        return favouriteService.getFavouriteBooksById(favId);
    }

    @DeleteMapping("/api/user/delete/")
    public String deleteFavouriteByName(@RequestBody Book book, @RequestBody Favourite favourite) {
        return favouriteService.removeBookFromFavourite(book, favourite);
    }

}
