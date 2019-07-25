package restapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.dto.KindOfProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.exception.kindofproduct.KindOfProductAlreadyExistsException;
import restapi.exception.kindofproduct.KindOfProductNotExistsException;
import restapi.service.kindofproduct.KindOfProductService;

import javax.validation.Valid;

/**
 * RestController for handling of requests with kind of products.
 */

@RestController
@RequestMapping("/assortment")
@Api(value="rest-api", description="Operations with kinds of products")
public class KindOfProductController {
    private final KindOfProductService kindOfProductService;

    @Autowired
    public KindOfProductController(KindOfProductService kindOfProductService){
        this.kindOfProductService = kindOfProductService;
    }

    /**
     * Create new kind of product.
     * @param kindOfProductDto - request body.
     * @return - responseEntity.
     */
    @PostMapping
    @ApiOperation(value = "Kind of product creation")
    public ResponseEntity<ResponseEntityDto> createKindOfProduct(@Valid @RequestBody KindOfProductDto kindOfProductDto) throws KindOfProductAlreadyExistsException{
        return new ResponseEntity<>(kindOfProductService.createKindOfProduct(kindOfProductDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Kind of product removal")
    public ResponseEntity<ResponseEntityDto> deleteKindOfProduct(@PathVariable Long id) throws KindOfProductNotExistsException{
        return new ResponseEntity<>(kindOfProductService.deleteKindOfProduct(id), HttpStatus.OK);
    }
}
