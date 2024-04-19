package org.thanhngo.retailapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thanhngo.retailapp.models.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
