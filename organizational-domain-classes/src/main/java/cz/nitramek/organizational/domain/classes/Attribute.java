package cz.nitramek.organizational.domain.classes;


public class Attribute<T> {
    private long id;
    private String strValue;
    private T value;

    private AttributeType<T> type;


    public Attribute(String strValue, AttributeType<T> type) {
        this.strValue = strValue;
        this.type = type;
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

    public AttributeType<T> getType() {
        return type;
    }

    public void setType(AttributeType<T> type) {
        this.type = type;
        this.value = this.type.getType().convert(this.strValue);

    }

    public T getValue() {
        return value;
    }

}
