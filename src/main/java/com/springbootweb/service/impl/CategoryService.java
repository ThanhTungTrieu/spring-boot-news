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
    private CategoryConverter categoryConverter;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryConverter.toEntity(categoryDTO);
        return categoryConverter.toDTO(categoryRepository.save(categoryEntity));
    }

    @Override
    public void delete(long[] ids) {
        for (long id: ids) {
            categoryRepository.delete(id);
        }
    }
}
