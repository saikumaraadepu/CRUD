package com.crud.crudapp.Product;

import com.crud.crudapp.Product.Model.Product;
import com.crud.crudapp.Product.Model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByName(String name);

	List<Product> findByDescriptionContaining(String description);

	@Query("SELECT p FROM Product p WHERE p.price < :maxPrice")
	List<Product> findProductsWithPriceLessThan(@Param("maxPrice") double maxPrice);

	@Query("SELECT new com.crud.crudapp.Product.Model.ProductDTO(p.name, p.description, p.price) FROM Product p")
	List<ProductDTO> getAllProductDTOs();
}
