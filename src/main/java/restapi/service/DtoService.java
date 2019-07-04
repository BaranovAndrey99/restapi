package restapi.service;

import restapi.dto.ProductDto;
import restapi.entity.Product;

public interface DtoService {
    Product toEntity(ProductDto productDto);
    ProductDto toDto(Product product);
}
