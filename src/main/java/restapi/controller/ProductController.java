package restapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import restapi.dto.Product;
import restapi.service.ProductServiceImpl;
import restapi.transfer.New;
import restapi.transfer.UpdateName;
import restapi.transfer.UpdateType;

import java.util.ArrayList;

/**
 * RestController for requests for products.
 */
@RestController
@RequestMapping("/product")
@Api(value="rest-api", description="Operations with products")
public class ProductController {


    /**
     * service for working with db.
     */
    @Autowired
    private final ProductServiceImpl productService = new ProductServiceImpl();

    /**
     * Method, which lists all products in JSON
     * @return - list of products.
     */
    @ApiOperation(value = "Listing of all products in system")
    @GetMapping
    public ArrayList<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    /**
     * Method, which gets product by id.
     * @return - searchable product.
     */
    @ApiOperation(value = "Product search by identifier")
    @GetMapping("{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

    /**
     * Method, which create new product.
     * First step - creation of product.
     * Second step - addition in repository"
     * Third step - object return.
     * @param product - body of POST request.
     */
    @ApiOperation(value = "Product creation")
    @PostMapping
    public void createProduct(@Validated(New.class) @RequestBody Product product){
        productService.createProduct(product);
    }

    /**
     * Method for updating of product.
     * Throws exception if not found object
     * @param product - body of PUT request.
     */
    @PutMapping
    @ApiOperation(value = "Product update by identifier with description of new parameters")
    public void updateProduct(@Validated({UpdateName.class, UpdateType.class}) @RequestBody Product product){
        productService.updateProduct(product);
    }

    /**
     * Method, which delete product by identifier with special method equals, which compares objects by id.
     * Throws exception if not found object
     * First step - creation of object with requried id.
     * Second step - deleting of object.
     * Third step - return of list of all products for check.
     * @param id - identifier of deletable product.
     */
    @DeleteMapping("{id}")
    @ApiOperation(value = "Product removal")
    public void deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
    }
}
