package restapi.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "kindofproduct")
public class KindOfProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name can't be empty.")
    private String name;

    @NotNull(message = "Type can't be empty.")
    private String type;

    public KindOfProduct() {
    }

    public KindOfProduct(Long id, String kindOfName, String kindOfType) {
        this.id = id;
        this.name = kindOfName;
        this.type = kindOfType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
