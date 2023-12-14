import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<ItemOrder> itemOrders;

    public ShoppingCart() {
        itemOrders = new ArrayList<ItemOrder>();
    }

    public void add(ItemOrder itemOrder) {
        itemOrders.remove(itemOrder);
        itemOrders.add(itemOrder);
    }

    public double getTotal() {
        double total = 0;
        for (ItemOrder itemOrder : itemOrders) {
            total += itemOrder.getPrice();
        }
        return total;
    }
}
