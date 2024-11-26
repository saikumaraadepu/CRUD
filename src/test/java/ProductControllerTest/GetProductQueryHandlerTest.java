package ProductControllerTest;

import com.crud.crudapp.CRUDApplication;
import com.crud.crudapp.Exceptions.ProductNotFoundException;
import com.crud.crudapp.Product.Model.Product;
import com.crud.crudapp.Product.Model.ProductDTO;
import com.crud.crudapp.Product.ProductRepository;
import com.crud.crudapp.Product.queryhandlers.GetProductQueryHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CRUDApplication.class)
public class GetProductQueryHandlerTest {

	@InjectMocks
	private GetProductQueryHandler getProductQueryHandler;

	@Mock
	private ProductRepository productRepository;

	@Test
	public void getProductQueryHandler_validId_returnsProductDTO() {
		Product product = new Product();
		product.setId(1);
		product.setName("Test");
		product.setDescription("Test");
		product.setPrice(1.0);
		product.setQuantity(1);
		ProductDTO expectedDTO = new ProductDTO(product);
		when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
		ResponseEntity<ProductDTO> actualResponse = getProductQueryHandler.execute(product.getId());
		assertEquals(expectedDTO, actualResponse.getBody());
		assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
	}

	@Test
	public void getProductQueryHandler_inValidId_throwsProductNotFoundException() {
		when(productRepository.findById(1)).thenReturn(Optional.empty());
		ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> getProductQueryHandler.execute(1));
		assertEquals("Product Not Found", exception.getSimpleResponse().getMessage());
	}
}
