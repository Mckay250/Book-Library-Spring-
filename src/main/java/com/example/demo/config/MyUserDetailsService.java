package com.example.demo.config;


import com.example.demo.Models.User;
import com.example.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;



@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email).orElse(null));
        if (user == null) {
            throw new UsernameNotFoundException("Not found: " + email);
        }
        else{
            return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), new ArrayList<>());
        }
//        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
//        return user.map(MyUserDetails::new).get();
    }

//    public User findByEmail(String email) {
//        return userService.getUserByEmail(email);
//    }
}
