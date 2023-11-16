package com.example.mintos.repository;

import com.example.mintos.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface that implements the Account Repository, with JPA CRUD methods.
 *
 * @author David Zingerman
 * @since 16/11/2023
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Method to search Accounts by the client ID.
     *
     * @author David Zingerman
     * @since 16/11/2023
     *
     * @param clientID
     * @return List<Account>
     */
    List<Account> findAccountsByClientID(Long clientID);
}

