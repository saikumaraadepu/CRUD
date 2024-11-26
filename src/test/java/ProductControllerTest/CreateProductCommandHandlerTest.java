package ProductControllerTest;

import com.crud.crudapp.CRUDApplication;
import com.crud.crudapp.Exceptions.ProductNotValidException;
import com.crud.crudapp.Product.Model.Product;
import com.crud.crudapp.Product.ProductRepository;
import com.crud.crudapp.Product.commandhandlers.CreateProductCommandHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = CRUDApplication.class)
public class CreateProductCommandHandlerTest {

	@InjectMocks
	private CreateProductCommandHandler createProductCommandHandler;

	@Mock
	private ProductRepository productRepository;

	@Test
	public void createProductCommandHandler_validProduct_returnsSuccess() {
		Product product = new Product();
		product.setId(1);
		product.setPrice(9.99);
		product.setName("Product Name");
		product.setDescription("Product Description");
		product.setQuantity(10);

		ResponseEntity<Product> response = createProductCommandHandler.execute(product);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void createProductCommandHandler_invalidPrice_throwsInvalidPriceException() {
		Product product = new Product();
		product.setId(1);
		product.setPrice(-9.99);
		product.setName("Product Name");
		product.setDescription("Product Description");
		product.setQuantity(10);

		ProductNotValidException exception = assertThrows(ProductNotValidException.class, () -> createProductCommandHandler.execute(product));
		assertEquals("Product price cannot be negative", exception.getSimpleResponse().getMessage());
	}
}
