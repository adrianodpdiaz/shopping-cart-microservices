package com.adpd.product.repository;

import com.adpd.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT MAX(p.id) FROM product p WHERE p.category = :category")
    Optional<Product> findMaxIdByCategory(@Param("category") String category);

}
