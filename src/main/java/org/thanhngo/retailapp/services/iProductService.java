package org.thanhngo.retailapp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.thanhngo.retailapp.dtos.ProductDTO;
import org.thanhngo.retailapp.exception.DataNotFoundException;
import org.thanhngo.retailapp.models.Product;

@Service
public interface iProductService {
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException;
    Product getProductById(Long id) throws DataNotFoundException;
    Page<Product> getAllProducts(PageRequest   pageRequest);
    Product updateProductById(Long id, ProductDTO productDTO) throws DataNotFoundException;
    void deleteProductById(Long id);
    boolean existsByName(String name);
}
