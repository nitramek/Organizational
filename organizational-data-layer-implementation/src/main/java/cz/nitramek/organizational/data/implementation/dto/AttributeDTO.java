package cz.nitramek.organizational.data.implementation.dto;


public class AttributeDTO<T> {
    private long id;
    private String strValue;
    private T value;

    private AttributeTypeDTO<T> type;


    public AttributeDTO(String strValue, AttributeTypeDTO<T> type) {
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

    public AttributeTypeDTO<T> getType() {
        return type;
    }

    public void setType(AttributeTypeDTO<T> type) {
        this.type = type;
        this.value = this.type.getType().convert(this.strValue);

    }

    public T getValue() {
        return value;
    }

}
