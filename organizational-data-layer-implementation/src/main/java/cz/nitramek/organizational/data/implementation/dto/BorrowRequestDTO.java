package cz.nitramek.organizational.data.implementation.dto;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "REQ")
public class BorrowRequestDTO extends MessageDTO {

    @ManyToOne
    @JoinColumn(name = "reqItemId")
    private ItemDTO requestedItemDTO;

    public ItemDTO getRequestedItemDTO() {
        return requestedItemDTO;
    }

    public void setRequestedItemDTO(ItemDTO requestedItemDTO) {
        this.requestedItemDTO = requestedItemDTO;
    }
}
