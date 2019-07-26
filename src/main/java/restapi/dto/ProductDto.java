package restapi.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Class, which uses for receiving of request for product as request body.
 */
public class ProductDto {
    private final Long id;

    @NotNull(message = "Name can't be empty.")
    @ApiModelProperty(notes = "Name of product")
    private final String name;

    @NotNull(message = "Type can't be empty.")
    @ApiModelProperty(notes = "Type of product")
    private final String type;

    @NotNull(message = "Price can't be empty.")
    @ApiModelProperty(notes = "Price of product")
    private final Long price;


    public ProductDto() {
        this.id = null;
        this.name = null;
        this.type = null;
        this.price = null;
    }

    public ProductDto(Long id, String name, String type, Long price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public ProductDto(String name, String type, Long price) {
        this.id = null;
        this.name = name;
        this.type = type;
        this.price = price;
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

    public Long getPrice() {
        return price;
    }
}