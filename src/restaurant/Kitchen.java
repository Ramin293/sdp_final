package restaurant;

public final class Kitchen implements OrderObserver {
    @Override
    public void statusChanged(int orderNumber, OrderStatus status) {
        System.out.printf("Kitchen: order #%d is %s%n", orderNumber, status);
    }
}
