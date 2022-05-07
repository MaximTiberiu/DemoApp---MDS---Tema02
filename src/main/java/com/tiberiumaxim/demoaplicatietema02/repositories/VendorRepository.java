package com.tiberiumaxim.demoaplicatietema02.repositories;

import com.tiberiumaxim.demoaplicatietema02.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
