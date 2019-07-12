package restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import restapi.dto.ResponseEntityDto;
import restapi.exception.general.NullArgsException;

import java.rmi.UnexpectedException;

@ControllerAdvice
public class GeneralExceptionHandler {

    /* ARGUMENT EXCEPTIONS */

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
