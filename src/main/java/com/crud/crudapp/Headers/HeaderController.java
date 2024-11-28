package com.crud.crudapp.Headers;

import com.crud.crudapp.Product.Model.Product;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {

	@GetMapping(path = "/header", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Product> getProduct() {
		Product product = new Product();
		product.setName("Name");
		product.setId(1);
		product.setDescription("Description");
		return ResponseEntity.ok(product);
	}
}
