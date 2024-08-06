package com.example.rentahome.repository;



import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rentahome.entity.User;



public interface  UserRepository  extends JpaRepository<User, Integer>{

    List<User> users = new ArrayList<>();
    
    public User findByNameAndPassword(String name, String password);

    public User findByUserId(int userId);

}
