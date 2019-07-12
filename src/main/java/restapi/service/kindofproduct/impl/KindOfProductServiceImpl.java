package restapi.service.kindofproduct.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.dto.KindOfProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.entity.KindOfProduct;
import restapi.exception.kindofproduct.KindOfProductAlreadyExistsException;
import restapi.exception.kindofproduct.KindOfProductNotExistsException;
import restapi.repository.KindOfProductRepository;
import restapi.service.kindofproduct.KindOfProductCheckingService;
import restapi.service.kindofproduct.KindOfProductDtoService;
import restapi.service.kindofproduct.KindOfProductService;

@Service
public class KindOfProductServiceImpl implements KindOfProductService {

    private final KindOfProductDtoService kindOfProductDtoService;
    private final KindOfProductRepository kindOfProductRepository;
    private final KindOfProductCheckingService kindOfProductCheckingService;

    @Autowired
    public KindOfProductServiceImpl(KindOfProductDtoService kindOfProductDtoService,
                                    KindOfProductRepository kindOfProductRepository,
                                    KindOfProductCheckingService kindOfProductCheckingService) {
        this.kindOfProductDtoService = kindOfProductDtoService;
        this.kindOfProductRepository = kindOfProductRepository;
        this.kindOfProductCheckingService = kindOfProductCheckingService;
    }

    /**
     * Create kind of product;
     * @param kindOfProductDto - request body.
     * @return - response entity for POST request.
     */
    @Override
    public ResponseEntityDto createKindOfProduct(KindOfProductDto kindOfProductDto) throws KindOfProductAlreadyExistsException{
        if (kindOfProductCheckingService.isKindOfProductExistsByNameAndType(kindOfProductDto.getName(), kindOfProductDto.getType())) {
            throw new KindOfProductAlreadyExistsException();
        }
        KindOfProduct kindOfProduct = kindOfProductDtoService.toKindOfProductEntity(kindOfProductDto);
        kindOfProductRepository.save(kindOfProduct);
        return new ResponseEntityDto<>("Kind of product was successfully created", null);
    }

    /**
     * Delete kind of product;
     * @param id - identifier of deletable kind of product.
     * @return - response entity for POST request.
     */
    @Override
    public ResponseEntityDto deleteKindOfProduct(Long id) throws KindOfProductNotExistsException {
        if(!kindOfProductCheckingService.isKindOfProductExistsById(id)){
            throw new KindOfProductNotExistsException();
        }
        kindOfProductRepository.delete(kindOfProductRepository.findKindOfProductById(id));
        return new ResponseEntityDto<>("Kind of product was successfully deleted", null);
    }
}
