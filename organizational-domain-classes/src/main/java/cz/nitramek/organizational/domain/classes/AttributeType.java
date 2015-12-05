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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttributeType)) return false;

        AttributeType<?> that = (AttributeType<?>) o;

        if (getId() != that.getId()) return false;
        if (isMandatory() != that.isMandatory()) return false;
        if (!getName().equals(that.getName())) return false;
        return getType().equals(that.getType());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        result = 31 * result + (isMandatory() ? 1 : 0);
        result = 31 * result + getType().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AttributeType{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", mandatory=").append(mandatory);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
