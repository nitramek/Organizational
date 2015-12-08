package cz.nitramek.organizational.data.implementation.dto;


import cz.nitramek.organizational.domain.classes.CompositeNotificationSetting;
import cz.nitramek.organizational.domain.classes.NotificationSetting;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "CNS")
public class CompositeNotificationSettingDTO extends NotificationSettingDTO {


    @Enumerated(value = EnumType.STRING)
    private CompositeNotificationSetting.CompositeOperation compositeOperation;

    @ManyToOne
    @JoinColumn(name = "nextNotificationId")
    private NotificationSettingDTO nextNotificationSettingDTO;

    public CompositeNotificationSettingDTO() {
        super();
    }

    public CompositeNotificationSettingDTO(
            NotificationSetting.Operation operation, String name, String triggerValue, AttributeDTO watchedAttributeDTO,
            CompositeNotificationSetting.CompositeOperation compositeOperation,
            NotificationSettingDTO nextNotificationSettingDTO) {
        super(operation, name, triggerValue, watchedAttributeDTO);
        this.compositeOperation = compositeOperation;
        this.nextNotificationSettingDTO = nextNotificationSettingDTO;
    }


    public CompositeNotificationSetting.CompositeOperation getCompositeOperation() {
        return compositeOperation;
    }

    public void setCompositeOperation(CompositeNotificationSetting.CompositeOperation compositeOperation) {
        this.compositeOperation = compositeOperation;
    }

    public NotificationSettingDTO getNextNotificationSettingDTO() {
        return nextNotificationSettingDTO;
    }

    public void setNextNotificationSettingDTO(NotificationSettingDTO nextNotificationSettingDTO) {
        this.nextNotificationSettingDTO = nextNotificationSettingDTO;
    }
}
