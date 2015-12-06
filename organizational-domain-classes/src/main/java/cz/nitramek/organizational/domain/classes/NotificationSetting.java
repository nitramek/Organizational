package cz.nitramek.organizational.domain.classes;


import cz.nitramek.organizational.domain.interafaces.Identifiable;

public class NotificationSetting implements Identifiable {
    private long id;
    private String name;
    private String triggerValue;
    private Operation operation;
    private String text;
    private Attribute watchedAttribute;
    private User user;

    public NotificationSetting() {
    }

    public NotificationSetting(Operation operation, String name, String triggerValue, Attribute watchedAttribute) {
        this.operation = operation;
        this.name = name;
        this.triggerValue = triggerValue;
        this.watchedAttribute = watchedAttribute;
    }

    public NotificationSetting(NotificationSetting notificationSetting) {
        this.id = notificationSetting.getId();
        this.name = notificationSetting.getName();
        this.triggerValue = notificationSetting.getTriggerValue();
        this.operation = notificationSetting.getOperation();
        this.text = notificationSetting.getText();
        this.watchedAttribute = notificationSetting.getWatchedAttribute();
        this.user = notificationSetting.getUser();
    }

    public boolean check() {

        Comparable watchedValue = (Comparable) watchedAttribute.getValue();
        Comparable secondvalue = (Comparable) watchedAttribute.getType().getType().convert(this.triggerValue);
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

    public Attribute getWatchedAttribute() {
        return watchedAttribute;
    }

    public void setWatchedAttribute(Attribute watchedAttribute) {
        this.watchedAttribute = watchedAttribute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationSetting)) return false;

        NotificationSetting that = (NotificationSetting) o;

        if (getId() != that.getId()) return false;
        return getName().equals(that.getName());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NotificationSetting{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", triggerValue='").append(triggerValue).append('\'');
        sb.append(", operation=").append(operation);
        sb.append(", text='").append(text).append('\'');
        sb.append(", watchedAttribute=").append(watchedAttribute);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }

    public enum Operation {
        EQ, LT, GT
    }
}
