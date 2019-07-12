package restapi.service.product;

import restapi.dto.ProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.exception.product.ProductNotExistsException;
import restapi.exception.product.ProductAlreadyExistsException;

public interface ProductService {
    ResponseEntityDto findAllProducts();
    ResponseEntityDto findProductById(Long id) throws ProductNotExistsException;
    ResponseEntityDto createProduct(ProductDto productDto) throws ProductAlreadyExistsException;
    ResponseEntityDto updateProduct(ProductDto productDto) throws ProductNotExistsException, ProductAlreadyExistsException;
    ResponseEntityDto deleteProduct(Long id) throws ProductNotExistsException;
}