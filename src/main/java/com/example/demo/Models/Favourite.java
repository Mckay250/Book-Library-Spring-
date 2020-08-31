package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Favourite implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "favourite")
    @JsonIgnore
    private User user;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="favourite_book", joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "favourite_id"))
    private List<Book> books;

    public Favourite() {
    }

    public Favourite(User user, List<Book> books) {
        this.user = user;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
