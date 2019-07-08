package restapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.dto.KindOfProductDto;
import restapi.entity.KindOfProduct;
import restapi.service.KindOfProductDtoService;

/**
 * Service for translating entity <-> dto.
 */

@Service
public class KindOfProductDtoServiceImpl implements KindOfProductDtoService {
    /**
     * Constructor based DI for ModelMapper;
     */
    private ModelMapper modelMapper;

    @Autowired
    public KindOfProductDtoServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public KindOfProduct toKindOfProductEntity(KindOfProductDto kindOfProductDto){
        return modelMapper.map(kindOfProductDto, KindOfProduct.class);
    }

    @Override
    public KindOfProductDto toKindOfProductDto(KindOfProduct kindOfProduct){
        return modelMapper.map(kindOfProduct, KindOfProductDto.class);
    }
}
