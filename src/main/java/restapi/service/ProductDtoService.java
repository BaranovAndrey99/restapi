package restapi.service;

import restapi.dto.KindOfProductDto;
import restapi.dto.ProductDto;
import restapi.entity.KindOfProduct;
import restapi.entity.Product;

public interface ProductDtoService {
    Product toProductEntity(ProductDto productDto);
    ProductDto toProductDto(Product product);
}