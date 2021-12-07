package repos.interfaces;

import models.Account;
import models.Status;

import java.util.Date;

public interface IAccountMapper {

    int getAllAccounts();
    Status getAccountStatus(long accountId);
    boolean setAccountStatus(Status status, long accountId);
    boolean createAccount(Account newAccount);
    Date getAccountCreationDate(long accountId);
}
