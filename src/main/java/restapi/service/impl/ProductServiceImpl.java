package restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import restapi.dto.ProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.entity.Product;
import restapi.exception.ProductCheckingFailedException;
import restapi.exception.NoSuchProductException;
import restapi.exception.ProductAlreadyExistsException;
import restapi.repository.ProductRepository;
import restapi.service.KindOfProductService;
import restapi.service.ProductDtoService;
import restapi.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    /**
     * Constructor-based DI for dataCheckingService and productRepository.
     */
    private ProductDtoService productDtoService;
    private ProductRepository productRepository;
    private KindOfProductService kindOfProductService;

    @Autowired
    public ProductServiceImpl(ProductDtoService productDtoService,
                              ProductRepository productRepository,
                              KindOfProductService kindOfProductService){
        this.productRepository = productRepository;
        this.productDtoService = productDtoService;
        this.kindOfProductService = kindOfProductService;
    }


    /**
     * Method, which lists all products in JSON
     * @return - entity with list of products.
     */
    @Override
    public ResponseEntity<ResponseEntityDto> getAllProducts(){
        return new ResponseEntity<>(new ResponseEntityDto("List of all products.", productRepository.findAll()), HttpStatus.OK);
    }

    /**
     * Method for getting product by id.
     * @return - entity with searchable product.
     */
    @Override
    public ResponseEntity<ResponseEntityDto> getProductById(long id){
        /*
         * Checking for existence of same object.
         */
        if(checkExistenceOfProductById(productRepository, id)){
            return new ResponseEntity<>(new ResponseEntityDto("Product was found", productRepository.findProductById(id)), HttpStatus.OK);
        } else {
            throw new NoSuchProductException();
        }
    }

    /**
     * Method for creating of product.
     * @param productDto - entity with body of POST request.
     */
    @Override
    public ResponseEntity<ResponseEntityDto> createProduct(ProductDto productDto){
        if(kindOfProductService.checkExistenceOfKindOfProduct(productDto)) {
            Product product = productDtoService.toProductEntity(productDto);
            productRepository.save(product);
            return new ResponseEntity<>(new ResponseEntityDto("Product was successfully created", null), HttpStatus.OK);
        } else {
            throw new ProductCheckingFailedException();
        }
    }

    /**
     * Method for updating of product.
     * Throws exception if not found object
     * @param productDto - entity with body of PUT request.
     */
    @Override
    public ResponseEntity<ResponseEntityDto> updateProduct(ProductDto productDto){
        /*
         * Convert DTO to Entity
         */
        Product product = productDtoService.toProductEntity(productDto);

        long putRequestBodyId = product.getId();

        Product productToUpdate = productRepository.getProductById(putRequestBodyId);
        if(productToUpdate == null){
            throw new NoSuchProductException();
        }
        productToUpdate.setName(product.getName());
        productToUpdate.setType(product.getType());
        /*
         * Checking for existence of same object.
         */
        if(checkExistenceOfProduct(productRepository, productToUpdate)){
            throw new ProductAlreadyExistsException();
        } else {
            productRepository.save(productToUpdate);
            return new ResponseEntity<>(new ResponseEntityDto("Product was successfully updated", null), HttpStatus.OK);
        }
    }

    /**
     * Method for deleting of product by identifier.
     * @param id - identifier of deletable product.
     * @return - entity with body of DELETE request.
     */
    @Override
    public ResponseEntity<ResponseEntityDto> deleteProduct(long id){
        /*
         * Checking for existence of same object.
         */
        if(checkExistenceOfProductById(productRepository, id)){
            productRepository.delete(productRepository.findProductById(id).get(0));
            return new ResponseEntity<>(new ResponseEntityDto("Product was successfully deleted", null), HttpStatus.OK);
        } else {
            throw new NoSuchProductException();
        }
    }

    /**
     * Check of existence of same object in db by name and type.
     * @param productRepository - fake bd
     * @param product - product, which existence we would to check.
     * @return - boolean
     */
    @Override
    public boolean checkExistenceOfProduct(ProductRepository productRepository, Product product){
        if(productRepository.findProductByNameAndType(product.getName(), product.getType()).isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check of existence of same object in db by identifier.
     * @param productRepository - fake bd
     * @param id - id of product, which existence we would to check.
     * @return - boolean
     */
    @Override
    public boolean checkExistenceOfProductById(ProductRepository productRepository, long id){
        return !productRepository.findProductById(id).isEmpty();
    }
}
