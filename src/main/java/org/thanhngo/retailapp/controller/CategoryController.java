package org.thanhngo.retailapp.controller;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.thanhngo.retailapp.dtos.CategoryDTO;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/pcategories")
//@Validated
public class CategoryController {
    //show all categories
    @GetMapping("")
    // What if parameter is an object? => Data transfer object = Request object
    public ResponseEntity<String> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) throws IOException{
        return ResponseEntity.ok(String.format("All Categories page = %d limit = %d",page,limit));
    }
    @PostMapping("")
    public ResponseEntity<?> insertCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                       BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok("This is insertCategory: " + categoryDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok("This is update category");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok("This is delete category");
    }
}
