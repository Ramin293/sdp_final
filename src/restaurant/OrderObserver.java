package restaurant;

public interface OrderObserver {
    void statusChanged(int orderNumber, OrderStatus status);
}
