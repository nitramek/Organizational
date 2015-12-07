package cz.nitramek.organizational.data.implementation.dto;

import cz.nitramek.organizational.domain.classes.Permission;
import cz.nitramek.organizational.domain.interafaces.Identifiable;

import javax.persistence.*;

@Entity(name = "Permission")
@Table(name = "Permission")
//@NamedQuery(name = "Permission.selectHighest", query = "SELECT p\n" +
//        "FROM Permission p \n" +
//        "WHERE item = :itemId AND EXISTS (\n" +
//        "    SELECT pp\n" +
//        "    FROM Permission PP \n" +
//        "    WHERE p.level >= pp.level AND\n" +
////        "     item = :itemId AND\n" +
//        "     p.roleId.id IN (\n" +
//        "        SELECT r.id \n" +
//        "        FROM Role r \n" +
//        "        WHERE r.id IN(\n" +
//        "            SELECT ur.id \n" +
//        "            FROM User u join fetch u.roles ur\n" +
//        "            WHERE u.id = :userId\n" +
//        "        )\n" +
//        "    ) \n" +
//        ")")
public class PermissionDTO implements Identifiable {

    @Id
    @GeneratedValue
    private long id;

    private Permission.Level level;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private RoleDTO roleId;

    public PermissionDTO() {
    }


    public RoleDTO getRole() {
        return roleId;
    }

    public void setRole(RoleDTO roleId) {
        this.roleId = roleId;
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
