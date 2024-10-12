package com.wailah.bank.repositories;

import com.wailah.bank.entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AccountsRepository extends JpaRepository<Accounts,Integer> {
}
