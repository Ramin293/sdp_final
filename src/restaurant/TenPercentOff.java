package restaurant;

public final class TenPercentOff implements DiscountStrategy {
    @Override
    public int total(Order order) {
        return (int) (order.subtotal() * 90L / 100);
    }
}
