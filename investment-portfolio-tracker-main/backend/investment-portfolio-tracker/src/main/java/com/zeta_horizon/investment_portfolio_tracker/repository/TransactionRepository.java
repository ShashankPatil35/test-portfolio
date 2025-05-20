package com.zeta_horizon.investment_portfolio_tracker.repository;

import com.zeta_horizon.investment_portfolio_tracker.entity.Transaction;
import com.zeta_horizon.investment_portfolio_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    // list all the transactions of a particular user
    List<Transaction> findByUser(User user);

    // list all the transactions of a particular user in a sorted manner of transaction date
    List<Transaction> findByUserOrderByTxnDateDesc(User user);

}
