package com.example.SearchableEncryption.dao;

import com.example.SearchableEncryption.models.Account;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public interface IAccountDao {
    // verify if the data is consistent in the table account
    int insertAccount(long id, Account account);
    default int insertAccount(Account account) {
        long id = ThreadLocalRandom.current().nextLong();
        return insertAccount(id, account);
    }
    List<Account> getAllAccounts();

    int deleteAccountById(long id);

    int updateAccountById(long id, Account account);

    Optional<Account> getAccountById(long id);
}
