package com.example.SearchableEncryption.service;

import com.example.SearchableEncryption.models.Account;
import com.example.SearchableEncryption.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Optional<Account> findById(long id) {
        return repository.findById(id);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void save(Account account) {
        repository.save(account);
    }

    public Optional<Account> findByUsernameAndPassword(String username, String password) {
        return repository.findByUserNameAndPassword(username, password);
    }

}
