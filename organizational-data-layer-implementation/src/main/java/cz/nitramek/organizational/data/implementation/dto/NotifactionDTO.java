package cz.nitramek.organizational.data.implementation.dto;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "NOT")
public class NotifactionDTO extends MessageDTO {

    private String triggeredValue;

    @Transient
    private ItemDTO triggeredItemDTO;

    public String getTriggeredValue() {
        return triggeredValue;
    }

    public void setTriggeredValue(String triggeredValue) {
        this.triggeredValue = triggeredValue;
    }

    public ItemDTO getTriggeredItemDTO() {
        return triggeredItemDTO;
    }

    public void setTriggeredItemDTO(ItemDTO triggeredItemDTO) {
        this.triggeredItemDTO = triggeredItemDTO;
    }
}
