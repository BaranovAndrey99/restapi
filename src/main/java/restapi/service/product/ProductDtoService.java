package restapi.service.product;

import restapi.dto.ProductDto;
import restapi.entity.Product;

public interface ProductDtoService {
    Product toProductEntity(ProductDto productDto);
    ProductDto toProductDto(Product product);
}