package com.springbootweb.api;

import com.springbootweb.dto.CategoryDTO;
import com.springbootweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping(value = "/category")
    public void deleteCategory(@RequestBody long[] ids) {
        categoryService.delete(ids);
    }
}
