package com.example.SearchableEncryption.controllers;

import com.example.SearchableEncryption.models.Account;
import com.example.SearchableEncryption.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/account")
@RestController
public class AccountController {

    private final AccountRepository accountRepo;

    @Autowired
    public AccountController(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @PostMapping
    public void addAccount(@RequestBody Account account){
        accountRepo.save(account);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    @GetMapping(path="id={id}")
    public Account getAccountById(@PathVariable("id") long id) {
        return accountRepo.findById(id)
                .orElse(null);
    }

    @DeleteMapping(path="id={id}")
    public void deleteAccountById(@PathVariable("id") long id) {
        accountRepo.deleteById(id);
    }

    @PutMapping(path="id={id}")
    public void updateAccount(@PathVariable("id") long id, @Valid @NotNull @RequestBody Account accountToUpdate) {
        accountRepo.save(accountToUpdate);
    }


    @GetMapping(path="username={username}/password={password}")
    public Account getAccountByUsernameAndPassword(@PathVariable("username") String username, @PathVariable("password") String password) {
        return accountRepo.findByUserNameAndPassword(username, password)
                .orElse(null);
    }
}
