package restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Order {
    public enum Fulfillment {
        PICKUP, DINE_IN
    }

    private final List<MenuItem> items;
    private final Fulfillment fulfillment;
    private final String note;

    private Order(Builder builder) {
        items = List.copyOf(builder.items);
        fulfillment = builder.fulfillment;
        note = builder.note;
    }

    public List<MenuItem> items() {
        return items;
    }

    public Fulfillment fulfillment() {
        return fulfillment;
    }

    public String note() {
        return note;
    }

    public int subtotal() {
        return items.stream().mapToInt(MenuItem::price).sum();
    }

    public static final class Builder {
        private final List<MenuItem> items = new ArrayList<>();
        private Fulfillment fulfillment = Fulfillment.PICKUP;
        private String note = "";

        public Builder addItem(MenuItem item) {
            items.add(Objects.requireNonNull(item, "item"));
            return this;
        }

        public Builder fulfillment(Fulfillment fulfillment) {
            this.fulfillment = Objects.requireNonNull(fulfillment, "fulfillment");
            return this;
        }

        public Builder note(String note) {
            this.note = Objects.requireNonNull(note, "note");
            return this;
        }

        public Order build() {
            if (items.isEmpty()) {
                throw new IllegalStateException("Add at least one item before building an order.");
            }
            return new Order(this);
        }
    }
}
