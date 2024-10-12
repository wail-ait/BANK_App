package com.wailah.bank.repositories;

import com.wailah.bank.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface TransactionsRepository extends JpaRepository<Transactions,Integer> {
}
