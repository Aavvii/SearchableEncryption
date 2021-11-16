package com.example.SearchableEncryption.controllers;

import com.example.SearchableEncryption.models.Account;
import com.example.SearchableEncryption.repository.AccountRepository;
import com.example.SearchableEncryption.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/account")
@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public void addAccount(@RequestBody Account account){
        accountService.save(account);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        System.out.println("I just gotAllAccounts()");
        return accountService.findAll();
    }

    @GetMapping(path="id={id}")
    public Account getAccountById(@PathVariable("id") long id) {
        return accountService.findById(id)
                .orElse(null);
    }

    @DeleteMapping(path="id={id}")
    public void deleteAccountById(@PathVariable("id") long id) {
        accountService.deleteById(id);
    }

    @PutMapping(path="id={id}")
    public void updateAccount(@PathVariable("id") long id, @Valid @NotNull @RequestBody Account accountToUpdate) {
        accountService.save(accountToUpdate);
    }


    @GetMapping(path="username={username}/password={password}")
    public Account getAccountByUsernameAndPassword(@PathVariable("username") String username, @PathVariable("password") String password) {
        return accountService.findByUsernameAndPassword(username, password)
                .orElse(null);
    }
}
