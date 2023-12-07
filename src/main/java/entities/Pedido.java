
package entities; //Paquete entities del programa

public class Pedido {
    // ATRIBUTOS DE UN PEDIDO
    private final String tipoPizza;     //Tipo de la pizza actual
    private final int tiempoPreparacion;//Tiempo de preparacion de la pizza actual
    private boolean estaListo;          //Variable para los cocineros, indica cuando la pizza esta lista y peude ser entregada
    private int numCliente;             //Etiqueta del cliente a quien le corresponde la pizza
    private float precioPizza;          //Precio de la pizza actual
    private int numMesa;
    
    // CONSTRUCTOR 
    // Inicializamos los atributos de la clase pedido
    public Pedido(String tipoPizza, int tiempoPreparacion, int numCliente, float precioPizza, int numMesa) {
        this.tipoPizza = tipoPizza;
        this.tiempoPreparacion = tiempoPreparacion;
        this.estaListo = false;
        this.numCliente = numCliente;
        this.precioPizza = precioPizza;
        this.numMesa = numMesa;
    }
    
    //Obtener el numero de la mesa
    public int getNumMesa(){
        return numMesa;
    }
    
    //Obtener el precio de la pizza
    public float getPrecioPizza(){
        return precioPizza;
    }
    
    //Obtener la etiqueta del cliente
    public int getNumCliente(){
        return numCliente;
    }
    
    //Obetener el tipo de pizza
    public String getTipoPizza() {
        return tipoPizza;
    }

    //Obtener el tiempo de preparacion
    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    //Metodo de prueba (ignorar)
    public synchronized boolean isEstaListo() {
        return estaListo;
    }

    //Metodo especial para los cocineros.
    //Indica cuando un cocinero termina de preparar una pizza y esta esta lista para ser recogida por el cliente
    public synchronized void marcarComoListo() {
        this.estaListo = true; // Marcar el pedido como listo
        notify(); // Notifica a todos los hilos en espera que el estado ha cambiado.
                  // Notifica a todos los hilos que estan esperando (wait) que el estado ha cambiado
    }

    //Metodo especial para los clientes
    //Indica cuando el hilo entrara en espera para que el cocinero prepare su pizza, esto cambiara hasta que elk
    //cocinero indique que termino de preparar la pizza
    public synchronized void esperar() throws InterruptedException {
        while (!estaListo) { // Mientras el pedido no esté listo, el hilo espera
            wait();
        }
    }
}
