package cz.nitramek.organizational.domain.classes;


import cz.nitramek.organizational.domain.interafaces.Identifiable;

import java.util.ArrayList;
import java.util.List;

public class ItemType implements Identifiable {
    private long id;
    private String name;

    private List<AttributeType> attributeTypes;


    public ItemType(String name) {
        this.name = name;
        attributeTypes = new ArrayList<>();
    }

    public List<AttributeType> getAttributeTypes() {
        return attributeTypes;
    }

    public void setAttributeTypes(List<AttributeType> attributeTypes) {
        this.attributeTypes = attributeTypes;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemType)) return false;

        ItemType itemType = (ItemType) o;

        if (getId() != itemType.getId()) return false;
        return getName().equals(itemType.getName());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ItemType{");
        sb.append("attributeTypes=").append(attributeTypes);
        sb.append(", id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
