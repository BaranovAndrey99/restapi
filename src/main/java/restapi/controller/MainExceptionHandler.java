package restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import restapi.dto.ResponseEntityDto;
import restapi.exception.NoSuchProductException;
import restapi.exception.ProductAlreadyExistsException;

/**
 * Class for exception handling.
 */
@ControllerAdvice
public class MainExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Product not find for GET, PUT, DELETE.
     * @return - responseEntity.
     */
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ResponseEntityDto> handleNoSuchProductException(){
        return new ResponseEntity<>(new ResponseEntityDto("Product not found", null),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Product already exists for POST.
     * @return - responseEntity.
     */
    @ExceptionHandler(ProductAlreadyExistsException.class)
    protected ResponseEntity<ResponseEntityDto> handleProductAlreadyExistsException(){
        return new ResponseEntity<>(new ResponseEntityDto("Product already exists", null),
                HttpStatus.BAD_REQUEST);
    }
}
