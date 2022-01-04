package repos.interfaces;

import models.Account;
import models.Status;

import java.util.List;

public interface IAccountMapper {

    List<Account> getAllAccounts();
    Account getAccountByUsername(String username);

    Status getAccountStatus(long accountId);

    boolean setAccountStatus(Status status, long accountId);

    boolean createAccount(Account newAccount);

    boolean deleteAccount(long accountId);

    boolean updateAccount(Account account);

    Account getAccountById(long accountId);

    List<Account> getAccountBy(String email); // recover account by email

    Account getAccountBy(String username, String password);
}
