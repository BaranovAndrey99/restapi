package restapi.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.dto.KindOfProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.service.KindOfProductService;

import javax.validation.Valid;

/**
 * RestController for handling of requests with kind of products.
 */

@RestController
@RequestMapping("/assortnment")
@Api(value="rest-api", description="Operations with kinds of products")
public class KindOfProductController {
    private KindOfProductService kindOfProductService;

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
    public ResponseEntity<ResponseEntityDto> createKindOfProduct(@Valid @RequestBody KindOfProductDto kindOfProductDto){
        return kindOfProductService.createKindOfProduct(kindOfProductDto);
    }
}
