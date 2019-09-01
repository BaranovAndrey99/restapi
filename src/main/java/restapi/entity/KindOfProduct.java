package restapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "kind_of_product")
public class KindOfProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
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
