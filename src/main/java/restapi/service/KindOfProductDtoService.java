package restapi.service;

import restapi.dto.KindOfProductDto;
import restapi.entity.KindOfProduct;

public interface KindOfProductDtoService {
    KindOfProduct toKindOfProductEntity(KindOfProductDto kindOfProductDto);
    KindOfProductDto toKindOfProductDto(KindOfProduct kindOfProduct);
}
