package restapi.valid;

import restapi.valid.constraints.TypeExistence;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Check of existence of type of product.
 */
public class TypeExistenceValidator implements ConstraintValidator<TypeExistence, String>{
    private final List<String> productTypes = new ArrayList<>(Arrays.asList(
            "Молоко",
            "Мясо",
            "Фрукты",
            "Овощи")
    );

    @Override
    public void initialize(TypeExistence nameExistence){
    }

    @Override
    public boolean isValid(String requestType, ConstraintValidatorContext context) {
        Iterator<String> iterator = productTypes.iterator();
        boolean result = false;
        while(iterator.hasNext()){
            if(iterator.next().equals(requestType)){
                result = true;
            }
        }
        return result;
    }
}
