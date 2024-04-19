package org.thanhngo.retailapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thanhngo.retailapp.dtos.CategoryDTO;
import org.thanhngo.retailapp.models.Category;
import org.thanhngo.retailapp.repositories.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements iCategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Category not found"));
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category categoryToUpdate = getCategoryById(categoryId);
        categoryToUpdate.setName(categoryDTO.getName());
        return categoryRepository.save(categoryToUpdate);

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
