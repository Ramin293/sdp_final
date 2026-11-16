package restaurant;

public final class Main {
    public static void main(String[] args) {
        MenuItemFactory factory = new MenuItemFactory();

        for (MenuItemFactory.Dish dish : MenuItemFactory.Dish.values()) {
            MenuItem item = factory.create(dish);
            System.out.printf("%s: %d KZT%n", item.description(), item.price());
        }

        MenuItem burger = factory.create(MenuItemFactory.Dish.BURGER);
        MenuItem cheeseburger = new Topping(burger, Topping.Extra.CHEESE);
        MenuItem burgerWithSauce = new Topping(cheeseburger, Topping.Extra.SAUCE);
        System.out.printf("%s: %d KZT%n", cheeseburger.description(), cheeseburger.price());
        System.out.printf("%s: %d KZT%n", burgerWithSauce.description(), burgerWithSauce.price());

        Order order = new Order.Builder()
                .addItem(burgerWithSauce)
                .addItem(factory.create(MenuItemFactory.Dish.SALAD))
                .fulfillment(Order.Fulfillment.DINE_IN)
                .note("Serve the salad first")
                .build();

        System.out.println("Order:");
        for (MenuItem item : order.items()) {
            System.out.printf("  %s: %d KZT%n", item.description(), item.price());
        }
        System.out.printf("Subtotal: %d KZT%n", order.subtotal());
        System.out.println("Fulfillment: " + order.fulfillment());
        System.out.println("Note: " + order.note());
    }
}
