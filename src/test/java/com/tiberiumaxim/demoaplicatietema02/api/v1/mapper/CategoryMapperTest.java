package com.tiberiumaxim.demoaplicatietema02.api.v1.mapper;

import com.tiberiumaxim.demoaplicatietema02.api.v1.model.CategoryDTO;
import com.tiberiumaxim.demoaplicatietema02.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    private static final String NAME = "Drinks";
    private static final long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() throws Exception {

        // given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        // when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        // then
        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}