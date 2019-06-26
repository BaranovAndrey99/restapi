package restapi.validation;

import restapi.domain.Product;
import restapi.repository.ImaginaryRepository;

import java.util.Iterator;

/**
 * Class for validation of requests input data.
 */
public class Validator {
    /**
     * Check of existence of name of product.
     * @param imaginaryRepository - fake bd
     * @param name - name, which we would to create.
     * @return - boolean
     */
    public boolean checkExistenceOfName(ImaginaryRepository imaginaryRepository, String name){
        Iterator<String> iterator = imaginaryRepository.getProductNames().iterator();
        boolean result = false;
        while(iterator.hasNext()){
            if(iterator.next().equals(name)){
                result = true;
            }
        }
        return result;
    }
    /**
     * Check of existence of name of product.
     * @param imaginaryRepository - fake bd
     * @param type - type, which we would to create.
     * @return - boolean
     */
    public boolean checkExistenceOfType(ImaginaryRepository imaginaryRepository, String type){
        Iterator<String> iterator = imaginaryRepository.getProductTypes().iterator();
        boolean result = false;
        while(iterator.hasNext()){
            if(iterator.next().equals(type)){
                result = true;
            }
        }
        return result;
    }

    /**
     * Check of existence of same object in db.
     * @param imaginaryRepository - fake bd
     * @param product - product, which we would to create.
     * @return - boolean
     */
    public boolean checkExistenceOfProduct(ImaginaryRepository imaginaryRepository, Product product){
        Iterator<Product> iterator = imaginaryRepository.getProductList().iterator();
        boolean result = false;
        while(iterator.hasNext()){
            if(iterator.next().nameAndTypeEquals(product)){
                result = true;
            }
        }
        return result;
    }
}
