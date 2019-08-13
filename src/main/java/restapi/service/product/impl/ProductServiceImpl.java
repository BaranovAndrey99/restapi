package restapi.service.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restapi.dto.ProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.entity.Product;
import restapi.exception.kindofproduct.KindOfProductNotExistsException;
import restapi.exception.product.ProductAlreadyExistsException;
import restapi.exception.product.ProductNotExistsException;
import restapi.repository.product.ProductRepository;
import restapi.repository.product.ProductRepositoryCriteriaExtension;
import restapi.service.kindofproduct.KindOfProductCheckingService;
import restapi.service.product.ProductCheckingService;
import restapi.service.product.ProductDtoService;
import restapi.service.product.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductCheckingService productCheckingService;
    private final KindOfProductCheckingService kindOfProductCheckingService;
    private final ProductDtoService productDtoService;
    private final ProductRepository productRepository;
    private final ProductRepositoryCriteriaExtension productRepositoryCriteriaExtension;

    @Autowired
    public ProductServiceImpl(ProductCheckingService productCheckingService,
                              KindOfProductCheckingService kindOfProductCheckingService,
                              ProductDtoService productDtoService,
                              ProductRepository productRepository,
                              ProductRepositoryCriteriaExtension productRepositoryCriteriaExtension){
        this.productCheckingService = productCheckingService;
        this.kindOfProductCheckingService = kindOfProductCheckingService;
        this.productRepository = productRepository;
        this.productDtoService = productDtoService;
        this.productRepositoryCriteriaExtension = productRepositoryCriteriaExtension;
    }

    /**
     * Method, which lists all products in JSON
     * @return - entity with list of products.
     */
    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            readOnly = true
    )
    @Override
    public ResponseEntityDto<List<Product>> findAllProducts(){
        return new ResponseEntityDto<>("List of all products.", productRepository.findAll());
    }


    /**
     * Method for getting product by id.
     * Method works with Optional<> and can check for null and throws exception.
     * @return - entity with searchable product.
     */
    @Override
    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            readOnly = true
    )
    public ResponseEntityDto<Product> findProductById(Long id) throws ProductNotExistsException{
        Product product = productRepository.findById(id).orElseThrow(ProductNotExistsException::new);
        return new ResponseEntityDto<>("Product was found", product);
    }

    /**
     * Method which can get product from repository with filtering by price: min prica, max price and interval.
     * @param type - searchable type.
     * @param minPrice - min price constraint.
     * @param maxPrice - max price constraint.
     * @return - entity with list of searchable products.
     */
    @Override
    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            readOnly = true
    )
    public ResponseEntityDto<List<Product>> findAllProductsByTypeWithPrice(String type, Long minPrice, Long maxPrice){
        return new ResponseEntityDto<>("List of products by type with price constraint",
                productRepositoryCriteriaExtension.findAllProductsByTypeWithPrice(type, minPrice, maxPrice));
    }

    /**
     * Method for creating of product.
     * @param productDto - entity with body of POST request.
     */
    @Transactional(
            isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class
    )
    @Override
    public ResponseEntityDto createProduct(ProductDto productDto) throws ProductNotExistsException, ProductAlreadyExistsException {
        if(!kindOfProductCheckingService.isKindOfProductExistsByNameAndType(productDto.getName(), productDto.getType())){
            throw new KindOfProductNotExistsException();
        }
        Product product = productDtoService.toProductEntity(productDto);
        if(productCheckingService.isProductExistsByNameAndType(product)){
            throw new ProductAlreadyExistsException();
        }
        productRepository.save(product);
        return new ResponseEntityDto<>("Product was successfully created", product);

    }

    /**
     * Method for updating of product.
     * Throws exception if not found object
     * @param productDto - request entity of PUT request.
     */
    @Transactional(
            isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class
    )
    @Override
    public ResponseEntityDto updateProduct(ProductDto productDto) throws ProductNotExistsException, ProductAlreadyExistsException {
        /*
         * If kind of product not exists - throw exception.
         */
        if(!kindOfProductCheckingService.isKindOfProductExistsByNameAndType(productDto.getName(), productDto.getType())){
            throw new KindOfProductNotExistsException();
        }
        /*
         * Create product, which will be used as new object.
         */
        long putRequestBodyId = productDto.getId();
        productRepository.findById(putRequestBodyId).orElseThrow(ProductNotExistsException::new);
        Product product = productDtoService.toProductEntity(productDto);
        /*
         * If product already exists - throw exception.
         */
        if(productCheckingService.isProductExistsByNameAndType(product)){
            throw new ProductAlreadyExistsException();
        }
        productRepository.save(product);
        return new ResponseEntityDto<>("Product was successfully updated", product);
    }


    /**
     * Method for deleting of product by identifier.
     * @param id - identifier of deletable product.
     * @return - entity with body of DELETE request.
     */
    @Transactional(
            isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class
    )
    @Override
    public ResponseEntityDto deleteProduct(Long id) throws ProductNotExistsException{
        if(!productCheckingService.isProductExistsById(id)){
            throw new ProductNotExistsException();
        }
        productRepository.deleteById(id);
        return new ResponseEntityDto<>("Product was successfully deleted", null);
    }
}