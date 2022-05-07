package com.tiberiumaxim.demoaplicatietema02.api.v1.mapper;

import com.tiberiumaxim.demoaplicatietema02.api.v1.model.CategoryDTO;
import com.tiberiumaxim.demoaplicatietema02.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    //@Mapping(source = "id", target = "id")
    CategoryDTO categoryToCategoryDTO(Category category);
}
