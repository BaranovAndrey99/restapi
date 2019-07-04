package restapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.dto.ProductDto;
import restapi.entity.Product;
import restapi.service.DtoService;

@Service
public class DtoServiceImpl implements DtoService {
    /**
     * Constructor based DI for ModelMapper;
     */
    private ModelMapper modelMapper;

    @Autowired
    public DtoServiceImpl(ModelMapper modelMapper) {
       this.modelMapper = modelMapper;
    }

    @Override
    public Product toEntity(ProductDto productDto){
        return modelMapper.map(productDto, Product.class);
    }

    @Override
    public ProductDto toDto(Product product){
        return modelMapper.map(product, ProductDto.class);
    }
}
