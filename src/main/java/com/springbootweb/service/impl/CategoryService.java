package com.springbootweb.service.impl;

import com.springbootweb.converter.CategoryConverter;
import com.springbootweb.dto.CategoryDTO;
import com.springbootweb.entity.CategoryEntity;
import com.springbootweb.repository.CategoryRepository;
import com.springbootweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        if (categoryDTO.getId() != null) {
            CategoryEntity oldCategory = categoryRepository.findOne(categoryDTO.getId());
            categoryEntity = categoryConverter.toEntity(oldCategory, categoryDTO);
        } else {
            categoryEntity = categoryConverter.toEntity(categoryDTO);
        }
        return categoryConverter.toDTO(categoryRepository.save(categoryEntity));
    }
}
