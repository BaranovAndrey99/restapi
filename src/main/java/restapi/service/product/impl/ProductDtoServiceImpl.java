package restapi.service.product.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.dto.ProductDto;
import restapi.entity.Product;
import restapi.service.product.ProductDtoService;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for translating List<entity> <-> List<dto>.
 */

@Service
public class ProductDtoServiceImpl implements ProductDtoService {

    private final ModelMapper modelMapper;

    @Autowired
    public ProductDtoServiceImpl(ModelMapper modelMapper) {
       this.modelMapper = modelMapper;
    }


    @Override
    public List<Product> toProductEntityList(List<ProductDto> productDtoList){
        List<Product> productList = new ArrayList<>();
        for(ProductDto productDto : productDtoList){
            productList.add(modelMapper.map(productDto, Product.class));
        }
        return productList;
    }

    @Override
    public List<ProductDto> toProductDtoList(List<Product> productList){
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product product : productList){
            productDtoList.add(modelMapper.map(product, ProductDto.class));
        }
        return productDtoList;
    }

}
