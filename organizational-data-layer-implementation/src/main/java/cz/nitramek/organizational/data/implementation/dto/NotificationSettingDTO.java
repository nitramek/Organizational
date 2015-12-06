package cz.nitramek.organizational.data.implementation.dto;


import cz.nitramek.organizational.domain.interafaces.Identifiable;

public class NotificationSettingDTO implements Identifiable {
    enum Operation {
        EQ, LT, GT
    }

    private long id;
    private String name;
    private String triggerValue;
    private Operation operation;
    private String text;

    private AttributeDTO watchedAttributeDTO;


    private UserDTO userDTO;

    public NotificationSettingDTO(
            Operation operation, String name, String triggerValue, AttributeDTO watchedAttributeDTO) {
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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public AttributeDTO getWatchedAttributeDTO() {
        return watchedAttributeDTO;
    }

    public void setWatchedAttributeDTO(AttributeDTO watchedAttributeDTO) {
        this.watchedAttributeDTO = watchedAttributeDTO;
    }
}
