package com.example.demo.services;


import com.example.demo.Models.Book;
import com.example.demo.Models.Favourite;
import com.example.demo.Models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private FavouriteService favouriteService;

    public String addUser(User user) {
        Optional<User> existingUser = getUserByEmail(user.getEmail());
        if (existingUser.isEmpty()) {
            user.setRole("USER");
            repository.save(user);
            User newUser = getUserByEmail(user.getEmail()).orElse(null);
            if(newUser.getId() == 1) {
                newUser.setRole("ADMIN");
            }
            favouriteService.addFavouriteList(new Favourite(newUser, new ArrayList<>()));
            return  "User added Successfully";
        }
        else {
            return "User already exist";
        }
    }

    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(repository.findByEmail(email).orElse(null));
    }

    public User updateUser(User user) {
        User existingUser = getUserByEmail(user.getEmail()).orElse(null);
        if (existingUser != null) {
            existingUser.setEmail(user.getEmail());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setFavourite(user.getFavourite());
            existingUser.setLastName(user.getLastName());
            existingUser.setPassword(user.getPassword());
            existingUser.setUsername(user.getUsername());
            repository.save(existingUser);
            return existingUser;
        }
        return null;
    }

    public String deleteUser(Integer id) {
        repository.deleteById(id);
        return "User has been removed from the database";
    }
}
