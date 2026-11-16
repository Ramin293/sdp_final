# Restaurant Ordering System

A Java 17 console project for Software Design Patterns. The demo builds an order with a burger, cheese, sauce, and a salad. It checks out the same order at two prices and shows how the kitchen and customer get status updates.

## Run the demo

Install JDK 17 and open a terminal in the repository folder. The project has no external dependencies.

PowerShell

```powershell
javac --release 17 -d out (Get-ChildItem src/restaurant/*.java).FullName
java -cp out restaurant.Main
```

Bash

```bash
javac --release 17 -d out src/restaurant/*.java
java -cp out restaurant.Main
```

The first order costs **3,330 KZT** with a 10% discount. The second costs **3,700 KZT** at the regular price. For each order, the kitchen and customer get updates as the status moves through `ACCEPTED`, `PREPARING`, `READY`, and `COMPLETED`.

## Where to start in the code

[Main.java](src/restaurant/Main.java) runs the demo. It builds an `Order`, then passes it and a discount strategy to `CheckoutFacade`.

| Pattern | How the project uses it |
| --- | --- |
| Factory | `MenuItemFactory` creates a dish from the selected type. |
| Decorator | `Topping` adds an ingredient to an item's description and price. |
| Builder | `Order.Builder` collects dishes, a fulfillment option, and a note. |
| Strategy | `RegularPrice` and `TenPercentOff` calculate the total. |
| Facade | `CheckoutFacade` places orders and updates their status. |
| Observer | `Kitchen` and `Customer` subscribe to order status updates. |

[Project report with UML diagrams](report.pdf)
