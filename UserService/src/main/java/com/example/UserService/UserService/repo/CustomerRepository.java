package com.example.UserService.UserService.repo;

import com.example.UserService.UserService.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,String> {
}
