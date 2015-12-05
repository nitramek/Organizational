package cz.nitramek.organizational.data.implementation.dto;


public class CompositeNotificationSettingDTODTO<T extends Comparable<T>> extends NotificationSettingDTO<T> {

    enum CompositeOperation {
        AND, OR
    }

    private CompositeOperation compositeOperation;

    private NotificationSettingDTO<T> nextNotificationSettingDTO;


    public CompositeNotificationSettingDTODTO(Operation operation, String name, String triggerValue, AttributeDTO<T> watchedAttributeDTO, CompositeOperation compositeOperation, NotificationSettingDTO<T> nextNotificationSettingDTO) {
        super(operation, name, triggerValue, watchedAttributeDTO);
        this.compositeOperation = compositeOperation;
        this.nextNotificationSettingDTO = nextNotificationSettingDTO;
    }

    @Override
    public boolean check() {
        if (this.compositeOperation.equals(CompositeOperation.AND)) {
            return super.check() && nextNotificationSettingDTO.check();
        } else if (this.compositeOperation.equals(CompositeOperation.OR)) {
            return super.check() || nextNotificationSettingDTO.check();
        }
        return false;
    }

    public CompositeOperation getCompositeOperation() {
        return compositeOperation;
    }

    public void setCompositeOperation(CompositeOperation compositeOperation) {
        this.compositeOperation = compositeOperation;
    }

    public NotificationSettingDTO<T> getNextNotificationSettingDTO() {
        return nextNotificationSettingDTO;
    }

    public void setNextNotificationSettingDTO(NotificationSettingDTO<T> nextNotificationSettingDTO) {
        this.nextNotificationSettingDTO = nextNotificationSettingDTO;
    }
}
