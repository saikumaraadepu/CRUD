package com.crud.crudapp.Product.commandhandlers;

import com.crud.crudapp.Command;
import com.crud.crudapp.Exceptions.ProductNotValidException;
import com.crud.crudapp.Product.Model.Product;
import com.crud.crudapp.Product.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductCommandHandler implements Command<Product, Product> {

	private final ProductRepository productRepository;
	public CreateProductCommandHandler(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<Product> execute(Product product) {

		validateProduct(product);

		return ResponseEntity.ok(productRepository.save(product));
	}

	private void validateProduct(Product product) {

		if (StringUtils.isBlank(product.getName())){
			throw new ProductNotValidException("Product name cannot be empty");
		}

		if (StringUtils.isBlank(product.getDescription())){
			throw new ProductNotValidException("Product description cannot be empty");
		}

		if (product.getPrice() <= 0.0) {
			throw new ProductNotValidException("Product price cannot be negative");
		}

		if (product.getQuantity() <= 0) {
			throw new ProductNotValidException("Product quantity cannot be negative");
		}
	}
}
