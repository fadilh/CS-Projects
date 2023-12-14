public class Item {
    private String name;
    private double price;
    private int bulkQty;
    private double bulkPrice;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Item(String name, double price, int bulkQty, double bulkPrice) {
        this(name, price);
        this.bulkQty = bulkQty;
        this.bulkPrice = bulkPrice;
    }

    public double priceFor(int quantity) {
        if (quantity == bulkQty) {
            return bulkPrice;
        }
        return price * quantity;
    }

    @Override
    public boolean equals(Object obj) {
        Item i = (Item) obj;
        return i.name.equals(name);
    }

    public String toString() {
        String output = name + ", $" + price;
        if (bulkPrice != 0.0) {
            output += " (" + bulkQty + " for " + bulkPrice + ")";
        }

        return output;
    }
}
