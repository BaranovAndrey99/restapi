package restapi.repository.product;

import restapi.entity.Product;

import java.util.List;

/**
 * Interface, which describes methods with Criteria Api.
 */
public interface ProductRepositoryCriteriaExtension {
    List<Product> findAllProductsByTypeWithPrice(String type, Long minPrice, Long maxPrice);
}
