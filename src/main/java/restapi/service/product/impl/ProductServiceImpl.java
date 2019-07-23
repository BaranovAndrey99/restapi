package restapi.service.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.dto.ProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.entity.Product;
import restapi.exception.kindofproduct.KindOfProductNotExistsException;
import restapi.exception.product.ProductAlreadyExistsException;
import restapi.exception.product.ProductNotExistsException;
import restapi.repository.ProductRepository;
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

    @Autowired
    public ProductServiceImpl(ProductCheckingService productCheckingService,
                              KindOfProductCheckingService kindOfProductCheckingService,
                              ProductDtoService productDtoService,
                              ProductRepository productRepository){
        this.productCheckingService = productCheckingService;
        this.kindOfProductCheckingService = kindOfProductCheckingService;
        this.productRepository = productRepository;
        this.productDtoService = productDtoService;
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
    public ResponseEntityDto<Product> findProductById(Long id) throws ProductNotExistsException{
        if(!productCheckingService.isProductExistsById(id)){
            throw new ProductNotExistsException();
        }
        return new ResponseEntityDto<>("Product was found", productRepository.findProductById(id));
    }

    /**
     * Method for creating of product.
     * @param productDto - entity with body of POST request.
     */
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
     * @param productDto - entity with body of PUT request.
     */
    @Override
    public ResponseEntityDto updateProduct(ProductDto productDto) throws ProductNotExistsException, ProductAlreadyExistsException {
        if(!kindOfProductCheckingService.isKindOfProductExistsByNameAndType(productDto.getName(), productDto.getType())){
            throw new KindOfProductNotExistsException();
        }
        Product product = productDtoService.toProductEntity(productDto);
        long putRequestBodyId = product.getId();
        Product productToUpdate = productRepository.findProductById(putRequestBodyId);
        if(productToUpdate == null){
            throw new ProductNotExistsException();
        }
        productToUpdate.setName(product.getName());
        productToUpdate.setType(product.getType());
        if(productCheckingService.isProductExistsByNameAndType(productToUpdate)){
            throw new ProductAlreadyExistsException();
        }
        productRepository.save(productToUpdate);
        return new ResponseEntityDto<>("Product was successfully updated", productToUpdate);
    }

    /**
     * Method for deleting of product by identifier.
     * @param id - identifier of deletable product.
     * @return - entity with body of DELETE request.
     */
    @Override
    public ResponseEntityDto deleteProduct(Long id) throws ProductNotExistsException{
        if(!productCheckingService.isProductExistsById(id)){
            throw new ProductNotExistsException();
        }
        Product product = productRepository.findProductById(id);
        productRepository.delete(product);
        return new ResponseEntityDto<>("Product was successfully deleted", product);
    }
}