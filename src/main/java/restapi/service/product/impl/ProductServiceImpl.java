package restapi.service.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restapi.dto.ProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.entity.Product;
import restapi.exception.general.EmptyRequestBodyException;
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
@Transactional
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
    @Override
    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.SUPPORTS,
            rollbackFor = Exception.class
    )
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
            propagation = Propagation.SUPPORTS,
            rollbackFor = Exception.class
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
            propagation = Propagation.SUPPORTS,
            rollbackFor = Exception.class
    )
    public ResponseEntityDto<List<Product>> findAllProductsByTypeWithPrice(String type, Long minPrice, Long maxPrice){
        return new ResponseEntityDto<>("List of products by type with price constraint",
                productRepositoryCriteriaExtension.findAllProductsByTypeWithPrice(type, minPrice, maxPrice));
    }

    /**
     * Method for creating of product.
     * @param productDtoList - list of entities with bodies of POST request.
     */
    @Transactional(
            isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Exception.class
    )
    @Override
    public ResponseEntityDto createProduct(List<ProductDto> productDtoList) throws ProductNotExistsException, ProductAlreadyExistsException {
        /*
         * If request body is empty - throws exception.
         */
        if(productDtoList.isEmpty()){
            throw new EmptyRequestBodyException();
        }
        List<Product> productList = productDtoService.toProductEntityList(productDtoList);
        for (Product product: productList) {
            if(!kindOfProductCheckingService.isKindOfProductExistsByNameAndType(product.getName(), product.getType())){
                throw new KindOfProductNotExistsException();
            }
            if(productCheckingService.isProductExistsByNameAndType(product)){
                throw new ProductAlreadyExistsException();
            }
        }

        productRepository.saveAll(productList);
        return new ResponseEntityDto<>("Product was successfully created", productList);

    }

    /**
     * Method for updating of product.
     * Throws exception if not found object
     * @param productDtoList - list of request entities of PUT request.
     */
    @Transactional(
            isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Exception.class
    )
    @Override
    public ResponseEntityDto updateProduct(List<ProductDto> productDtoList) throws ProductNotExistsException, ProductAlreadyExistsException {
        /*
         * If request body is empty - throws exception.
         */
        if(productDtoList.isEmpty()){
            throw new EmptyRequestBodyException();
        }
        List<Product> productList = productDtoService.toProductEntityList(productDtoList);
        for (Product product: productList) {
            /*
             * If kind of product not exists - throw exception.
             */
            if(!kindOfProductCheckingService.isKindOfProductExistsByNameAndType(product.getName(), product.getType())){
                throw new KindOfProductNotExistsException();
            }
            /*
             * Create product, which will be used as new object.
             */
            long putRequestBodyId = product.getId();
            productRepository.findById(putRequestBodyId).orElseThrow(ProductNotExistsException::new);
            /*
             * If product already exists - throw exception.
             */
            if(productCheckingService.isProductExistsByNameAndType(product)){
                throw new ProductAlreadyExistsException();
            }
        }
        productRepository.saveAll(productList);
        return new ResponseEntityDto<>("Product was successfully updated", productList);
    }

    /**
     * Method for deleting of product by identifier.
     * @param id - identifier of deletable product.
     * @return - entity with body of DELETE request.
     */
    @Transactional(
            isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRES_NEW,
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