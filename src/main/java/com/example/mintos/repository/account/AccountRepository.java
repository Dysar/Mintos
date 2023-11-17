package com.example.mintos.repository.account;

import com.example.mintos.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

   List<Account> findByClientIDEquals(Long clientID);

}