package restaurant;

public final class CheckoutFacade {
    public record Receipt(int number, int total, OrderStatus status) {}

    private final OrderRegistry registry = new OrderRegistry();

    public Receipt placeOrder(Order order, DiscountStrategy strategy) {
        int total = strategy.total(order);
        int number = registry.register(order, total);
        return new Receipt(number, total, OrderStatus.ACCEPTED);
    }

    public Receipt advanceStatus(int number) {
        OrderRegistry.Entry entry = registry.find(number);
        OrderStatus next = switch (entry.status()) {
            case ACCEPTED -> OrderStatus.PREPARING;
            case PREPARING -> OrderStatus.READY;
            case READY -> OrderStatus.COMPLETED;
            case COMPLETED -> throw new IllegalStateException("Order already completed: " + number);
        };
        registry.updateStatus(number, next);
        return new Receipt(number, entry.total(), next);
    }
}
