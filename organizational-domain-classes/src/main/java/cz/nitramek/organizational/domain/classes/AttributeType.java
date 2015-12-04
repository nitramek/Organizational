package cz.nitramek.organizational.domain.classes;


import cz.nitramek.organizational.domain.interafaces.Identifiable;

public class AttributeType<T> implements Identifiable {
    private long id;
    private String name;
    private boolean mandatory;

    private AttributeValueType<T> type;

    public AttributeType(boolean mandatory, String name, AttributeValueType<T> type) {
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

    public AttributeValueType<T> getType() {
        return type;
    }

    public void setType(AttributeValueType<T> type) {
        this.type = type;
    }
}
