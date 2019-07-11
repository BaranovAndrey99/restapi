package restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.dto.ProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.entity.Product;
import restapi.exception.NoSuchProductException;
import restapi.exception.ProductAlreadyExistsException;
import restapi.repository.ProductRepository;
import restapi.service.DataCheckingService;
import restapi.service.DtoService;
import restapi.service.ProductService;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    /**
     * Constructor-based DI for dataCheckingService and productRepository.
     */
    private DataCheckingService dataCheckingService;
    private DtoService dtoService;
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(DataCheckingService dataCheckingService,
                              DtoService dtoService,
                              ProductRepository productRepository){
        this.dataCheckingService = dataCheckingService;
        this.productRepository = productRepository;
        this.dtoService = dtoService;
    }


    /**
     * Method, which lists all products in JSON
     * @return - entity with list of products.
     */
    @Override
    public ResponseEntityDto<List<Product>> findAllProducts(){
        return new ResponseEntityDto<>("List of all products.", productRepository.findAll());
    }

    /**
     * Method for getting product by id.
     * @return - entity with searchable product.
     */
    @Override
    public ResponseEntityDto<Product> findProductById(Long id) {
        /*
         * Checking for existence of same object.
         */
        if(!dataCheckingService.isProductExistsById(id)){
            throw new NoSuchProductException();
        }
        return new ResponseEntityDto<>("Product was found", productRepository.findProductById(id));
    }

    /**
     * Method for creating of product.
     * @param productDto - entity with body of POST request.
     */
    @Override
    public ResponseEntityDto createProduct(ProductDto productDto) throws ConstraintViolationException {
        Product product = dtoService.toEntity(productDto);
        if(dataCheckingService.isProductExistsByNameAndType(product)){
            throw new ProductAlreadyExistsException();
        }
        productRepository.save(product);
        return new ResponseEntityDto<>("Product was successfully created", null);

    }

    /**
     * Method for updating of product.
     * Throws exception if not found object
     * @param productDto - entity with body of PUT request.
     */
    @Override
    public ResponseEntityDto updateProduct(ProductDto productDto){
        /*
         * Convert DTO to Entity
         */
        Product product = dtoService.toEntity(productDto);

        long putRequestBodyId = product.getId();

        Product productToUpdate = productRepository.findProductById(putRequestBodyId);
        if(productToUpdate == null){
            throw new NoSuchProductException();
        }
        productToUpdate.setName(product.getName());
        productToUpdate.setType(product.getType());
        /*
         * Checking for existence of same object.
         */
        if(dataCheckingService.isProductExistsByNameAndType(productToUpdate)){
            throw new ProductAlreadyExistsException();
        }
        productRepository.save(productToUpdate);
        return new ResponseEntityDto<>("Product was successfully updated", null);
    }

    /**
     * Method for deleting of product by identifier.
     * @param id - identifier of deletable product.
     * @return - entity with body of DELETE request.
     */
    @Override
    public ResponseEntityDto deleteProduct(Long id){
        /*
         * Checking for existence of same object.
         */
        if(!dataCheckingService.isProductExistsById(id)){
            throw new NoSuchProductException();
        }
        productRepository.delete(productRepository.findProductById(id));
        return new ResponseEntityDto<>("Product was successfully deleted", null);
    }
}
