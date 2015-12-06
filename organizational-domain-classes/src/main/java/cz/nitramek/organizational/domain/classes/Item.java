package cz.nitramek.organizational.domain.classes;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Item implements Serializable {
    private long id;
    private String name;
    private Date dateAdded;

    private Date dateChanged;

    private boolean borrowable;

    private List<Permission> permissions;
    private ItemType type;

    private List<Attribute> attributes;
    private List<Category> categories;


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    private User owner;

    public Item() {
        attributes = new ArrayList<>();
        categories = new ArrayList<>();
        permissions = new ArrayList<>();
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public boolean isBorrowable() {
        return borrowable;
    }

    public void setBorrowable(boolean borrowable) {
        this.borrowable = borrowable;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(Date dateChanged) {
        this.dateChanged = dateChanged;
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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (getId() != item.getId()) return false;
        return getName().equals(item.getName());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Item{");
        sb.append("attributes=").append(attributes);
        sb.append(", id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", dateAdded=").append(dateAdded);
        sb.append(", dateChanged=").append(dateChanged);
        sb.append(", borrowable=").append(borrowable);
        sb.append(", permissions=").append(permissions);
        sb.append(", type=").append(type);
        sb.append(", categories=").append(categories);
        sb.append('}');
        return sb.toString();
    }
}
