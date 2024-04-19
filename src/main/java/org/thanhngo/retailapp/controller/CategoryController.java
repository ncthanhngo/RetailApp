package org.thanhngo.retailapp.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.thanhngo.retailapp.dtos.CategoryDTO;
import org.thanhngo.retailapp.models.Category;
import org.thanhngo.retailapp.services.CategoryService;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
//@Validated
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMessages);
        }
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok("Insert: " + categoryDTO+ " successfully");
    }
    //show all categories
    @GetMapping("")
    // What if parameter is an object? => Data transfer object = Request object
    public ResponseEntity<List<Category>> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) throws IOException{
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategoryById(
            @PathVariable Long id,
            @RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok("The Category "+categoryDTO+" with id = "+id+" has been updated successfully");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("The Category with id = "+id+" has been deleted successfully");
    }
}
