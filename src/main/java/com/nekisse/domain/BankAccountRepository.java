package com.nekisse.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
    @Query(value ="select * from BANK_ACCOUNT where depositor like (:name%)" ,nativeQuery = true)
    List<BankAccount> findByDepositor(@Param("name") String name);
}
