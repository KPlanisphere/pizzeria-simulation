
package main; //Paquete principal del programa

import entities.Pedido; //importamos la clase PEDIDO del paquete entities
import utils.Menu; //importamos la clase MENU del paquete utils
import java.util.Scanner; //Leer entrada de teclado

public class PizzeriaSimuladorTEST1 {
    public static void main(String[] args) {
        Pizzeria pizzeria = new Pizzeria(); //Se crea una instancia de la clase "Pizzeria"
        
        // Suponiendo que queremos recibir un cierto número de pedidos
        //Se solicita al usuario que ingrese el numero de pedidos
        System.out.println("Ingrese el número de pedidos:");
        Scanner scanner = new Scanner(System.in); //Objeto SCANNER para leer entrada del usuario
        int numeroPedidos = scanner.nextInt(); //En esta variale se guardaran la cantidad de pedidos
        while (numeroPedidos < 0) {
            System.out.println("[!] ERROR: Por favor, elige una opción mayor a cero: [!]");
            numeroPedidos = scanner.nextInt();
        }
        
        /*
            [!] NOTA [!]
            LA CANTIDAD DE PEDIDOS SERA LO MISMO QUE LA CANTIDAD DE CLIENTES
            QUE ENTRARAN A LA PIZZERIA Y A SU VEZ LA CANTIDAD DE PIZZAS  QUE
            SE PODRAN SOLICITAR, ESTO QUIERE DECIR...
        
            numeroPedidos = clientes [en hilos] = pedidos [en objetos]
        */
        
        Menu.mostrarMenu(); //Se muestra el menu desde la clase "Menu" con el metodo mostrarMenu

        //Se crean la cantidad de pedidos especificados por el usuario
        for (int i = 0; i < numeroPedidos; i++) {
            /*Se crean los pedidos y se les asigna una etiqueta numerica para diferenciarlos
              visualmente (empezando desde el 1)*/
            Pedido pedido = Menu.recibirPedidoDelUsuario(i+1);
            pizzeria.recibirPedido(pedido,i); //Se recibe el pedido y se crea el cliente
        }
        
        //Al abrir la pizzeria comienzan a trabajar los 3 cocineros (cada uno es un hilo distinto)
        pizzeria.abrirPizzeria();
        
        //Evitamos posibles fugas de recursos
        scanner.close();
    }
}
