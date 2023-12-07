
package entities; //Paquete entities del programa
import entities.Recepcionista; //importamos la clase RECEPCIONISTA del paquete entities

public class Cliente implements Runnable {
    // Atributos de la clase
    private final Pedido pedido;
    
    // Constructor 
    public Cliente(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void run() {
        try {
            // El recepcionista otorga una mesa
            // Se crea un recepcionista pasandole el pedido
            Recepcionista recepcionista = new Recepcionista(pedido);
            // Se inicia el hilo creado para que el recepcionista atienda al cliente
            new Thread(recepcionista).start();
            
            // Mirar el menu
            Thread.sleep(200);
            System.out.println("Cliente  " + "[" + pedido.getNumCliente() + "]" + " mirando el menu...");
            Thread.sleep(200); // Simular espera
             
            // Hacer el pedido
            System.out.println("Cliente  " + "[" + pedido.getNumCliente() + "]" + " pidiendo:   " + pedido.getTipoPizza());

            // Esperar a que el pedido esté listo
            pedido.esperar(); 
            System.out.println("Cliente  " + "[" + pedido.getNumCliente() + "]" + " recibiendo  su pedido " + " <--------------------- (" + pedido.getTipoPizza() + ")");
            
            // Pagar el pedido
            Thread.sleep(400);
            System.out.println("Cliente  " + "[" + pedido.getNumCliente() + "]" + " ha pagado " + pedido.getPrecioPizza());
            
            // Simular tiempo comiendo
            Thread.sleep(1000); // Simular comiendo
            System.out.println("Cliente  " + "[" + pedido.getNumCliente() + "]" + " terminando  de comer");

        } catch (InterruptedException e) {
            //Manejar interrupciones
            Thread.currentThread().interrupt();
            System.out.println("Cliente  " + "[" + pedido.getNumCliente() + "]" + " interrumpido.");
        } 
    }
}

