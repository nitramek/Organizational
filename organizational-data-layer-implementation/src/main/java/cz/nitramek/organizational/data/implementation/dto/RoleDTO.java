package cz.nitramek.organizational.data.implementation.dto;


import cz.nitramek.organizational.domain.interafaces.Identifiable;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Role")
@Table(name = "Role")
@NamedQueries(
        {
                @NamedQuery(name = "Role.selectByName", query = "SELECT r FROM Role r WHERE r.name = :roleName"),
                @NamedQuery(name = "Role.selectAll", query = "SELECT r FROM Role r"),
                @NamedQuery(name = "Role.selectOne", query = "SELECT r FROM Role r WHERE r.id = :id")
        }
)
public class RoleDTO implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String displayName;

    @ManyToMany
    @JoinTable(name = "Role_Permission",
            joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permisisonId", referencedColumnName = "id")})
    private Set<PermissionDTO> permissionDTOs;

    public Set<PermissionDTO> getPermission() {
        return permissionDTOs;
    }

    public void setPermissions(Set<PermissionDTO> permissionDTOs) {
        this.permissionDTOs = permissionDTOs;
    }

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
