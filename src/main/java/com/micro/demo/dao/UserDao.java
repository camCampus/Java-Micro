package com.micro.demo.dao;

import com.micro.demo.Model.User;

import java.math.BigInteger;
import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    User delete(User user);
}
