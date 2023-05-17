package com.micro.demo.Services;

import com.micro.demo.Model.User;

public interface UserService {

    Iterable<User> getAll();
    User getUser(Long licenseNumber);

    String addUser(User user);

    String updateUser(User user);

    String deleteUser(Long licenseNumber);
}
