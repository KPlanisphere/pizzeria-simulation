
package entities; //Paquete entities del programa

public class Recepcionista implements Runnable {
    // Atributos de la clase
    private final Pedido pedido; 

    // Constructor 
    public Recepcionista(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void run() {
        try {
            // Simular asignación de mesa
            System.out.println("RECEPCIONISTA ASIGNANDO MESA [" + pedido.getNumMesa() + "] AL CLIENTE [" + pedido.getNumCliente() + "]");
            // Espera hasta que el cliente termine de comer
            pedido.esperar();
            Thread.sleep(1700);
            // Simular liberación de mesa
            System.out.println("RECEPCIONISTA LIBERANDO MESA [" + pedido.getNumMesa() + "] AL CLIENTE [" + pedido.getNumCliente() + "]");
            
        } catch (InterruptedException e) {
            //Manejar interrupciones
            Thread.currentThread().interrupt();
            System.out.println("RECEPCIONISTA INTERRUMPIDO");
        }
    }
}

