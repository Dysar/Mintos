package com.example.mintos.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

   List<Account> findByClientIDEquals(Long clientID);

}