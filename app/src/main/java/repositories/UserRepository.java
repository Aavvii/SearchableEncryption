package repositories;

import models.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<Account, UUID> {

    Account findUserById(UUID uuid);
}
