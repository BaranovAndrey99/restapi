package restapi.service;

import restapi.dto.ProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.exception.NoSuchProductException;
import restapi.exception.ProductAlreadyExistsException;

public interface ProductService {
    ResponseEntityDto findAllProducts();
    ResponseEntityDto findProductById(Long id) throws NoSuchProductException;
    ResponseEntityDto createProduct(ProductDto productDto) throws ProductAlreadyExistsException;
    ResponseEntityDto updateProduct(ProductDto productDto) throws NoSuchProductException, ProductAlreadyExistsException;
    ResponseEntityDto deleteProduct(Long id) throws NoSuchProductException;
}