package com.tiberiumaxim.demoaplicatietema02.repositories;

import com.tiberiumaxim.demoaplicatietema02.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
