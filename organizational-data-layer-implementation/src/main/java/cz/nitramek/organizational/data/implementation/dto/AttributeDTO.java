package cz.nitramek.organizational.data.implementation.dto;

import javax.persistence.*;

@Entity(name = "Attribute")
@Table(name = "Attribute")
public class AttributeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String strValue;

    @ManyToOne
    @JoinColumn(name = "typeId")
    private AttributeTypeDTO type;


    public AttributeDTO(String strValue, AttributeTypeDTO type) {
        this.strValue = strValue;
        this.type = type;
    }

    public AttributeDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public AttributeTypeDTO getType() {
        return type;
    }

    public void setType(AttributeTypeDTO type) {
        this.type = type;

    }


}
