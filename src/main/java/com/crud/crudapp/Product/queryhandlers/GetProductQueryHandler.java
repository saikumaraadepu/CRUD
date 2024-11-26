package com.crud.crudapp.Product.queryhandlers;

import com.crud.crudapp.Exceptions.ProductNotFoundException;
import com.crud.crudapp.Product.Model.Product;
import com.crud.crudapp.Product.Model.ProductDTO;
import com.crud.crudapp.Product.ProductRepository;
import com.crud.crudapp.Query;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetProductQueryHandler implements Query<Integer, ProductDTO> {

	private final ProductRepository productRepository;
	public GetProductQueryHandler(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	@Cacheable("productCache")
	public ResponseEntity<ProductDTO> execute(Integer id) {
		Product product = productRepository.findById(id).orElse(null);
		if (product == null) {
			throw new ProductNotFoundException();
		}
		ProductDTO productDTO = new ProductDTO(product);
		return ResponseEntity.ok(productDTO);
	}
}
