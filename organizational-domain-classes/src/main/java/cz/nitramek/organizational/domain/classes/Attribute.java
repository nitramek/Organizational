package cz.nitramek.organizational.domain.classes;


public class Attribute {
    private long id;
    private String strValue;
    private Object value;

    private AttributeType type;


    public Attribute(String strValue, AttributeType type) {
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

    public AttributeType getType() {
        return type;
    }

    public void setType(AttributeType type) {
        this.type = type;
        this.value = this.type.getType().convert(this.strValue);

    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Attribute{");
        sb.append("id=").append(id);
        sb.append(", strValue='").append(strValue).append('\'');
        sb.append(", value=").append(value);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attribute)) return false;

        Attribute attribute = (Attribute) o;

        if (getId() != attribute.getId()) return false;
        if (!getStrValue().equals(attribute.getStrValue())) return false;
        return getType().equals(attribute.getType());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getStrValue().hashCode();
        result = 31 * result + getType().hashCode();
        return result;
    }
}
