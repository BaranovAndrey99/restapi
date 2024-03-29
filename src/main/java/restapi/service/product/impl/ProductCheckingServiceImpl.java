package restapi.service.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.entity.Product;
import restapi.exception.general.NullArgsException;
import restapi.repository.product.ProductRepository;
import restapi.service.product.ProductCheckingService;

@Service
public class ProductCheckingServiceImpl implements ProductCheckingService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductCheckingServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    /**
     * Check of existence of same object in db by name and type.
     * @param product - product, which existence we would to check.
     * @return - "true" if product with same name and type exists, "false" if not exists.
     */
    @Override
    public boolean isProductExistsByNameAndType(Product product) throws NullArgsException{
        if(product.getName() == null || product.getType() == null){
            throw  new NullArgsException();
        } else {
            return productRepository.existsProductByNameAndType(product.getName(), product.getType());
        }
    }

    /**
     * Check of existence of same object in db by identifier.
     * @param id - id of product, which existence we would to check.
     * @return - "true" if product with same id exists, "false" if not exists.
     */
    @Override
    public boolean isProductExistsById(Long id){
        return productRepository.existsById(id);
    }

}