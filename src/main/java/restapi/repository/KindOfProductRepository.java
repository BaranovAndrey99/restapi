package restapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restapi.entity.KindOfProduct;

@Repository
public interface KindOfProductRepository extends CrudRepository<KindOfProduct, Long> {
    KindOfProduct findKindOfProductByNameAndType(String name, String type);
    boolean existsKindOfProductByNameAndType(String name, String type);

    KindOfProduct findKindOfProductById(Long id);
    boolean existsKindOfProductById(Long id);

}
