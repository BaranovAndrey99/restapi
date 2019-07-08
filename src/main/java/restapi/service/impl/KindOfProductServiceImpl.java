package restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import restapi.dto.KindOfProductDto;
import restapi.dto.ProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.entity.KindOfProduct;
import restapi.entity.Product;
import restapi.repository.KindOfProductRepository;
import restapi.service.KindOfProductDtoService;
import restapi.service.KindOfProductService;

@Service
public class KindOfProductServiceImpl implements KindOfProductService {
    private KindOfProductDtoService kindOfProductDtoService;
    private KindOfProductRepository kindOfProductRepository;

    @Autowired
    public KindOfProductServiceImpl(KindOfProductDtoService kindOfProductDtoService, KindOfProductRepository kindOfProductRepository) {
        this.kindOfProductDtoService = kindOfProductDtoService;
        this.kindOfProductRepository = kindOfProductRepository;
    }

    /**
     * Create kind of product;
     * @param kindOfProductDto - request body.
     * @return - response entity.
     */
    @Override
    public ResponseEntity<ResponseEntityDto> createKindOfProduct(KindOfProductDto kindOfProductDto){
        KindOfProduct kindOfProduct = kindOfProductDtoService.toKindOfProductEntity(kindOfProductDto);
        kindOfProductRepository.save(kindOfProduct);
        return new ResponseEntity<>(new ResponseEntityDto("Kind of product was successfully created", null), HttpStatus.OK);
    }

    /**
     * Checking of existence of kind of product. Used in productService.
     * @param productDto - response for creating of product.
     * @return - boolean.
     */
    @Override
    public boolean checkExistenceOfKindOfProduct(ProductDto productDto){
        return kindOfProductRepository.findKindOfProductByNameAndType(productDto.getName(), productDto.getType()) != null;
    }


}
