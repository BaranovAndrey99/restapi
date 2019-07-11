package restapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restapi.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();

    Product findProductById(Long id);
    boolean existsProductById(Long id);

    List<Product> findProductByNameAndType(String name, String type);
    boolean existsProductByNameAndType(String name, String type);

}
