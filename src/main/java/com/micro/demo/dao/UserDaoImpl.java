package com.micro.demo.dao;

import com.micro.demo.Model.User;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImpl implements UserDao{

    public static List<User> users = new ArrayList<>();

    static {
        users.add(new User(125489654215L, "nico", "flemme", new Date(2000), "nico@gmail.com"));
        users.add(new User(125469875423L, "plouf", "plouf", new Date(2000), "plouf@gmail.com"));
        users.add(new User(658954120124L, "aupif", "aupaf", new Date(2000), "pifpaf@gmail.com"));
    }
    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) {
        for (User user : users) {
            if (Objects.equals(user.getLicense_number(), id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }
}
