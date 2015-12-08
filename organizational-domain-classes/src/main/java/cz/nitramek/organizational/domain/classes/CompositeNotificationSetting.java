package cz.nitramek.organizational.domain.classes;


public class CompositeNotificationSetting extends NotificationSetting {

    private CompositeOperation compositeOperation;
    private NotificationSetting nextNotificationSetting;


    public CompositeNotificationSetting(
            Operation operation, String name, String triggerValue, Attribute watchedAttribute,
            CompositeOperation compositeOperation, NotificationSetting nextNotificationSetting) {
        super(operation, name, triggerValue, watchedAttribute);
        this.compositeOperation = compositeOperation;
        this.nextNotificationSetting = nextNotificationSetting;
    }


    public CompositeNotificationSetting(
            Operation operation, String name, String triggerValue,
            Attribute watchedAttribute) {
        super(operation, name, triggerValue, watchedAttribute);
    }

    public CompositeNotificationSetting(
            NotificationSetting first, NotificationSetting second, CompositeOperation compositeOperation) {
        super(first);
        this.nextNotificationSetting = second;
        this.compositeOperation = compositeOperation;
    }

    @Override
    public boolean check() {
        if (this.compositeOperation.equals(CompositeOperation.AND)) {
            return super.check() && nextNotificationSetting.check();
        } else if (this.compositeOperation.equals(CompositeOperation.OR)) {
            return super.check() || nextNotificationSetting.check();
        }
        return false;
    }

    public CompositeOperation getCompositeOperation() {
        return compositeOperation;
    }

    public void setCompositeOperation(CompositeOperation compositeOperation) {
        this.compositeOperation = compositeOperation;
    }

    public NotificationSetting getNextNotificationSetting() {
        return nextNotificationSetting;
    }

    public void setNextNotificationSetting(NotificationSetting nextNotificationSetting) {
        this.nextNotificationSetting = nextNotificationSetting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositeNotificationSetting)) return false;

        CompositeNotificationSetting that = (CompositeNotificationSetting) o;

        if (getCompositeOperation() != that.getCompositeOperation()) return false;
        return getNextNotificationSetting().equals(that.getNextNotificationSetting()) && super.equals(o);

    }

    @Override
    public int hashCode() {
        int result = getCompositeOperation().hashCode();
        result = 31 * result + getNextNotificationSetting().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CompositeNotificationSetting{");
        sb.append("compositeOperation=").append(compositeOperation);
        sb.append(", nextNotificationSetting=").append(nextNotificationSetting);
        sb.append('}');
        return sb.toString();
    }

    public enum CompositeOperation {
        AND, OR
    }
}
