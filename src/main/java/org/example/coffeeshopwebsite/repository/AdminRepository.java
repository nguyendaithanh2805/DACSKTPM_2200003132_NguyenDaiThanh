package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
