package restapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restapi.domain.PostRequestBody;
import restapi.domain.Product;
import restapi.exception.NoSuchProductException;
import restapi.exception.ProductAlreadyExistsException;
import restapi.exception.ValidationFailedException;
import restapi.repository.ImaginaryRepository;
import restapi.validation.Validator;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * RestController for requests for products.
 */
@RestController
@RequestMapping("/product")
@Api(value="rest-api", description="Operations with products")
public class ProductController {
    /**
     * Fake repository(handmade).
     */
    @Autowired
    private final ImaginaryRepository imaginaryRepository = new ImaginaryRepository();

    @Autowired
    private final Validator validator = new Validator();
    /**
     * Automatically increasing identifier
     */
    private AtomicLong identifierCounter = new AtomicLong();

    /**
     * Method, which lists all products in JSON
     * @return - list of products.
     */
    @ApiOperation(value = "Listing of all products in system")
    @GetMapping
    public ArrayList<Product> getAllProducts(){
        return imaginaryRepository.findAllProducts();
    }

    /**
     * Method, which gets product by id.
     * @return - searchable product.
     */
    @ApiOperation(value = "Product search by identifier")
    @GetMapping("{id}")
    public Product getProductById(@PathVariable long id) {
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
     * @param postRequestBody - body of request
     * @return - new Product.
     */
    @ApiOperation(value = "Product creation")
    @PostMapping
    public Product createProduct(@RequestBody PostRequestBody postRequestBody){
        /*
         * Checking of input data.
         */
        if(!validator.checkExistenceOfName(imaginaryRepository, postRequestBody.getName()) || !validator.checkExistenceOfType(imaginaryRepository, postRequestBody.getType())){
            throw new ValidationFailedException();
        }
        /*
         * Creating of object and addition id fake database;
         */
        Product product = new Product(identifierCounter.incrementAndGet(), postRequestBody.getName(), postRequestBody.getType());
        /*
         * Checking for existence of same object.
         */
        if(validator.checkExistenceOfProduct(imaginaryRepository, product)){
            throw new ProductAlreadyExistsException();
        }
        imaginaryRepository.addProduct(product);
        return product;
    }

    /**
     * Method, which delete product by identifier with special method equals, which compares objects by id.
     * Throws exception if not found object
     * First step - creation of object with requried id.
     * Second step - deleting of object.
     * Third step - return of list of all products for check.
     * @param id - identifier of deletable product.
     * @return - updated list.
     */
    @DeleteMapping("{id}")
    @ApiOperation(value = "Product removal")
    public ArrayList<Product> deleteProduct(@PathVariable long id){
        imaginaryRepository.getProductList().stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchProductException::new);
        imaginaryRepository.delProduct(id);
        return imaginaryRepository.findAllProducts();
    }

    /**
     * Method for updating of product.
     * Throws exception if not found object
     * @param id - identifier of updatable product.
     * @param newName - new Name of updatable product or old name.
     * @param newType - new Type of updatable product or old type.
     */
    @PutMapping("{id}")
    @ApiOperation(value = "Product update by identifier with description of new parameters")
    public void updateProduct(@PathVariable long id,
                              @RequestParam(value = "name") String newName,
                              @RequestParam(value = "type") String newType){
        imaginaryRepository.getProductList().stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchProductException::new);
        Product product = new Product(id, newName, newType);
        imaginaryRepository.updProduct(product);
    }
}
