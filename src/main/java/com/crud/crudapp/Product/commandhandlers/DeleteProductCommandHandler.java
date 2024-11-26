package com.crud.crudapp.Product.commandhandlers;

import com.crud.crudapp.Command;
import com.crud.crudapp.Exceptions.ProductNotFoundException;
import com.crud.crudapp.Product.Model.Product;
import com.crud.crudapp.Product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductCommandHandler implements Command<Integer, Product> {

	private final ProductRepository productRepository;
	public DeleteProductCommandHandler(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<Product> execute(Integer id) {
		Optional<Product> productOptional = productRepository.findById(id);
		if (productOptional.isEmpty()) {
			throw new ProductNotFoundException();
		}
		productRepository.delete(productOptional.get());
		return ResponseEntity.ok().build();
	}
}
