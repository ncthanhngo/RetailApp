package org.thanhngo.retailapp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.thanhngo.retailapp.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);//product name exists or not

    @Override
    Page<Product> findAll(Pageable pageable);//Phan trang (trang 1: 1--10; 2: 11--20; 3: 21--30)
}
