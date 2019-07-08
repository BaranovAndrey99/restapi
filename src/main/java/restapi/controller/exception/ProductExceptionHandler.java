package restapi.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import restapi.dto.ResponseEntityDto;
import restapi.exception.NoSuchProductException;
import restapi.exception.NullProductArgsException;
import restapi.exception.ProductAlreadyExistsException;
import restapi.exception.ProductCheckingFailedException;

/**
 * Class for exception handling.
 */
@ControllerAdvice
<<<<<<< HEAD:src/main/java/restapi/controller/MainExceptionHandler.java
public class MainExceptionHandler {
=======
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {
>>>>>>> Mistakes Correction 2.1.:src/main/java/restapi/controller/exception/ProductExceptionHandler.java

    /**
     * Product not find for GET, PUT, DELETE.
     * @return - responseEntity for product not found exception.
     */
    @ExceptionHandler(NoSuchProductException.class)
    protected ResponseEntity<ResponseEntityDto> handleNoSuchProductException(){
        return new ResponseEntity<>(new ResponseEntityDto<>("Product not found", null),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Product already exists for POST.
     * @return - responseEntity for product already exists exception.
     */
    @ExceptionHandler(ProductAlreadyExistsException.class)
    protected ResponseEntity<ResponseEntityDto> handleProductAlreadyExistsException(){
        return new ResponseEntity<>(new ResponseEntityDto<>("Product already exists", null),
                HttpStatus.BAD_REQUEST);
    }

    /**
<<<<<<< HEAD:src/main/java/restapi/controller/MainExceptionHandler.java
     * Exception handler for @Valid
     * @param ex - default exception
     * @return - responseEntity for exception of @Valid.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ResponseEntityDto> handleValidationFailedException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String errorMessage = "";
        if(fieldError != null){
            errorMessage = fieldError.getDefaultMessage();
        }
        return new ResponseEntity<>(new ResponseEntityDto<>(errorMessage, null),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for null parameters of requests
     * @return - response entity for null arguments of Product.
     */
    @ExceptionHandler(NullProductArgsException.class)
    protected ResponseEntity<ResponseEntityDto> handleNullProductArgsException(){
        return new ResponseEntity<>(new ResponseEntityDto<>("All necessary parameters must be specified.", null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handler for unexpected exceptions.
     * @return - responseEntity for unexpected exceptions - internal error 500.
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseEntityDto> handleUnexpectedError(){
        return new ResponseEntity<>(new ResponseEntityDto<>("Internal server error", null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
=======
     * Wrong name or type of product.
     * @return - responseEntity
     */
    @ExceptionHandler(ProductCheckingFailedException.class)
    public ResponseEntity<ResponseEntityDto> handleProductCheckingFailedException(){
        return new ResponseEntity<>(new ResponseEntityDto("Wrong name or type of product", null),
                HttpStatus.BAD_REQUEST);
    }
>>>>>>> Mistakes Correction 2.1.:src/main/java/restapi/controller/exception/ProductExceptionHandler.java
}
