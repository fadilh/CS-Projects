public class ItemOrder {
    private Item item;
    private int qty;

    public ItemOrder(Item item, int qty){
        this.item = item;
        this.qty = qty;
    }

    public double getPrice() {
        return item.priceFor(qty);
    }

    public Item getItem() {
        return item;
    }

    @Override
    public boolean equals(Object obj) {
        ItemOrder i = (ItemOrder) obj;
        return i.getItem() == item;
    }
}
