package restapi.service.kindofproduct;

import restapi.dto.KindOfProductDto;
import restapi.dto.ResponseEntityDto;
import restapi.exception.kindofproduct.KindOfProductAlreadyExistsException;
import restapi.exception.kindofproduct.KindOfProductNotExistsException;

public interface KindOfProductService {
    ResponseEntityDto createKindOfProduct(KindOfProductDto kindOfProductDto) throws KindOfProductAlreadyExistsException;
    ResponseEntityDto deleteKindOfProduct(Long id) throws KindOfProductNotExistsException;
}
