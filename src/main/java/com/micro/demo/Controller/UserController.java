package com.micro.demo.Controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.micro.demo.Model.User;
import com.micro.demo.dao.UserDao;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;
import java.util.List;
import java.util.Objects;

/**
 * Classe controller pour le user
 */
@RestController
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users")
    public MappingJacksonValue getAll() {
        List<User> users = userDao.findAll();
        SimpleBeanPropertyFilter emailFilter = SimpleBeanPropertyFilter.serializeAllExcept("email");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("filterByEmail", emailFilter);
        MappingJacksonValue usersFilter = new MappingJacksonValue(users);
        usersFilter.setFilters(filterProvider);
        return usersFilter;
    }

    @GetMapping("/user/{id}")
    public User getOneUser(@PathVariable Long id) {
        return userDao.findById(id);
    }

    @PostMapping("/users")
    @ApiIgnore
    public ResponseEntity<User> addUser(@RequestBody User user) {
       User userAdded = userDao.save(user);
       if (Objects.isNull(userAdded)) {
           return ResponseEntity.noContent().build();
       }
       URI location = ServletUriComponentsBuilder
               .fromCurrentRequest()
               .path("/{id}")
               .buildAndExpand(userAdded.getLicense_number())
               .toUri();
       return ResponseEntity.created(location).build();
    }

    @PutMapping("/user/{id}")
    String replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        User user = userDao.findById(id);
        int userIndex = userDao.findAll().indexOf(user);

        if (!Objects.isNull(newUser)) {
            userDao.findAll().set(userIndex, newUser);
            return "user update";
        }
        return "Error please check the sending data";
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) {
        userDao.delete(userDao.findById(id));
        return "user delete";
    }
}
