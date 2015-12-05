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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BorrowRequest)) return false;

        BorrowRequest that = (BorrowRequest) o;

        return getRequestedItem().equals(that.getRequestedItem()) && super.equals(o);

    }

    @Override
    public int hashCode() {
        return getRequestedItem().hashCode();
    }

    @Override
    public String toString() {

        final StringBuffer sb = new StringBuffer("BorrowRequest{");
        sb.append("requestedItem=").append(requestedItem);
        sb.append('}');
        return super.toString() + sb.toString();
    }
}
