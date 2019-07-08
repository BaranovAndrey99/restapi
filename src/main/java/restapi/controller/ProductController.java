package restapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.dto.ProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.service.ProductService;

import javax.validation.Valid;

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
    private ProductService productService;

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
        return productService.getAllProducts();
    }

    /**
     * Method for getting product by id.
     * @return - response entity with searchable product.
     */
    @ApiOperation(value = "Product search by identifier")
    @GetMapping("{id}")
    public ResponseEntity<ResponseEntityDto> getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

    /**
     * Method for creating of product.
     * @param productDto - response entity of POST request.
     */
    @ApiOperation(value = "Product creation")
    @PostMapping
    public ResponseEntity<ResponseEntityDto> createProduct(@Valid @RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }

    /**
     * Method for updating of product.
     * Throws exception if not found object
     * @param productDto - response entity of PUT request.
     */
    @PutMapping
    @ApiOperation(value = "Product update by identifier with description of new parameters")
    public ResponseEntity<ResponseEntityDto> updateProduct(@Valid @RequestBody ProductDto productDto){
        return productService.updateProduct(productDto);
    }

    /**
     * Method for deleting of product by identifier.
     * @param id - identifier of deletable product.
     * @return - response entity of DELETE request.
     */
    @DeleteMapping("{id}")
    @ApiOperation(value = "Product removal")
    public ResponseEntity<ResponseEntityDto> deleteProduct(@PathVariable long id){
        return productService.deleteProduct(id);
    }
}
