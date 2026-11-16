package restaurant;

import java.util.Objects;

public final class Topping implements MenuItem {
    public enum Extra {
        CHEESE("cheese", 350),
        SAUCE("sauce", 150);

        private final String name;
        private final int price;

        Extra(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }

    private final MenuItem item;
    private final Extra extra;

    public Topping(MenuItem item, Extra extra) {
        this.item = Objects.requireNonNull(item, "item");
        this.extra = Objects.requireNonNull(extra, "extra");
    }

    @Override
    public String description() {
        return item.description() + " + " + extra.name;
    }

    @Override
    public int price() {
        return item.price() + extra.price;
    }
}
