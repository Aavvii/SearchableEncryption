package repos.interfaces;

import models.User;

public interface IUserMapper {

    boolean createNew(User user);
    boolean deleteUser(long userId);
    boolean updateUser(User user, long userId);

    User getUserById(long id);
    User getUserBy(String email); // recover account by email
    User getUserBy(String username, String password);
}
