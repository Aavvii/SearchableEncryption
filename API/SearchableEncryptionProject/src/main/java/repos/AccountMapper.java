package repos;

import models.Account;
import models.Status;
import repos.interfaces.IAccountMapper;

import javax.transaction.Transactional;
import java.util.Date;

public class AccountMapper extends DataMapper<Account, Long> implements IAccountMapper {

    public AccountMapper(){
        super(Account.class);
    }


    @Override
    public int getAllAccounts() {
        return this.findAll().size();
    }

    @Override
    public Status getAccountStatus(long accountId) {
        String qlString = "select e from " + entityClass.getSimpleName() + " e WHERE e.id = " + accountId;
        Account result = (Account) entityManager.createQuery(qlString).getSingleResult();
        return result.getStatus();
    }

    @Override
    @Transactional
    public boolean setAccountStatus(Status status, long accountId) {
        try{
            Account accountFound = findById(accountId);
            if (accountFound != null) {
                accountFound.setStatus(status);
                this.update(accountFound);
                return true;
            }
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean createAccount(Account account) {
        try{
            this.create(account);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Date getAccountCreationDate(long accountId) {
        String qlString = "select e from " + entityClass.getSimpleName() + " e WHERE e.id = " + accountId;
        Account result = (Account) entityManager.createQuery(qlString).getSingleResult();
        return result.getCreationDate();
    }
}
