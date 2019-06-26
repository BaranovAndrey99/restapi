package restapi.service;

import org.springframework.stereotype.Service;
import restapi.dto.Product;
import restapi.repository.ProductRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

@Service
public class ValidationService {
    private final ArrayList<String> productNames = new ArrayList<>(Arrays.asList(
            "Молоко", "Кефир", "Масло",
            "Колбаса", "Свинина", "Сосиски",
            "Яблоко", "Груша", "Апельсин",
            "Огурец", "Томат", "Капуста")
    );
    private final ArrayList<String> productTypes = new ArrayList<>(Arrays.asList(
            "Молоко",
            "Мясо",
            "Фрукты",
            "Овощи")
    );

    /**
     * Check of existence of name of product.
     * @param name - name, which we would to create.
     * @return - boolean
     */
    public boolean checkExistenceOfName(String name){
        Iterator<String> iterator = productNames.iterator();
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
     * @param type - type, which we would to create.
     * @return - boolean
     */
    public boolean checkExistenceOfType(String type){
        Iterator<String> iterator = productTypes.iterator();
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
     * @param productRepository - fake bd
     * @param product - product, which we would to create.
     * @return - boolean
     */
    public boolean checkExistenceOfProduct(ProductRepository productRepository, Product product){
        if(productRepository.findProductByNameAndType(product.getName(), product.getType()).isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    public boolean checkExistenceOfProductById(ProductRepository productRepository, long id){
        if(productRepository.findProductById(id) == null){
            return false;
        } else {
            return true;
        }
    }
}
