package com.example.SearchableEncryption.services;

import com.example.SearchableEncryption.dao.IAccountDao;
import com.example.SearchableEncryption.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final IAccountDao accountDao;

    @Autowired
    public AccountService(@Qualifier("postgres") IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public int addAccount(Account account){
        return accountDao.insertAccount(account);
    }

    public List<Account> getAllAccounts(){
        return accountDao.getAllAccounts();
    }

    public Optional<Account> getAccountById(long id) {
        return accountDao.getAccountById(id);
    }

    public int deleteAccount(long id) {
        return accountDao.deleteAccountById(id);
    }

    public int updateAccount(long id, Account newAccount) {
        return accountDao.updateAccountById(id, newAccount);
    }
}
