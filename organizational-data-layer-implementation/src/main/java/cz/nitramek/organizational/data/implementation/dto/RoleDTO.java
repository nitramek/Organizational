package cz.nitramek.organizational.data.implementation.dto;


import cz.nitramek.organizational.domain.interafaces.Identifiable;

public class RoleDTO implements Identifiable {
    private long id;
    private String name;
    private String displayName;


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
}