package restapi.service.product;

import restapi.dto.ProductDto;
import restapi.entity.Product;

import java.util.List;

public interface ProductDtoService {
    Product toProductEntity(ProductDto productDto);
    ProductDto toProductDto(Product product);
}