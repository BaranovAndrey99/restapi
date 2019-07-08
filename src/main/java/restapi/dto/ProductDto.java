package restapi.dto;

import javax.validation.constraints.NotNull;

public class ProductDto {
    private Long id;

    @NotNull(message = "Name can't be empty.")
    private String name;

    @NotNull(message = "Type can't be empty.")
    private String type;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public ProductDto(String name, String type) {
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
