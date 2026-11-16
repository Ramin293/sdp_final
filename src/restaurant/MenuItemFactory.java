package restaurant;

public final class MenuItemFactory {
    public enum Dish {
        BURGER, PIZZA, SALAD
    }

    public MenuItem create(Dish dish) {
        return switch (dish) {
            case BURGER -> new BasicDish("Burger", 1800);
            case PIZZA -> new BasicDish("Pizza", 2600);
            case SALAD -> new BasicDish("Salad", 1400);
        };
    }

    private record BasicDish(String description, int price) implements MenuItem {}
}
