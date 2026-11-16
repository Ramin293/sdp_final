package restaurant;

public final class RegularPrice implements DiscountStrategy {
    @Override
    public int total(Order order) {
        return order.subtotal();
    }
}
