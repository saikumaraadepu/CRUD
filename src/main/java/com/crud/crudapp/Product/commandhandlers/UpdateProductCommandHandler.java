package com.crud.crudapp.Product.commandhandlers;

import com.crud.crudapp.Command;
import com.crud.crudapp.Exceptions.ProductNotFoundException;
import com.crud.crudapp.Product.Model.Product;
import com.crud.crudapp.Product.Model.UpdateProductCommand;
import com.crud.crudapp.Product.ProductRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, Product> {

	private final ProductRepository productRepository;
	public UpdateProductCommandHandler(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	@CachePut(value = "productCache", key = "#command.getId()")
	public ResponseEntity<Product> execute(UpdateProductCommand command) {
		Optional<Product> optionalProduct = productRepository.findById(command.getId());
		if (optionalProduct.isEmpty()) {
			throw new ProductNotFoundException();
		}
		Product product = command.getProduct();
		product.setId(command.getId());
		return ResponseEntity.ok(productRepository.save(product));
	}
}
