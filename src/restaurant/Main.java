package restaurant;

public final class Main {
    public static void main(String[] args) {
        MenuItemFactory factory = new MenuItemFactory();

        for (MenuItemFactory.Dish dish : MenuItemFactory.Dish.values()) {
            MenuItem item = factory.create(dish);
            System.out.printf("%s: %d KZT%n", item.description(), item.price());
        }
    }
}
