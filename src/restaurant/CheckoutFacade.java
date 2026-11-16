package restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class CheckoutFacade {
    public record Receipt(int number, int total, OrderStatus status) {}

    private final OrderRegistry registry = new OrderRegistry();

    private final List<OrderObserver> observers = new ArrayList<>();

    public void subscribe(OrderObserver observer) {
        observers.add(Objects.requireNonNull(observer));
    }

    public Receipt placeOrder(Order order, DiscountStrategy strategy) {
        int total = strategy.total(order);
        int number = registry.register(order, total);
        notifyObservers(number, OrderStatus.ACCEPTED);
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
        notifyObservers(number, next);
        return new Receipt(number, entry.total(), next);
    }

    private void notifyObservers(int number, OrderStatus status) {
        for (OrderObserver observer : observers) {
            observer.statusChanged(number, status);
        }
    }
}
