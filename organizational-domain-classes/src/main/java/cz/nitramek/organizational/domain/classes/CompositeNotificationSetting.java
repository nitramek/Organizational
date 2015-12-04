package cz.nitramek.organizational.domain.classes;


public class CompositeNotificationSetting<T extends Comparable<T>> extends NotificationSetting<T> {

    enum CompositeOperation {
        AND, OR
    }

    private CompositeOperation compositeOperation;

    private NotificationSetting<T> nextNotificationSetting;

    
    public CompositeNotificationSetting(Operation operation, String name, String triggerValue, Attribute<T> watchedAttribute, CompositeOperation compositeOperation, NotificationSetting<T> nextNotificationSetting) {
        super(operation, name, triggerValue, watchedAttribute);
        this.compositeOperation = compositeOperation;
        this.nextNotificationSetting = nextNotificationSetting;
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

    public NotificationSetting<T> getNextNotificationSetting() {
        return nextNotificationSetting;
    }

    public void setNextNotificationSetting(NotificationSetting<T> nextNotificationSetting) {
        this.nextNotificationSetting = nextNotificationSetting;
    }
}
