package restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import restapi.dto.ResponseEntityDto;

import java.util.List;

/**
 * Controller for handling of exceptions, which presented by @Valid annotation.
 * Handling of MethodArgumentNotValidException.
 */
@ControllerAdvice
public class ArgumentExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ResponseEntityDto> handleValidationFailedException(MethodArgumentNotValidException ex){

        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String field = fieldError.getField();
        String errorMessage = fieldError.getDefaultMessage();
        return new ResponseEntity<>(new ResponseEntityDto(errorMessage, null),
                HttpStatus.BAD_REQUEST);
    }
}
