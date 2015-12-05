package cz.nitramek.organizational.domain.classes;

import cz.nitramek.organizational.domain.interafaces.Identifiable;

import java.util.List;

public class User implements Identifiable {
    private long id;

    private String nickname;

    private String firstName;

    private String lastName;

    private String password;


    private String birthday;

    private String email;

    private boolean administrator;


    private List<String> rolesToAdd;
    private List<Role> roles;
    private List<Message> sent;
    private List<Message> received;

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

    public List<Message> getReceived() {
        return received;
    }

    public void setReceived(List<Message> received) {
        this.received = received;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<String> getRolesToAdd() {
        return rolesToAdd;
    }

    public void setRolesToAdd(List<String> rolesToAdd) {
        this.rolesToAdd = rolesToAdd;
    }

    public List<Message> getSent() {
        return sent;
    }

    public void setSent(List<Message> sent) {
        this.sent = sent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        return getNickname().equals(user.getNickname());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getNickname().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("administrator=").append(administrator);
        sb.append(", id=").append(id);
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", birthday='").append(birthday).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", rolesToAdd=").append(rolesToAdd);
        sb.append(", roles=").append(roles);
        sb.append(", sent=").append(sent);
        sb.append(", received=").append(received);
        sb.append('}');
        return sb.toString();
    }
}

