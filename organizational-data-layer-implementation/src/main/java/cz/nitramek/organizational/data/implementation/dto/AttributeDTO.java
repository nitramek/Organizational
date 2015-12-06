package cz.nitramek.organizational.data.implementation.dto;


public class AttributeDTO {
    private long id;
    private String strValue;
    private Object value;

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
        this.value = this.type.getType().convert(this.strValue);
    }

    public AttributeTypeDTO getType() {
        return type;
    }

    public void setType(AttributeTypeDTO type) {
        this.type = type;
        this.value = this.type.getType().convert(this.strValue);

    }

    public Object getValue() {
        return value;
    }

}
