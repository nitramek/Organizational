package cz.nitramek.organizational.domain.classes;


import cz.nitramek.organizational.domain.interafaces.Identifiable;

public class NotificationSetting<T extends Comparable<T>> implements Identifiable {
    enum Operation {
        EQ, LT, GT
    }

    private long id;
    private String name;
    private String triggerValue;
    private Operation operation;
    private String text;

    private Attribute<T> watchedAttribute;


    private User user;

    public NotificationSetting(Operation operation, String name, String triggerValue, Attribute<T> watchedAttribute) {
        this.operation = operation;
        this.name = name;
        this.triggerValue = triggerValue;
        this.watchedAttribute = watchedAttribute;
    }


    public boolean check() {

        T watchedValue = watchedAttribute.getValue();
        T secondvalue = watchedAttribute.getType().getType().convert(this.triggerValue);
        int compared = watchedValue.compareTo(secondvalue);
        switch (this.operation) {
            case EQ:
                return compared == 0;
            case LT:
                return compared < 1;
            case GT:
                return compared > 1;
            default:
                return false;

        }
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

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTriggerValue() {
        return triggerValue;
    }

    public void setTriggerValue(String triggerValue) {
        this.triggerValue = triggerValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Attribute<T> getWatchedAttribute() {
        return watchedAttribute;
    }

    public void setWatchedAttribute(Attribute<T> watchedAttribute) {
        this.watchedAttribute = watchedAttribute;
    }
}
