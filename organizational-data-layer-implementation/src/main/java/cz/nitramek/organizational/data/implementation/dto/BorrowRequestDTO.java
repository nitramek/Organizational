package cz.nitramek.organizational.data.implementation.dto;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "REQ")
public class BorrowRequestDTO extends MessageDTO {

    @Transient
    private ItemDTO requestedItemDTO;

    public ItemDTO getRequestedItemDTO() {
        return requestedItemDTO;
    }

    public void setRequestedItemDTO(ItemDTO requestedItemDTO) {
        this.requestedItemDTO = requestedItemDTO;
    }
}
