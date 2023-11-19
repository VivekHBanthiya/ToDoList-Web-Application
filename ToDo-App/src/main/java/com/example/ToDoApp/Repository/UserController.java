package com.example.ToDoApp.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/user")
    public String showUserList(Model model){
        List<UserRepo> listUserepo = service.listAll();
        model.addAttribute("listUserRepo", listUserepo);
        return "user";
    }

    @GetMapping("/user/new")
    public String showForm(Model model){
     model.addAttribute("user", new UserRepo());
     return "user_form";
    }
}
