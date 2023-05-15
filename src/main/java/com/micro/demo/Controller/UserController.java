package com.micro.demo.Controller;

import com.micro.demo.Model.User;
import com.micro.demo.dao.UserDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }


    @GetMapping("/users")
    public List<User> getAll() {
        return userDao.findAll();
    }

    @GetMapping("/user/{id}")
    public User getOneUser(@PathVariable Long id) {
        return userDao.findById(id);
    }
}
