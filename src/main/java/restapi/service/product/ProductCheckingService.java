package restapi.service.product;

import restapi.entity.Product;
import restapi.exception.general.NullArgsException;

public interface ProductCheckingService {
    boolean isProductExistsByNameAndType(Product product) throws NullArgsException;
    boolean isProductExistsById(Long id);
}