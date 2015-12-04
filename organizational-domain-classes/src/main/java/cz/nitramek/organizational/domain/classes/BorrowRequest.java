package cz.nitramek.organizational.domain.classes;


public class BorrowRequest extends Message {
    private Item requestedItem;

    public Item getRequestedItem() {
        return requestedItem;
    }

    public void setRequestedItem(Item requestedItem) {
        this.requestedItem = requestedItem;
    }
}
