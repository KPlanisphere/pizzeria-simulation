
package main; //Paquete principal del programa

//Importamos CLIENTE, PEDIDO Y COCINERO del paquete entities
import entities.Cliente;
import entities.Pedido;
import entities.Cocinero;
import entities.Recepcionista;

//Estas clases se utilizan para manejar la cola de pedidos.
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Pizzeria {
    //Declaramos una cola bloqueante llamada colaPedidos que contendra objetos de
    //tipo Pedido. Esta cola se usara para coordinar la comunicacion entre los
    //clientes y los cocineros
    private final BlockingQueue<Pedido> colaPedidos = new LinkedBlockingQueue<>();

    public void abrirPizzeria() {
        // Crear cocineros y empezar sus hilos
        for (int i = 0; i < 3; i++) { // Suponiendo 3 cocineros
            //Se crean los cocineros, se les asigna la cola de pedidos y una etiqueta
            //numerica para diferenciarlos visualmente (comenzando desde el 1)
            Cocinero cocinero = new Cocinero(colaPedidos,i+1);
            //Iniciamos el hilo para que el cocinero empiece a trabajar
            new Thread(cocinero).start();
        }
    }

    public void recibirPedido(Pedido pedido,int Num) { //recibe un pedido y la etiqueta del mismo
        //Se agrega el pedido a la cola de pedidos usando add de la interfaz BloquingQueue
        //Esta cola se utiliza para coordinar la comunicación entre los clientes y los cocineros. 
        //Cuando se agrega un pedido a la cola, los cocineros pueden tomarlo de la cola y comenzar a procesarlo.
        colaPedidos.add(pedido);
        //Se crea un objeto Cliente pasandole el pedido y el numero asociado a este
        Cliente cliente = new Cliente(pedido);
        //Se inicia el hilo recien creado para el cliente para que pueda ver el menu y pedir algo
        new Thread(cliente).start();
    }
}
