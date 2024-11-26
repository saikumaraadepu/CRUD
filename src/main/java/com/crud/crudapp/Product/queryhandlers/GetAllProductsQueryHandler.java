package com.crud.crudapp.Product.queryhandlers;

import com.crud.crudapp.Product.Model.ProductDTO;
import com.crud.crudapp.Product.ProductRepository;
import com.crud.crudapp.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsQueryHandler implements Query<Void, List<ProductDTO>>{

	private final ProductRepository productRepository;
	public GetAllProductsQueryHandler(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<List<ProductDTO>> execute(Void input) {
		List<ProductDTO> productDTOS = productRepository.getAllProductDTOs();
		return ResponseEntity.ok(productDTOS);
	}
}
