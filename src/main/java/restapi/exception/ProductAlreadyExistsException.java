package restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED, reason = "Product with same name and type is already exists.")
public class ProductAlreadyExistsException extends RuntimeException {
}
