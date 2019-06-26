package restapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import restapi.repository.ImaginaryRepository;
import restapi.validation.Validator;

/**
 * Class for configuring.
 * This class contains bean declarations.
 */
@Configuration
public class ApplicationConfig {
    @Bean
    public ImaginaryRepository imaginaryRepository(){
        return new ImaginaryRepository();
    }

    @Bean
    public Validator validator(){
        return new Validator();
    }
}
