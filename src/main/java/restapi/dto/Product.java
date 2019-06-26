package restapi.dto;

import io.swagger.annotations.ApiModelProperty;
import restapi.transfer.New;
import restapi.transfer.UpdateName;
import restapi.transfer.UpdateType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class Product {
    /**
     * Annotations @Null and @NotNull work only with interfaces New and UpdateName in different cases.
     */
    @Null(groups = {New.class})
    @NotNull(groups = {UpdateName.class})
    @ApiModelProperty(notes = "The database generated product ID")
    private Long id;

    @NotNull(groups = {New.class, UpdateName.class})
    @ApiModelProperty(notes = "The product name")
    private String name;

    @NotNull(groups = {New.class, UpdateType.class})
    @ApiModelProperty(notes = "The product type")
    private String type;

    public Product() {
    }

    public Product(long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    /**
     * Equals by identifier
     * @param product - comparable product
     * @return - result
     */
    public boolean idEquals(Product product){
        if(this.id == product.id){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Equals by identifier
     * @param product - comparable product
     * @return - result
     */
    public boolean nameAndTypeEquals(Product product){
        if(this.name.equals(product.getName())&&this.type.equals(product.getType())){
            return true;
        } else {
            return false;
        }
    }
}
