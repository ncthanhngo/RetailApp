package org.thanhngo.retailapp.services;

import org.thanhngo.retailapp.dtos.CategoryDTO;
import org.thanhngo.retailapp.models.Category;

import java.util.List;

public interface iCategoryService {
    Category createCategory(CategoryDTO category);
    Category getCategoryById(Long id);
    Category updateCategory(Long categoryId,CategoryDTO category);
    List<Category> getAllCategories();
    void deleteCategory(Long id);
}
