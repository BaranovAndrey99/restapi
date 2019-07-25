package restapi.service.product.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.dto.ProductDto;
import restapi.entity.Product;
import restapi.service.product.ProductDtoService;

/**
 * Service for translating entity <-> dto.
 */

@Service
public class ProductDtoServiceImpl implements ProductDtoService {

    private final ModelMapper modelMapper;

    @Autowired
    public ProductDtoServiceImpl(ModelMapper modelMapper) {
       this.modelMapper = modelMapper;
    }

    @Override
    public Product toProductEntity(ProductDto productDto){
        return modelMapper.map(productDto, Product.class);
    }

    @Override
    public ProductDto toProductDto(Product product){
        return modelMapper.map(product, ProductDto.class);
    }

}
