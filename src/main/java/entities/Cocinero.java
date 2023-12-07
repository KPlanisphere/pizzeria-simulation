
package entities; //Paquete entities del programa

//Estas clases se utilizan para manejar la cola de pedidos.
import java.util.concurrent.BlockingQueue;

public class Cocinero implements Runnable {
    // Atributos de la clase
    private final BlockingQueue<Pedido> colaPedidos; //Una cola bloqueante de pedidos que el cocinero tomará para preparar.
    private final int NumCo; //Numero asociado al cocinero

    //Constructor
    //Inicializa los atributos de la clase con los valores proporcionados.
    public Cocinero(BlockingQueue<Pedido> colaPedidos,int NumCo) {
        this.colaPedidos = colaPedidos; //Cola actual de pedidos
        this.NumCo = NumCo; //Numero del cocinero
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Pedido pedido = colaPedidos.take(); // Toma un pedido de la cola
                Thread.sleep(600);
                // Preparar orden del cliente
                System.out.println("Cocinero " + "[" + NumCo + "]" + " preparando  orden del Cliente " + "[" + pedido.getNumCliente() + "]" + " <---------- (" + pedido.getTipoPizza() + ")");
                
                // Completar orden del cliente
                Thread.sleep(pedido.getTiempoPreparacion() * 1000); // Simular la preparación
                System.out.println("Cocinero " + "[" + NumCo + "]" + " completando orden del Cliente " + "[" + pedido.getNumCliente() + "]" + " <---------- (" + pedido.getTipoPizza() + ")");
                Thread.sleep(200); //Simula la entrega
                
                pedido.marcarComoListo(); //Despertar recepcionista
                pedido.marcarComoListo(); //Despertar Cliente
            }
        } catch (InterruptedException e) {
            //Manejar interrupciones
            Thread.currentThread().interrupt();
            System.out.println("Cocinero " + "[" + NumCo + "]" + " interrumpido.");
        }
    }
}
