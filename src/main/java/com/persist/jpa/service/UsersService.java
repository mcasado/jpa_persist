package com.persist.jpa.service;

import com.persist.jpa.entity.User;
import com.persist.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UsersService {

    @Autowired
    public UserRepository repo;

    public User addUser(String first, String last, String email, String accountid) {

        List<User> users = new ArrayList<>();
        User u = new User(accountid, first, last, email);
        users.add(u);
        return saveUsers(users).get(0);
    }

    public List<User> saveUsers(List<User> users) {
        return repo.saveUsers(users);
    }

    public List<User> getUsers(String accountId) {
        return repo.getUsers(accountId);
    }
}
