package restapi.valid;

import restapi.valid.constraints.NameExistence;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Check of existence of name of product.
 */
public class NameExistenceValidator implements ConstraintValidator<NameExistence, String> {
    private final List<String> productNames = new ArrayList<>(Arrays.asList(
            "Молоко", "Кефир", "Масло",
            "Колбаса", "Свинина", "Сосиски",
            "Яблоко", "Груша", "Апельсин",
            "Огурец", "Томат", "Капуста")
    );

    @Override
    public void initialize(NameExistence nameExistence){
    }

    @Override
    public boolean isValid(String requestName, ConstraintValidatorContext context) {
        Iterator<String> iterator = productNames.iterator();
        boolean result = false;
        while(iterator.hasNext()){
            if(iterator.next().equals(requestName)){
                result = true;
            }
        }
        return result;
    }
}
