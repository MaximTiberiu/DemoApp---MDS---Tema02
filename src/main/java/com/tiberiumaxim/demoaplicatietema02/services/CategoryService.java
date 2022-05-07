package com.tiberiumaxim.demoaplicatietema02.services;

import com.tiberiumaxim.demoaplicatietema02.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
