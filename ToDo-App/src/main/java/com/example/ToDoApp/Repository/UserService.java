package com.example.ToDoApp.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    public List<UserRepo> listAll(){
        return (List<UserRepo>) repo.findAll();
    }
}
