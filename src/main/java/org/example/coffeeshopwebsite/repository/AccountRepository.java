package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
