package restapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restapi.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findProductById(long id);
    ArrayList<Product> findProductByNameAndType(String name, String type);
    Product getProductById(long id);
}
