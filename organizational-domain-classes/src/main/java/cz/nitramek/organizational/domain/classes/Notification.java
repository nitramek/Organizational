package cz.nitramek.organizational.domain.classes;


public class Notification extends Message {
    private String triggeredValue;
    private Item triggredItem;

    public Notification() {
    }

    public Notification(String subject) {
        super(subject);
    }

    public String getTriggeredValue() {
        return triggeredValue;
    }

    public void setTriggeredValue(String triggeredValue) {
        this.triggeredValue = triggeredValue;
    }

    public Item getTriggredItem() {
        return triggredItem;
    }

    public void setTriggredItem(Item triggeredItem) {
        this.triggredItem = triggeredItem;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Notifaction{");
        sb.append("triggeredValue='").append(triggeredValue).append('\'');
        sb.append(", triggredItem=").append(triggredItem);
        sb.append('}');
        return sb.toString();
    }
}
