package restapi.service.kindofproduct;

import restapi.exception.general.NullArgsException;

public interface KindOfProductCheckingService {
    boolean isKindOfProductExistsByNameAndType(String name, String type) throws NullArgsException;
    boolean isKindOfProductExistsById(Long id);
}
