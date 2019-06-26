package restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.dto.Product;
import restapi.exception.NoSuchProductException;
import restapi.exception.ProductAlreadyExistsException;
import restapi.exception.ValidationFailedException;
import restapi.repository.ProductRepository;

import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService {

    /**
     * Validator class(bad practice).
     */
    @Autowired
    private final ValidationService validationService = new ValidationService();

    /**
     * Fake repository(handmade).
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * Method for list of all products.
     * @return - ArrayList
     */
    public ArrayList<Product> getAllProducts(){
        return productRepository.findAll();
    }

    /**
     * Method, which gets product by id.
     * @return - searchable product.
     */
    public Product getProductById(long id){
        return productRepository.findProductById(id);
    }

    /**
     * Method, which create new product.
     * First step - creation of product.
     * Second step - addition in repository"
     * Third step - object return.
     * @param product - body of POST request.
     */
    public void createProduct(Product product){
        /*
         * Checking of input data.
         */
        if(!validationService.checkExistenceOfName(product.getName()) || !validationService.checkExistenceOfType(product.getType())){
            throw new ValidationFailedException();
        }
        /*
         * Checking for existence of same object.
         */
        if(validationService.checkExistenceOfProduct(productRepository, product)){
            throw new ProductAlreadyExistsException();
        } else {
            productRepository.save(product);
        }
    }

    /**
     * Method for updating of product.
     * Throws exception if not found object
     * @param product - body of PUT request.
     */
    public void updateProduct(Product product){
        long putRequestBodyId = product.getId();
        /*
         * Checking of input data.
         */
        if(!validationService.checkExistenceOfName(product.getName()) || !validationService.checkExistenceOfType(product.getType())){
            throw new ValidationFailedException();
        }
        Product productToUpdate = productRepository.getProductById(putRequestBodyId);
        productToUpdate.setName(product.getName());
        productToUpdate.setType(product.getType());
        /*
         * Checking for existence of same object.
         */
        if(validationService.checkExistenceOfProduct(productRepository, productToUpdate)){
            throw new ProductAlreadyExistsException();
        } else {
            productRepository.save(productToUpdate);
        }
    }

    /**
     * Method, which delete product by identifier with special method equals, which compares objects by id.
     * Throws exception if not found object
     * First step - creation of object with requried id.
     * Second step - deleting of object.
     * Third step - return of list of all products for check.
     * @param id - identifier of deletable product.
     */
    public void deleteProduct(long id){
        /*
         * Checking for existence of same object.
         */
        if(validationService.checkExistenceOfProductById(productRepository, id)){
            productRepository.delete(productRepository.findProductById(id));
        } else {
            throw new NoSuchProductException();
        }
    }
}
