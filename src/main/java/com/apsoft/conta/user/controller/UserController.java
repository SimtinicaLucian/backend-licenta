package com.apsoft.conta.user.controller;


import com.apsoft.conta.user.persistence.User;
import com.apsoft.conta.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;


@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/viewUser/{email}")
    public Optional<User> getUser(@PathVariable String email){
        log.info("Hattz");
        return userService.findUserByEmail(email);
    }

    @GetMapping("/viewAll")
    public List<User> searchAll(){
        log.info("All");
        return userService.searchAll();
    }
}
