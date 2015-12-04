package cz.nitramek.organizational.domain.classes;


public class Notifaction {
    private String triggeredValue;
    private Item triggredItem;

    public String getTriggeredValue() {
        return triggeredValue;
    }

    public void setTriggeredValue(String triggeredValue) {
        this.triggeredValue = triggeredValue;
    }

    public Item getTriggredItem() {
        return triggredItem;
    }

    public void setTriggredItem(Item triggredItem) {
        this.triggredItem = triggredItem;
    }
}
