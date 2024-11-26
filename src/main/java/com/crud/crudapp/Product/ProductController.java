package com.crud.crudapp.Product;

import com.crud.crudapp.Product.Model.Product;
import com.crud.crudapp.Product.Model.ProductDTO;
import com.crud.crudapp.Product.Model.UpdateProductCommand;
import com.crud.crudapp.Product.commandhandlers.CreateProductCommandHandler;
import com.crud.crudapp.Product.commandhandlers.DeleteProductCommandHandler;
import com.crud.crudapp.Product.commandhandlers.UpdateProductCommandHandler;
import com.crud.crudapp.Product.queryhandlers.GetAllProductsQueryHandler;
import com.crud.crudapp.Product.queryhandlers.GetProductQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

	private final GetAllProductsQueryHandler getAllProductsQueryHandler;
	private final GetProductQueryHandler getProductQueryHandler;
	private final CreateProductCommandHandler createProductCommandHandler;
	private final UpdateProductCommandHandler updateProductCommandHandler;
	private final DeleteProductCommandHandler deleteProductCommandHandler;
	private final ProductRepository productRepository;

	public ProductController(GetAllProductsQueryHandler getAllProductsQueryHandler,
							 GetProductQueryHandler getProductQueryHandler,
							 CreateProductCommandHandler createProductCommandHandler,
							 UpdateProductCommandHandler updateProductCommandHandler,
							 DeleteProductCommandHandler deleteProductCommandHandler, ProductRepository productRepository) {
		this.getAllProductsQueryHandler = getAllProductsQueryHandler;
		this.getProductQueryHandler = getProductQueryHandler;
		this.createProductCommandHandler = createProductCommandHandler;
		this.updateProductCommandHandler = updateProductCommandHandler;
		this.deleteProductCommandHandler = deleteProductCommandHandler;
		this.productRepository = productRepository;
	}

	@GetMapping(path = "/test/{name}")
	public ResponseEntity<List<Product>> test(@PathVariable String name) {
		return ResponseEntity.ok(productRepository.findByName(name));
	}

	@GetMapping(path = "/search/{maxPrice}")
	public ResponseEntity<List<Product>> findProductByPrice(@PathVariable double maxPrice) {
		return ResponseEntity.ok(productRepository.findProductsWithPriceLessThan(maxPrice));
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		return  getAllProductsQueryHandler.execute(null);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
		return getProductQueryHandler.execute(id);
	}

	@GetMapping(path = "/search")
	public ResponseEntity<List<Product>> searchProduct(@RequestParam(value = "description") String description) {
		return ResponseEntity.ok(productRepository.findByDescriptionContaining(description));
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return createProductCommandHandler.execute(product);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
		UpdateProductCommand command = new UpdateProductCommand(id, product);
		return updateProductCommandHandler.execute(command);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
		return deleteProductCommandHandler.execute(id);
	}
}
