package com.springbootweb.api;

import com.springbootweb.dto.CategoryDTO;
import com.springbootweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryAPI {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping(value = "/category")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @PutMapping(value = "/category")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO updatedCategoryDTO) {
        return categoryService.save(updatedCategoryDTO);
    }
}
