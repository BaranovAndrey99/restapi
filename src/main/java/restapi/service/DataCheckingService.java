package restapi.service;

import restapi.entity.Product;

public interface DataCheckingService {
    boolean isProductExistsByNameAndType(Product product);
    boolean isProductExistsById(Long id);
}
