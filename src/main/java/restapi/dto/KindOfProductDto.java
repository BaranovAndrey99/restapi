package restapi.dto;

import javax.validation.constraints.NotNull;

public class KindOfProductDto {
    private long id;

    @NotNull(message = "Name can't be empty.")
    private String name;

    @NotNull(message = "Type can't be empty.")
    private String type;

    public KindOfProductDto() {
    }

    public KindOfProductDto(long id, String kindOfName, String kindOfType) {
        this.id = id;
        this.name = kindOfName;
        this.type = kindOfType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
