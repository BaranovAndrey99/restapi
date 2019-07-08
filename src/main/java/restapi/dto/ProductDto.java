package restapi.dto;

import restapi.valid.groups.ExtendedValidation;
import restapi.valid.constraints.NameExistence;
import restapi.valid.constraints.TypeExistence;

import javax.validation.constraints.NotNull;

public class ProductDto {
    private final Long id;

    @NotNull(message = "Name can't be empty.")
    private final String name;

    @NotNull(message = "Type can't be empty.")
    private final String type;

    public ProductDto() {
        this.id = null;
        this.name = null;
        this.type = null;
    }
    public ProductDto(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public ProductDto(String name, String type) {
        this.id = null;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}