package com.example.demo.repositories;

import com.example.demo.Models.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {

    Optional<Favourite> findByUserEmailContaining(String email);
}
