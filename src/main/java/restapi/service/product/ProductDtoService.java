package restapi.service.product;

import restapi.dto.ProductDto;
import restapi.entity.Product;

import java.util.List;

public interface ProductDtoService {
    List<Product> toProductEntityList(List<ProductDto> productDtoList);
    List<ProductDto> toProductDtoList(List<Product> productList);
}