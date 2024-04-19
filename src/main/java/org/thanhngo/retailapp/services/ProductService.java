package org.thanhngo.retailapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thanhngo.retailapp.dtos.ProductDTO;
import org.thanhngo.retailapp.dtos.ProductImageDTO;
import org.thanhngo.retailapp.exception.DataNotFoundException;
import org.thanhngo.retailapp.models.Category;
import org.thanhngo.retailapp.models.Product;
import org.thanhngo.retailapp.models.ProductImage;
import org.thanhngo.retailapp.repositories.CategoryRepository;
import org.thanhngo.retailapp.repositories.ProductRepository;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ProductService implements iProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException {
            Category existingCategory = categoryRepository.findById(productDTO.getCategoryid())
                    .orElseThrow(() ->
                            new DataNotFoundException("Can not find category with id: " + productDTO.getCategoryid()));

            Product product = Product.builder()
                    .name(productDTO.getName())
                    .description(productDTO.getDescription())
                    .price(productDTO.getPrice())
                    .thumbnail(productDTO.getThumbnail())
                    .category(existingCategory)
                    .build();
            return productRepository.save(product);

    }

    @Override
    public Product getProductById(Long id) throws DataNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can not find product with id: " + id));
    }

    @Override
    public Page<Product> getAllProducts(PageRequest pageRequest) {
        //get list of products by page and limit
        return productRepository.findAll(pageRequest);
    }

    @Override
    public Product updateProductById(Long id, ProductDTO productDTO) throws DataNotFoundException {
        Product existingProduct = getProductById(id);
        if(existingProduct!= null){
            //copy properties from productDTO to existingProduct
            //Could use ModelMapper
            Category existingCategory = categoryRepository.findById(productDTO.getCategoryid())
                    .orElseThrow(() ->
                            new DataNotFoundException("Can not find category with id: " + productDTO.getCategoryid()));
            existingProduct.setCategory(existingCategory);
            existingProduct.setName(productDTO.getName());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setThumbnail(productDTO.getThumbnail());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    private ProductImage createProductImage(Long productId,
                                            ProductImageDTO productImageDTO) throws DataNotFoundException {
        Product existingProduct = productRepository.findById(productImageDTO.getProductId())
                .orElseThrow(() ->
                        new DataNotFoundException("Can not find category with id: " + productImageDTO.getProductId()));

    }
}
