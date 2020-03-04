package com.persist.jpa;

import com.persist.jpa.entity.User;
import com.persist.jpa.service.UsersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner userDemo(UsersService userService) {
        return (args) -> {

            // create users
            User user1 = new User("1", "test1", "test1", "test1@g.com");
            User user2 = new User("2", "test2", "test2", "test2@g.com");

            List<User> users = new ArrayList();
            users.add(user1);
            users.add(user2);
            userService.saveUsers(users);

            // fetch all users
            User u1 = userService.getUsers("1").get(0);

            // fetch user by account id


        };
    }
}
