package cz.nitramek.organizational.data.implementation.dto;

import cz.nitramek.organizational.domain.interafaces.Identifiable;

import javax.persistence.*;
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

    @OneToMany
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private Set<RoleDTO> roles;

    @OneToMany
    @JoinColumn(name = "sender", referencedColumnName = "id")
    private Set<MessageDTO> sent;

    @OneToMany
    @JoinColumn(name = "recipient", referencedColumnName = "id")
    private Set<MessageDTO> received;

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

    public Set<MessageDTO> getReceived() {
        return received;
    }

    public void setReceived(Set<MessageDTO> received) {
        this.received = received;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public Set<MessageDTO> getSent() {
        return sent;
    }

    public void setSent(Set<MessageDTO> sent) {
        this.sent = sent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;

        UserDTO userDTO = (UserDTO) o;

        if (getId() != userDTO.getId()) return false;
        if (!getNickname().equals(userDTO.getNickname())) return false;
        return getEmail().equals(userDTO.getEmail());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getNickname().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }
}

