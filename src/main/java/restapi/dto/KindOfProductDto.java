package restapi.dto;

import javax.validation.constraints.NotNull;

public class KindOfProductDto {
    private final Long id;

    @NotNull(message = "Name can't be empty.")
    private final String name;

    @NotNull(message = "Type can't be empty.")
    private final String type;

    public KindOfProductDto() {
        this.id = null;
        this.name = null;
        this.type = null;
    }

    public KindOfProductDto(String kindOfName, String kindOfType) {
        this.id = null;
        this.name = kindOfName;
        this.type = kindOfType;
    }

    public KindOfProductDto(Long id, String kindOfName, String kindOfType) {
        this.id = id;
        this.name = kindOfName;
        this.type = kindOfType;
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
