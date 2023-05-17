package com.micro.demo.Services;

import com.micro.demo.Model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    Iterable<User> getAll();
    User getUser(Long licenseNumber);

    ResponseEntity<String> addUser(User user);

    ResponseEntity<String> updateUser(User user);

    ResponseEntity<String> deleteUser(Long licenseNumber);
}
