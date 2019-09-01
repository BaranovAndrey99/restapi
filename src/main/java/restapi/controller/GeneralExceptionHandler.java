package restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import restapi.dto.ResponseEntityDto;
import restapi.exception.general.EmptyRequestBodyException;
import restapi.exception.general.NullArgsException;
import restapi.exception.kindofproduct.KindOfProductAlreadyExistsException;
import restapi.exception.kindofproduct.KindOfProductNotExistsException;
import restapi.exception.product.ProductAlreadyExistsException;
import restapi.exception.product.ProductNotExistsException;

import java.rmi.UnexpectedException;

@ControllerAdvice
public class GeneralExceptionHandler {

    /* PRODUCT EXCEPTIONS HANDLERS */

    /**
     * Product not find for GET, PUT, DELETE.
     * @return - responseEntity for product not found exception.
     */
    @ExceptionHandler(ProductNotExistsException.class)
    protected ResponseEntity<ResponseEntityDto> handleProductNotExistsException(){
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

    /* KIND OF PRODUCT EXCEPTION HANDLERS */

    /**
     * Kind of product not find.
     * @return - responseEntity for product not found exception.
     */
    @ExceptionHandler(KindOfProductNotExistsException.class)
    protected ResponseEntity<ResponseEntityDto> handleKindOfProductNotExistsException(){
        return new ResponseEntity<>(new ResponseEntityDto<>("Kind of product not found", null),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Product already exists.
     * @return - responseEntity for product already exists exception.
     */
    @ExceptionHandler(KindOfProductAlreadyExistsException.class)
    protected ResponseEntity<ResponseEntityDto> handleKindOfProductAlreadyExistsException(){
        return new ResponseEntity<>(new ResponseEntityDto<>("Kind of product already exists", null),
                HttpStatus.BAD_REQUEST);
    }

    /* ARGUMENT EXCEPTIONS */

    @ExceptionHandler(EmptyRequestBodyException.class)
    protected ResponseEntity<ResponseEntityDto> handleEmptyRequestBodyException(){
        return new ResponseEntity<>(new ResponseEntityDto<>("Request for create can't be empty", null),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for null parameters of requests
     * @return - response entity for null arguments of Product.
     */
    @ExceptionHandler(NullArgsException.class)
    protected ResponseEntity<ResponseEntityDto> handleNullProductArgsException(){
        return new ResponseEntity<>(new ResponseEntityDto<>("All necessary parameters must be specified.", null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
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

    /* UNEXPECTED EXCEPTION */

    /**
     * Handler for unexpected exceptions.
     * @return - responseEntity for unexpected exceptions - internal error 500.
     */
    @ExceptionHandler({UnexpectedException.class})
    protected ResponseEntity<ResponseEntityDto> handleUnexpectedError(){
        return new ResponseEntity<>(new ResponseEntityDto<>("Internal server error", null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
