package cz.nitramek.organizational.data.implementation.dto;

import cz.nitramek.organizational.domain.interafaces.Identifiable;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "User")
@Table(name = "User")
public class UserDTO implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nickname;

    private String firstName;

    private String lastName;

    private String password;


    private String birthday;

    private String email;

    private boolean administrator;

    @Transient
    private List<String> rolesToAdd;
    @Transient
    private Set<RoleDTO> roles;
    @Transient
    private List<MessageDTO> sent;
    @Transient
    private List<MessageDTO> received;

    public UserDTO() {
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MessageDTO> getReceived() {
        return received;
    }

    public void setReceived(List<MessageDTO> received) {
        this.received = received;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public List<String> getRolesToAdd() {
        return rolesToAdd;
    }

    public void setRolesToAdd(List<String> rolesToAdd) {
        this.rolesToAdd = rolesToAdd;
    }

    public List<MessageDTO> getSent() {
        return sent;
    }

    public void setSent(List<MessageDTO> sent) {
        this.sent = sent;
    }
}

