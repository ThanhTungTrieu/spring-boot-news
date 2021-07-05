package com.springbootweb.converter;

import com.springbootweb.dto.CategoryDTO;
import com.springbootweb.entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDTO toDTO(CategoryEntity categoryEntity) {
        CategoryDTO result = modelMapper.map(categoryEntity, CategoryDTO.class);
        return result;
    }

    public CategoryEntity toEntity(CategoryDTO categoryDTO) {
        CategoryEntity result = modelMapper.map(categoryDTO, CategoryEntity.class);
        return result;
    }
}
