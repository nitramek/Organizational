package cz.nitramek.organizational.data.implementation.dto;


public class NotifactionDTO {
    private String triggeredValue;
    private ItemDTO triggredItemDTO;

    public String getTriggeredValue() {
        return triggeredValue;
    }

    public void setTriggeredValue(String triggeredValue) {
        this.triggeredValue = triggeredValue;
    }

    public ItemDTO getTriggredItemDTO() {
        return triggredItemDTO;
    }

    public void setTriggredItemDTO(ItemDTO triggredItemDTO) {
        this.triggredItemDTO = triggredItemDTO;
    }
}
