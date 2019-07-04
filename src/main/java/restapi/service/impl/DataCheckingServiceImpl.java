package restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.entity.Product;
import restapi.repository.ProductRepository;
import restapi.service.DataCheckingService;

@Service
public class DataCheckingServiceImpl implements DataCheckingService {

    @Autowired
    public DataCheckingServiceImpl(){}

    /**
     * Check of existence of same object in db by name and type.
     * @param productRepository - fake bd
     * @param product - product, which existence we would to check.
     * @return - boolean
     */
    @Override
    public boolean checkExistenceOfProduct(ProductRepository productRepository, Product product){
        if(productRepository.findProductByNameAndType(product.getName(), product.getType()).isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check of existence of same object in db by identifier.
     * @param productRepository - fake bd
     * @param id - id of product, which existence we would to check.
     * @return - boolean
     */
    @Override
    public boolean checkExistenceOfProductById(ProductRepository productRepository, long id){
        return !productRepository.findProductById(id).isEmpty();
    }

}
