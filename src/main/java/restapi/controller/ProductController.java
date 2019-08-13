package restapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.dto.ProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.exception.product.ProductAlreadyExistsException;
import restapi.exception.product.ProductNotExistsException;
import restapi.service.product.ProductService;

import javax.validation.Valid;
import java.util.List;

/**
 * RestController for requests for products.
 */
@RestController
@RequestMapping("/products")
@Api(value="rest-api", description="Operations with products")
public class ProductController {


    /**
     * Constructor-based DI for service for working with db.
     */
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    /**
     * Method, which lists all products in JSON
     * @return - response entity with list of products.
     */
    @ApiOperation(value = "Listing of all products in system")
    @GetMapping
    public ResponseEntity<ResponseEntityDto> getAllProducts(){
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    /**
     * Method for getting product by id.
     * @return - response entity with searchable product.
     */
    @ApiOperation(value = "Product search by identifier")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> getProductById(@PathVariable Long id) throws ProductNotExistsException {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<ResponseEntityDto> getAllProductsByTypeWithPrice(@RequestParam String type,
                                                                           @RequestParam Long minPrice,
                                                                           @RequestParam Long maxPrice){
        return new ResponseEntity<>(productService.findAllProductsByTypeWithPrice(type, minPrice, maxPrice), HttpStatus.OK);
    }

    /**
     * Method for creating of product.
     * @param productDto - request entity of POST request.
     */
    @ApiOperation(value = "Product creation")
    @PostMapping
    public ResponseEntity<ResponseEntityDto> createProduct(@Valid @RequestBody ProductDto productDto) throws ProductAlreadyExistsException {
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.OK);
    }

    /**
     * Method for updating of product.
     * Throws exception if not found object
     * @param productDto - request entity of PUT request.
     */
    @PutMapping
    @ApiOperation(value = "Product update by identifier with description of new parameters")
    public ResponseEntity<ResponseEntityDto> updateProduct(@Valid @RequestBody ProductDto productDto) throws ProductAlreadyExistsException, ProductNotExistsException {
        return new ResponseEntity<>(productService.updateProduct(productDto), HttpStatus.OK);
    }

    /**
     * Method for deleting of product by identifier.
     * @param id - identifier of deletable product.
     * @return - response entity of DELETE request.
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Product removal")
    public ResponseEntity<ResponseEntityDto> deleteProduct(@PathVariable Long id) throws ProductNotExistsException {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}