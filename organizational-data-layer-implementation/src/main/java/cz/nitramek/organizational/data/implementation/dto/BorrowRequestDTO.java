package cz.nitramek.organizational.data.implementation.dto;


public class BorrowRequestDTO extends MessageDTO {
    private ItemDTO requestedItemDTO;

    public ItemDTO getRequestedItemDTO() {
        return requestedItemDTO;
    }

    public void setRequestedItemDTO(ItemDTO requestedItemDTO) {
        this.requestedItemDTO = requestedItemDTO;
    }
}
