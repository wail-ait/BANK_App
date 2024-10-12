package com.wailah.bank.repositories;

import com.wailah.bank.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)
public interface UsersRepository extends JpaRepository<Users,Integer> {
}
