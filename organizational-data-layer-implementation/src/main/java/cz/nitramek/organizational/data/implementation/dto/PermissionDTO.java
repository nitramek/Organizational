package cz.nitramek.organizational.data.implementation.dto;

import cz.nitramek.organizational.domain.classes.Permission;
import cz.nitramek.organizational.domain.interafaces.Identifiable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Permission")
@Table(name = "Permission")
public class PermissionDTO implements Identifiable {

    @Id
    @GeneratedValue
    private long id;

    private Permission.Level level;

    public PermissionDTO() {
    }

    public PermissionDTO(Permission.Level level) {
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Permission.Level getLevel() {
        return level;
    }

    public void setLevel(Permission.Level level) {
        this.level = level;
    }
}
