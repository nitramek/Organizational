package cz.nitramek.organizational.data.implementation.dto;


import cz.nitramek.organizational.domain.interafaces.Identifiable;

public class AttributeTypeDTO<T> implements Identifiable {
    private long id;
    private String name;
    private boolean mandatory;

    private AttributeValueTypeDTO<T> type;


    public AttributeTypeDTO(boolean mandatory, String name, AttributeValueTypeDTO<T> type) {
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

    public AttributeValueTypeDTO<T> getType() {
        return type;
    }

    public void setType(AttributeValueTypeDTO<T> type) {
        this.type = type;
    }
}
