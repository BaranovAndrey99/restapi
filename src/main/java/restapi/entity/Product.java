package restapi.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.lang.annotation.Target;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated product ID")
    private Long id;

    @ApiModelProperty(notes = "The product name")
    private String name;
    
    @ApiModelProperty(notes = "The product type")
    private String type;

    public Product() {
    }

    public Product(long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
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
