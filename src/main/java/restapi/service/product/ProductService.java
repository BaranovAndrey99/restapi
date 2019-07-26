package restapi.service.product;

import restapi.dto.ProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.exception.product.ProductNotExistsException;
import restapi.exception.product.ProductAlreadyExistsException;

import java.util.List;

public interface ProductService {
    ResponseEntityDto findAllProducts();
    ResponseEntityDto findProductById(Long id) throws ProductNotExistsException;
    ResponseEntityDto findAllProductsByTypeWithPrice(String type, Long minPrice, Long maxPrice);
    ResponseEntityDto createProduct(List<ProductDto> productDtoList) throws ProductAlreadyExistsException;
    ResponseEntityDto updateProduct(List<ProductDto> productDto) throws ProductNotExistsException, ProductAlreadyExistsException;
    ResponseEntityDto deleteProduct(Long id) throws ProductNotExistsException;
}