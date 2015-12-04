package cz.nitramek.organizational.domain.classes;

import cz.nitramek.organizational.domain.interafaces.Identifiable;


public class Permission implements Identifiable {
    enum Level {
        GUEST, VISIBLE, ALL
    }

    private long id;
    private Level level;

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
}
