package restapi.service.kindofproduct.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.exception.general.NullArgsException;
import restapi.repository.kindofproduct.KindOfProductRepository;
import restapi.service.kindofproduct.KindOfProductCheckingService;

@Service
public class KindOfProductCheckingServiceImpl implements KindOfProductCheckingService {

    private final KindOfProductRepository kindOfProductRepository;

    @Autowired
    public KindOfProductCheckingServiceImpl(KindOfProductRepository kindOfProductRepository){
        this.kindOfProductRepository = kindOfProductRepository;
    }

    /**
     * Checking of existence of kind of product. Used in productService.
     * @param name - name of kind of product.
     * @param type - type of kind of product.
     * @return - "true" if kind of product exists, "false" if not exists.
     */
    @Override
    public boolean isKindOfProductExistsByNameAndType(String name, String type) throws NullArgsException{
        if(name == null || type == null){
            throw  new NullArgsException();
        } else {
            return kindOfProductRepository.existsKindOfProductByNameAndType(name, type);
        }
    }

    /**
     * Checking of existence of kind of product. Used in productService.
     * @param id - id of kind of product.
     * @return - "true" if kind of product exists, "false" if not exists.
     */
    @Override
    public boolean isKindOfProductExistsById(Long id) {
        return kindOfProductRepository.existsKindOfProductById(id);
    }
}
