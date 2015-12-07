package cz.nitramek.organizational.domain.classes;

import cz.nitramek.organizational.domain.interafaces.Identifiable;


public class Permission implements Identifiable {
    private long id;
    private Level level;

    private Role role;

    public Permission() {
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission(Level level) {
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permission)) return false;

        Permission that = (Permission) o;

        if (getId() != that.getId()) return false;
        return getLevel() == that.getLevel();

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getLevel().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Permission{");
        sb.append("id=").append(id);
        sb.append(", level=").append(level);
        sb.append('}');
        return sb.toString();
    }

    public enum Level {
        GUEST(0), VISIBLE(1), ALL(2);

        private int level;

        Level(int level) {
            this.level = level;
        }

    }
}
