package repos;

import models.Account;
import models.Message;
import models.User;
import repos.interfaces.IUserMapper;

import javax.transaction.Transactional;

/**
 * Mapper which will override the methods from the superclass.
 * Though this design pattern, Data Mapper, we avoid code duplication.
 */
public class UserMapper extends DataMapper<User, Long> implements IUserMapper {

    public UserMapper(){
        super(User.class);
    }

    @Override
    @Transactional
    public boolean createNew(User user) {
        try{
            this.create(user);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteUser(long userId) {
        try{
            User user = findById(userId);
            if (user != null) {
                this.remove(user);
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
    public boolean updateUser(User user, long userId)
    {
        try{
            User userFound = findById(userId);
            if (userFound != null) {
                userFound = user;
                this.update(userFound);
                return true;
            }
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public User getUserById(long id) {
        return this.findById(id);
    }

    @Override
    public User getUserBy(String email) {
        String qlString = "select e from " + entityClass.getSimpleName() + " e WHERE e.email = " + email;
        return (User) entityManager.createQuery(qlString).getSingleResult();
    }

    @Override
    public User getUserBy(String username, String password) {
        String qlString = "select e from " + entityClass.getSimpleName() + " e WHERE e.username = " + username + " AND e.password = " + password;
        return (User) entityManager.createQuery(qlString).getSingleResult();
    }
}
