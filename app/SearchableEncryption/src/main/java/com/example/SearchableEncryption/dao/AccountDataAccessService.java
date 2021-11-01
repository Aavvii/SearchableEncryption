package com.example.SearchableEncryption.dao;

import com.example.SearchableEncryption.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgres")
public class AccountDataAccessService implements IAccountDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertAccount(long id, Account account) {
        return 0;
    }

    @Override
    public List<Account> getAllAccounts() {
        final String sql= "SELECT id, firstname, lastname, username, password FROM Account";
        List<Account> accounts = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Account();
        });
        return accounts;
    }

    @Override
    public int deleteAccountById(long id) {
        return 0;
    }

    @Override
    public int updateAccountById(long id, Account account) {
        return 0;
    }

    @Override
    public Optional<Account> getAccountById(long id) {
        return Optional.empty();
    }
}
