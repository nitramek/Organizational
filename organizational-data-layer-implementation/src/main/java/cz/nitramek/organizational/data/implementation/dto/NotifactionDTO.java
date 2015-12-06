package cz.nitramek.organizational.data.implementation.dto;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "NOT")
public class NotifactionDTO extends MessageDTO {

    private String triggeredValue;

    @ManyToOne
    @JoinColumn(name = "trgItemDto")
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
