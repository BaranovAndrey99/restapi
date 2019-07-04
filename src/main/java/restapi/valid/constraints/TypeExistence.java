package restapi.valid.constraints;

import org.springframework.core.annotation.Order;
import restapi.valid.TypeExistenceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TypeExistenceValidator.class)
public @interface TypeExistence {
    String message() default "Not valid product type.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
