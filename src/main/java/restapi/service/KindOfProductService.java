package restapi.service;

import org.springframework.http.ResponseEntity;
import restapi.dto.KindOfProductDto;
import restapi.dto.ProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.entity.KindOfProduct;
import restapi.entity.Product;
import restapi.repository.KindOfProductRepository;

public interface KindOfProductService {
    ResponseEntity<ResponseEntityDto> createKindOfProduct(KindOfProductDto kindOfProductDto);
    boolean checkExistenceOfKindOfProduct(ProductDto productDto);
}
