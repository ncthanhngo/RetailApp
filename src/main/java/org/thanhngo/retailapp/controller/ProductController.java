package org.thanhngo.retailapp.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.thanhngo.retailapp.dtos.ProductDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @GetMapping("")
    public ResponseEntity<String> getAllProducts(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        return ResponseEntity.ok(String.format("All products page = %d limit = %d",page,limit));
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String productId) {
        return ResponseEntity.ok(String.format("Product with id = %s",productId));
    }
    //Upload object including image
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> insertProduct(
            @Valid @RequestBody ProductDTO productDTO,
            @RequestPart("file") MultipartFile file,        //upload images
            BindingResult result ) {
        if (result.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMessages);
        }
        //Check file != null
        //Check size of file
        if(file!=null) {
            if (file.getSize() > 10 * 1024 * 1024) { //size of file >10Mb
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                        .body("File is to large! Maximum size is 10Mb");
            }

            //Check contentType (type of file, is image or not)
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) { //contentType start with image
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                        .body("File must be an image");

            }
        }
        return ResponseEntity.ok("This is insertProduct : " + productDTO);
    }
    private String storeFile(MultipartFile file) throws IOException{
        String fileName = UUID.randomUUID().toString() + "_"+file.getOriginalFilename();
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProductById(@PathVariable Long id) {
        return ResponseEntity.ok("This is update Product with id = "+id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        return ResponseEntity.ok(String.format("Product with id = %d was deleted successfully! ",id));
    }
}
