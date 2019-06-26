package restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.dto.Product;
import restapi.exception.NoSuchProductException;
import restapi.exception.ProductAlreadyExistsException;
import restapi.exception.ValidationFailedException;
import restapi.repository.ImaginaryRepository;

import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService {

    /**
     * Validator class(bad practice).
     */
    @Autowired
    private final Validator validator = new Validator();

    /**
     * Fake repository(handmade).
     */
    @Autowired
    private final ImaginaryRepository imaginaryRepository = new ImaginaryRepository();

    /**
     * Method for list of all products.
     * @return - ArrayList
     */
    public ArrayList<Product> getAllProducts(){
        return imaginaryRepository.findAllProducts();
    }

    /**
     * Method, which gets product by id.
     * @return - searchable product.
     */
    public Product getProductById(long id){
        return imaginaryRepository.getProductList().stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchProductException::new);
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
        if(!validator.checkExistenceOfName(imaginaryRepository, product.getName()) || !validator.checkExistenceOfType(imaginaryRepository, product.getType())){
            throw new ValidationFailedException();
        }
        /*
         * Creating of object and addition id fake database;
         */
        product.setId(imaginaryRepository.getIdentifierCounter());
        /*
         * Checking for existence of same object.
         */
        if(validator.checkExistenceOfProduct(imaginaryRepository, product)){
            throw new ProductAlreadyExistsException();
        }
        imaginaryRepository.addProduct(product);
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
        if(!validator.checkExistenceOfName(imaginaryRepository, product.getName()) || !validator.checkExistenceOfType(imaginaryRepository, product.getType())){
            throw new ValidationFailedException();
        }
        imaginaryRepository.getProductList().stream()
                .filter(prd -> prd.getId() == putRequestBodyId)
                .findFirst()
                .orElseThrow(NoSuchProductException::new);
        product.setId(putRequestBodyId);
        imaginaryRepository.updProduct(product);
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
        imaginaryRepository.getProductList().stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchProductException::new);
        imaginaryRepository.delProduct(id);
    }
}
