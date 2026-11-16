package restaurant;

public final class Customer implements OrderObserver {
    @Override
    public void statusChanged(int orderNumber, OrderStatus status) {
        System.out.printf("Customer: order #%d is %s%n", orderNumber, status);
    }
}
