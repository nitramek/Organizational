package cz.nitramek.organizational.data.implementation.dto;


import cz.nitramek.organizational.domain.interafaces.Identifiable;

import javax.persistence.*;


@Entity(name = "AttributeType")
@Table(name = "AttributeType")
public class AttributeTypeDTO implements Identifiable {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private boolean mandatory;

    @Transient
    private AttributeValueTypeDTO type;


    public AttributeTypeDTO() {
    }

    public AttributeTypeDTO(boolean mandatory, String name, AttributeValueTypeDTO type) {
        this.mandatory = mandatory;
        this.name = name;
        this.type = type;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AttributeValueTypeDTO getType() {
        return type;
    }

    public void setType(AttributeValueTypeDTO type) {
        this.type = type;
    }
}
