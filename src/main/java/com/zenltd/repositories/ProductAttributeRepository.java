package com.zenltd.repositories;

import com.zenltd.entity.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {
    @Query(value = "SELECT t FROM ProductAttribute t where t.id = :id")
    ProductAttribute getProductAttributeById(Long id);
}