package restapi.service;

import restapi.entity.Product;
import restapi.repository.ProductRepository;

public interface DataCheckingService {
    boolean checkExistenceOfProduct(ProductRepository productRepository, Product product);
    boolean checkExistenceOfProductById(ProductRepository productRepository, long id);
}
