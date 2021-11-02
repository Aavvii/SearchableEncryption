package com.example.SearchableEncryption.dao;

import com.example.SearchableEncryption.models.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("accountDao")
public class AccountDao implements IAccountDao {

    private static List<Account> DB = new ArrayList<>();

    @Override
    public int insertAccount(long id, Account account) {
        DB.add(new Account());
        return 1;
    }

    @Override
    public List<Account> getAllAccounts() {
        return DB;
    }

    @Override
    public int deleteAccountById(long id) {
        Optional<Account> accountRes = getAccountById(id);
        if(accountRes.isEmpty()){
            return 0;
        }
        DB.remove(accountRes.get());
        return 1;
    }

    @Override
    public int updateAccountById(long id, Account account) {
        return getAccountById(id)
                .map(a-> {
                    int indexOfAccountToDelete = DB.indexOf(account);
                    if (indexOfAccountToDelete >= 0) {
                        DB.set(indexOfAccountToDelete, account);
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public Optional<Account> getAccountById(long id) {
        return DB.stream()
                .filter(account -> account.getId() == id)
                .findFirst();
    }


}
