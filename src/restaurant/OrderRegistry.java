package restaurant;

import java.util.HashMap;
import java.util.Map;

final class OrderRegistry {
    record Entry(Order order, OrderStatus status, int total) {}

    private final Map<Integer, Entry> orders = new HashMap<>();
    private int nextNumber = 1;

    int register(Order order, int total) {
        int number = nextNumber++;
        orders.put(number, new Entry(order, OrderStatus.ACCEPTED, total));
        return number;
    }

    Entry find(int number) {
        Entry entry = orders.get(number);
        if (entry == null) {
            throw new IllegalArgumentException("Unknown order: " + number);
        }
        return entry;
    }

    void updateStatus(int number, OrderStatus status) {
        Entry old = find(number);
        orders.put(number, new Entry(old.order(), status, old.total()));
    }
}
