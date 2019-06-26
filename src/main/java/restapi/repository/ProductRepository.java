package restapi.repository;

import org.springframework.data.repository.CrudRepository;
import restapi.dto.Product;

import java.util.ArrayList;

public interface ProductRepository extends CrudRepository<Product, Long> {
    ArrayList<Product> findAll();
    Product findProductById(long id);
    ArrayList<Product> findProductByNameAndType(String name, String type);
    Product getProductById(long id);
}
