package restapi.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    /**
     * ModelMapper for traslation entity <-> dto.
     * @return - ModelMapper
     */
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
