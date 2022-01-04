package repos;

import models.Account;
import models.Message;
import models.Status;
import repos.interfaces.IAccountMapper;

import javax.management.Query;
import javax.transaction.Transactional;
import java.util.List;

public class AccountMapper extends DataMapper<Account, Long> implements IAccountMapper {

    public AccountMapper() {
        super(Account.class);
    }

    @Override
    public List<Account> getAllAccounts() {
        return this.findAll();
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
        try {
            Account accountFound = findById(accountId);
            if (accountFound != null) {
                accountFound.setStatus(status);
                this.update(accountFound);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean createAccount(Account account) {
        try {
            this.create(account);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteAccount(long accountId) {
        try {
            String qlString = "delete from Message m WHERE m.sender_id = " + accountId;
            entityManager.createQuery(qlString).executeUpdate();
            qlString = "delete from Message m WHERE m.receiver_id = " + accountId;
            entityManager.createQuery(qlString).executeUpdate();
            Account account = findById(accountId);
            if (account != null) {
                this.remove(account);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updateAccount(Account account) {
        try {
            Account accountFound = findById(account.getId());
            if (accountFound != null) {
                accountFound = account;
                this.update(accountFound);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Account getAccountById(long accountId) {
        return this.findById(accountId);
    }

    @Override
    public List<Account> getAccountBy(String email) {
        String qlString = "select e from " + entityClass.getSimpleName() + " e WHERE e.email = \'" + email + "\'";
        return entityManager.createQuery(qlString).getResultList();
    }

    @Override
    public Account getAccountBy(String username, String password) {
        String qlString = "select e from " + entityClass.getSimpleName() + " e WHERE e.username = \'" + username + "\' AND e.password = \'" + password + "\'";
        return (Account) entityManager.createQuery(qlString).getSingleResult();
    }
}
