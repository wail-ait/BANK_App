package com.wailah.bank.repositories;

import com.wailah.bank.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)
public interface RolesRepository extends JpaRepository<Roles,Integer> {
}
