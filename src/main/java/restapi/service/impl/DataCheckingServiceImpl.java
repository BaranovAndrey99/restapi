package restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.entity.Product;
import restapi.exception.NullProductArgsException;
import restapi.repository.ProductRepository;
import restapi.service.DataCheckingService;

@Service
public class DataCheckingServiceImpl implements DataCheckingService {
    private ProductRepository productRepository;

    @Autowired
    public DataCheckingServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    /**
     * Check of existence of same object in db by name and type.
     * @param product - product, which existence we would to check.
     * @return - "true" if product with same name and type exists, "false" if not exists.
     */
    @Override
    public boolean isProductExistsByNameAndType(Product product){
        if(product.getName() == null || product.getType() == null){
            throw  new NullProductArgsException();
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
        return productRepository.existsProductById(id);
    }

}
