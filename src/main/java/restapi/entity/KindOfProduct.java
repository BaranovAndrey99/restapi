package restapi.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "kindofproduct")
public class KindOfProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated kind of product ID")
    private Long id;

    @ApiModelProperty(notes = "The kind of product name")
    private String name;

    @ApiModelProperty(notes = "The kind of product type")
    private String type;

    public KindOfProduct() {
    }

    public KindOfProduct(Long id, String kindOfName, String kindOfType) {
        this.id = id;
        this.name = name;
        this.type = type;
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
