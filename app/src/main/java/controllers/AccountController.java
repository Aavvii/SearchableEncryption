package controllers;

import models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repositories.UserRepository;

import java.util.UUID;

@RestController
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public String addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String userName, @RequestParam String password) {
        Account newAccount = new Account();
        newAccount.setFirstName(firstName);
        newAccount.setLastName(lastName);
        newAccount.setUserName(userName);
        newAccount.setPassword(password);
        userRepository.save(newAccount);
        return "Added new user account!";
    }

    @GetMapping("/list")
    public Iterable<Account> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Account findCustomerById(@PathVariable UUID id) {
        return userRepository.findUserById(id);
    }
}
