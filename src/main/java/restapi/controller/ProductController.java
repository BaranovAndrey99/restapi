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
@RequestMapping("/product")
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
    @GetMapping("/all")
    public ResponseEntity<ResponseEntityDto> getAllProducts(){
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    /**
     * Method for getting product by id.
     * @return - response entity with searchable product.
     */
    @ApiOperation(value = "Product search by identifier")
    @GetMapping("/byId/{id}")
    public ResponseEntity<ResponseEntityDto> getProductById(@PathVariable Long id) throws ProductNotExistsException {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }

    @GetMapping("/byTypeWithPrice")
    public ResponseEntity<ResponseEntityDto> getAllProductsByTypeWithPrice(@RequestParam String type,
                                                                           @RequestParam Long minPrice,
                                                                           @RequestParam Long maxPrice){
        return new ResponseEntity<>(productService.findAllProductsByTypeWithPrice(type, minPrice, maxPrice), HttpStatus.OK);
    }

    /**
     * Method for creating of product.
     * @param productDtoList - list of request entities of POST request.
     */
    @ApiOperation(value = "Product creation")
    @PostMapping("/create")
    public ResponseEntity<ResponseEntityDto> createProduct(@Valid @RequestBody List<ProductDto> productDtoList) throws ProductAlreadyExistsException {
        return new ResponseEntity<>(productService.createProduct(productDtoList), HttpStatus.OK);
    }

    /**
     * Method for updating of product.
     * Throws exception if not found object
     * @param productDtoList - list of request entities of PUT request.
     */
    @PutMapping("/update")
    @ApiOperation(value = "Product update by identifier with description of new parameters")
    public ResponseEntity<ResponseEntityDto> updateProduct(@Valid @RequestBody List<ProductDto> productDtoList) throws ProductAlreadyExistsException, ProductNotExistsException {
        return new ResponseEntity<>(productService.updateProduct(productDtoList), HttpStatus.OK);
    }

    /**
     * Method for deleting of product by identifier.
     * @param id - identifier of deletable product.
     * @return - response entity of DELETE request.
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Product removal")
    public ResponseEntity<ResponseEntityDto> deleteProduct(@PathVariable Long id) throws ProductNotExistsException {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}