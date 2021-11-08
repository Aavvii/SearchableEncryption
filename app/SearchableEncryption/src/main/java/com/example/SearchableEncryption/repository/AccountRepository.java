package com.example.SearchableEncryption.repository;

import com.example.SearchableEncryption.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserNameAndPassword(String username, String password);
}
