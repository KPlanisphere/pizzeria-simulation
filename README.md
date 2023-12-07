# Pizzeria Simulation System

### Description

This project simulates the operations of a pizzeria, handling customer orders and the cooking process through concurrent programming. The system includes various entities such as customers, cooks, orders, and a receptionist. The main program coordinates these entities to simulate a real-life pizzeria. The simulation demonstrates a multi-threaded environment where multiple cooks can process orders concurrently.

### Project Structure

- **main:**
  - `Pizzeria.java`: The core class managing the pizzeria operations, including the handling of orders and the initiation of cook threads. This class initializes a blocking queue to handle the orders and spawns cook threads.
  - `PizzeriaSimuladorTEST1.java`: The simulation class that demonstrates the functionalities of the pizzeria system by generating orders and managing the overall flow. It prompts users to enter pizza orders and simulates the process of receiving and cooking these orders.

- **entities:**
  - `Cliente.java`: Represents a customer, handling their order and interaction with the pizzeria. Customers are created with their respective orders and can place these orders with the pizzeria.
  - `Cocinero.java`: Represents a cook, responsible for preparing the orders taken from the queue. Each cook runs in a separate thread and processes orders from the blocking queue.
  - `Pedido.java`: Represents an order placed by a customer. This class includes details about the type of pizza and the time required for preparation.
  - `Recepcionista.java`: Represents the receptionist who handles the incoming customers and their orders. The receptionist interacts with customers and adds their orders to the queue.

- **utils:**
  - `Menu.java`: Contains the menu details, including available pizzas and their prices. This utility class provides methods to retrieve pizza information based on customer choices.

### Notable Code Snippets

#### Pizzeria.java
This class manages the pizzeria operations, including receiving orders and managing the queue of orders for cooks to process.

```java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Pizzeria {
    private final BlockingQueue<Pedido> colaPedidos = new LinkedBlockingQueue<>();

    public void abrirPizzeria() {
        for (int i = 0; i < 3; i++) { // Assuming 3 cooks
            Cocinero cocinero = new Cocinero(colaPedidos, i + 1);
            new Thread(cocinero).start();
        }
    }

    public void recibirPedido(Pedido pedido, int Num) {
        colaPedidos.add(pedido);
        Cliente cliente = new Cliente(pedido);
        new Thread(cliente).start();
    }
}
```

This snippet shows the creation of cook threads and the addition of customer orders to a blocking queue, facilitating concurrent order processing.

#### PizzeriaSimuladorTEST1.java

This class simulates the operations of the pizzeria, demonstrating how orders are handled and processed.

```java
import java.util.Scanner;

public class PizzeriaSimuladorTEST1 {
    public static void main(String[] args) {
        Pizzeria pizzeria = new Pizzeria();
        pizzeria.abrirPizzeria();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the type of pizza (1: Aceitunas, 2: Pera, 3: Platano):");
            int tipoPizza = scanner.nextInt();
            Pedido pedido = new Pedido(tipoPizza);
            pizzeria.recibirPedido(pedido, tipoPizza);
        }
    }
}
``` 

This snippet demonstrates the main simulation loop, where user input is taken to create orders that are processed by the pizzeria.

### Setup and Usage

#### Prerequisites

-   Java 8 or higher
-   Maven 3.6.3 or higher

#### Clone the Repository

```sh
git clone https://github.com/KPlanisphere/pizzeria-simulation.git
cd pizzeria-simulation
```

#### Build the Project

```sh
mvn clean install
```

#### Run the Simulation

```sh
mvn exec:java -Dexec.mainClass="main.PizzeriaSimuladorTEST1"
```

### Detailed Class Descriptions

#### Cliente.java

Handles customer interactions and the placing of orders. Each `Cliente` instance is associated with a `Pedido` and runs in its own thread.

#### Cocinero.java

Processes orders from the blocking queue. Each `Cocinero` instance runs in a separate thread, picks orders from the queue, and simulates the cooking process.

#### Pedido.java

Encapsulates order details, including the type of pizza and the preparation time.

#### Recepcionista.java

Manages customer interactions and adds orders to the queue, ensuring smooth operation between customer order placement and cook processing.

#### Menu.java

Provides information on the available pizzas, their names, preparation times, and prices. This class is used by other entities to retrieve menu information.