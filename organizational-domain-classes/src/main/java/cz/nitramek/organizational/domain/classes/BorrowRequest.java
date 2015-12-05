package cz.nitramek.organizational.domain.classes;


public class BorrowRequest extends Message {
    private Item requestedItem;

    public BorrowRequest(String subject, Item requestedItem) {
        super(subject);
        this.requestedItem = requestedItem;
    }

    public Item getRequestedItem() {
        return requestedItem;
    }

    public void setRequestedItem(Item requestedItem) {
        this.requestedItem = requestedItem;
    }
}
