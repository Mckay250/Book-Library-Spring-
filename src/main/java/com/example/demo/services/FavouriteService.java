package com.example.demo.services;

import com.example.demo.Models.Book;
import com.example.demo.Models.Favourite;
import com.example.demo.Models.User;
import com.example.demo.repositories.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@Service
public class FavouriteService {

    @Autowired
    private FavouriteRepository repository;
    @Autowired
    private UserService userService;

    public String addFavouriteList(Favourite favourite) {
        Optional<Favourite> existingFavouriteList =
                repository.findByUserEmailContaining(favourite.getUser().getEmail());
        if (existingFavouriteList.isPresent()) {

            return "The list already is exists";
        }
        else {
            repository.save(favourite);
            User existingUser = userService.getUserByEmail(favourite.getUser().getEmail()).orElse(null);
            existingUser.setFavourite(favourite);
            userService.updateUser(existingUser);
            return "Favourite List has been created";
        }
    }

    public String addFavouriteBook(Book book, Integer favouriteId) {
        Favourite existingFavourite = repository.findById(favouriteId).orElse(null);
        List<Book> existingBooks = existingFavourite.getBook();
        existingBooks.add(book);
        existingFavourite.setBook(existingBooks);
        repository.save(existingFavourite);
        return book + " has been added to Favourites";
    }

    public List<Book> getFavouriteBooksById(Integer id) {
        List<Book> books =  repository.findById(id).orElse(null).getBook();
        return books;
    }

    public String removeBookFromFavourite(Book book, Favourite favourite) {
        Favourite existingFavourite = repository.findById(favourite.getId()).orElse(null);
        if (existingFavourite != null) {
            List<Book> existingBooks = existingFavourite.getBook();
            existingBooks.remove(book);
            existingFavourite.setBook(existingBooks);
            repository.save(existingFavourite);
            return book + " has been removed from Favourites";
        }
        else  {
            return "The book does not exist in favourites";
        }
    }
}
