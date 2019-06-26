package restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class for exception handling.
 */
@ControllerAdvice
public class MainExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Product not find for GET, PUT, DELETE.
     * @return - exception body.
     */
    @ExceptionHandler(NoSuchProductException.class)
    protected ResponseEntity<ExceptionResponseBody> handleNoSuchProductException(){
        return new ResponseEntity<>(new ExceptionResponseBody("Product not found."),
                HttpStatus.NOT_FOUND);
    }

    /**
     * Product already exists for POST.
     * @return - exception body.
     */
    @ExceptionHandler(ProductAlreadyExistsException.class)
    protected ResponseEntity<ExceptionResponseBody> handleProductAlreadyExistsException(){
        return new ResponseEntity<>(new ExceptionResponseBody("Product with same name and type is already exists."),
                HttpStatus.ALREADY_REPORTED);
    }

    /**
     * Incorrect entity in request.
     * @return - exception body.
     */
    @ExceptionHandler(ValidationFailedException.class)
    protected ResponseEntity<ExceptionResponseBody> handleValidationFailedException(){
        return new ResponseEntity<>(new ExceptionResponseBody("Invalid request(name or type does not exist)."),
                HttpStatus.BAD_REQUEST);
    }


}
