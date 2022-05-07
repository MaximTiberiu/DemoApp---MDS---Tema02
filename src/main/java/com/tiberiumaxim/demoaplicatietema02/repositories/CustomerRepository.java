package com.tiberiumaxim.demoaplicatietema02.repositories;

import com.tiberiumaxim.demoaplicatietema02.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
