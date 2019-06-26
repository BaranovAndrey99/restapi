package restapi.service;

import restapi.dto.Product;

import java.util.ArrayList;

public interface ProductService {
    ArrayList<Product> getAllProducts();
    Product getProductById(long id);
    void createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(long id);
}
