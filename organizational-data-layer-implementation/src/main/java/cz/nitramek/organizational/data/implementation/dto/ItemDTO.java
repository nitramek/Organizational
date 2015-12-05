package cz.nitramek.organizational.data.implementation.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ItemDTO implements Serializable {
    private long id;
    private String name;
    private Date dateAdded;

    private Date dateChanged;

    private boolean borrowable;

    private List<PermissionDTO> permissionDTOs;
    private ItemTypeDTO type;

    private List<AttributeDTO<?>> attributeDTOs;
    private List<CategoryDTO> categories;

    public List<AttributeDTO<?>> getAttributeDTOs() {
        return attributeDTOs;
    }

    public void setAttributeDTOs(List<AttributeDTO<?>> attributeDTOs) {
        this.attributeDTOs = attributeDTOs;
    }

    public boolean isBorrowable() {
        return borrowable;
    }

    public void setBorrowable(boolean borrowable) {
        this.borrowable = borrowable;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
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

    public List<PermissionDTO> getPermissionDTOs() {
        return permissionDTOs;
    }

    public void setPermissionDTOs(List<PermissionDTO> permissionDTOs) {
        this.permissionDTOs = permissionDTOs;
    }

    public ItemTypeDTO getType() {
        return type;
    }

    public void setType(ItemTypeDTO type) {
        this.type = type;
    }
}