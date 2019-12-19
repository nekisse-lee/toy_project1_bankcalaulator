package com.nekisse.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DepositHistoryRepository extends JpaRepository<DepositHistory,Long> {
    @Query(value ="select * from DEPOSIT_HISTORY where depositor like (%:name%)" ,nativeQuery = true)
    List<DepositHistory> findByDepositor(@Param("name") String name);
}
