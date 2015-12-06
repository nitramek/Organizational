package cz.nitramek.organizational.data.implementation.dto;


import cz.nitramek.organizational.domain.classes.NotificationSetting;
import cz.nitramek.organizational.domain.interafaces.Identifiable;

import javax.persistence.*;


@Entity(name = "NotificationSetting")
@Table(name = "NotificationSetting")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@DiscriminatorValue(value = "NS")
@NamedQueries(value = {
        @NamedQuery(name = "NotificationSetting.selectByUser", query = "SELECT ns FROM NotificationSetting ns WHERE ns.owner.id = :id"),
        @NamedQuery(name = "NotificationSetting.selectById", query = "SELECT ns FROM NotificationSetting ns WHERE ns.id = :id")
})
public class NotificationSettingDTO implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String triggerValue;
    private NotificationSetting.Operation operation;
    private String text;

    @ManyToOne
    @JoinColumn(name = "watchedAttrId")
    private AttributeDTO watchedAttributeDTO;


    @ManyToOne
    @JoinColumn(name = "ownerId")
    private UserDTO owner;

    public NotificationSettingDTO() {
    }

    public NotificationSettingDTO(
            NotificationSetting.Operation operation, String name, String triggerValue,
            AttributeDTO watchedAttributeDTO) {
        this.operation = operation;
        this.name = name;
        this.triggerValue = triggerValue;
        this.watchedAttributeDTO = watchedAttributeDTO;
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

    public NotificationSetting.Operation getOperation() {
        return operation;
    }

    public void setOperation(NotificationSetting.Operation operation) {
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

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO userDTO) {
        this.owner = userDTO;
    }

    public AttributeDTO getWatchedAttributeDTO() {
        return watchedAttributeDTO;
    }

    public void setWatchedAttributeDTO(AttributeDTO watchedAttributeDTO) {
        this.watchedAttributeDTO = watchedAttributeDTO;
    }
}
