package restapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import restapi.dto.ProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.entity.Product;
import restapi.repository.KindOfProductRepository;
import restapi.repository.ProductRepository;

public interface ProductService {
    ResponseEntity<ResponseEntityDto> getAllProducts();
    ResponseEntity<ResponseEntityDto> getProductById(long id);
    ResponseEntity<ResponseEntityDto> createProduct(ProductDto productDto);
    ResponseEntity<ResponseEntityDto> updateProduct(ProductDto productDto);
    ResponseEntity<ResponseEntityDto> deleteProduct(long id);
    boolean checkExistenceOfProduct(ProductRepository productRepository, Product product);
    boolean checkExistenceOfProductById(ProductRepository productRepository, long id);
}
