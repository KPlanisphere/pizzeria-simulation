
package utils; //Paquete entities del programa

import java.util.Scanner; //Leer entrada de teclado
import entities.Pedido; //Importamos la clase PEDIDO del paquete entities
// Clases para generar el numero aleatorio
import java.util.HashSet; //Coleccion que no permite numeros duplicados
import java.util.Random; //Crear numeros aleatorios
import java.util.Set; //Conjunto de colecciones no repetidas

public class Menu {
    private static final Scanner scanner = new Scanner(System.in); //Objeto para leer entrada de teclado

    // Imprimir el menu de la pizzeria en consola.
    public static void mostrarMenu() {
        System.out.println("                     PIZZERIA PLANISPHERE");
        System.out.println("                     las mejores de Mexico\n");
        System.out.println("      [1] Aceituna.................................9.99");
        System.out.println("      [2] Pera.....................................6.99");
        System.out.println("      [3] Platano.................................12.99");
        System.out.println("\nPor favor, elige el tipo de pizza (1-3):");
    }

    // Recibir desiciones del usuario y crear pedido
    public static Pedido recibirPedidoDelUsuario(int i) {
        int eleccion = scanner.nextInt(); //Se guarda el tipo de pizza elegido por el usuario
        //El usuario solo debe de seleccionar entre 3 opciones
        while (eleccion < 1 || eleccion > 3) {
            System.out.println("[!] ERROR: Por favor, elige una opción entre 1 y 3: [!]");
            eleccion = scanner.nextInt();
        }
        // guardamos el tiempo de preparacion de cada pedido ya que cada pizza tinene  un
        // tiempo de preaparacion distinto, esto lo hacemos con la funcion calcularTiempoPreparacion()
        int tiempoPreparacion = calcularTiempoPreparacion(eleccion);
        // guardamos el tipo de pizza de cada pedido ya que cada pizza tinene  un
        // tiempo de preaparacion distinto, esto lo hacemos con la funcion obtenerNombrePizza()
        String tipoPizza = obtenerNombrePizza(eleccion);
        // Variable para la etiqueta del pedido actual, con esto podremos diferenciar a los clientes
        // visualmente en consola
        int numCliente = i;
        // Cada pizza tiene un precio distinto, en esta variable se guardara el precio de la pizza
        // elegida en el pedido
        float precioPizza = obtenerPrecioPizza(eleccion);
        
        int numMesa = i % 3;
        
        // retornamos el pedido
        return new Pedido(tipoPizza, tiempoPreparacion, numCliente, precioPizza, numMesa + 1);
    }

    // Este método privado calcula el tiempo de preparación de la pizza seleccionada por el usuario.
    private static int calcularTiempoPreparacion(int eleccion) {
        switch (eleccion) {
            case 1: return 10; // Tiempo de preparación para Pizza Aceitunas
            case 2: return 12; // Tiempo de preparación para Pizza Pera
            case 3: return 15; // Tiempo de preparación para Pizza Platano
            default: return 10;
        }
    }
    
    // Este método privado obtiene el nombre de la pizza seleccionada por el usuario.
    private static String obtenerNombrePizza(int eleccion) {
        switch (eleccion) {
            case 1: return "Pizza de aceitunas";
            case 2: return "Pizza de Pera";
            case 3: return "Pizza de Platano";
            default: return "Pizza de Peperoni";
        }
    }
    
    // Este método privado obtiene el precio de la pizza seleccionada por el usuario
    private static float obtenerPrecioPizza(int eleccion){
        switch (eleccion){
            case 1: return (float)  9.99;
            case 2: return (float) 6.99;
            case 3: return (float) 12.99;
            default: return 0;
        }
    }
    
    // Función para generar un número aleatorio no repetido dentro de un rango
    private static int generarNumeroNoRepetido() {
        // Creamos un conjunto para almacenar los números ya generados
        Set<Integer> numerosGenerados = new HashSet<>();

        // Creamos un generador de números aleatorios
        Random random = new Random();

        // Generamos un número aleatorio y verificamos si ya ha sido generado
        int numeroAleatorio;
        do {
            numeroAleatorio = random.nextInt(3) + 1; // Genera números entre 1 y 3
        } while (numerosGenerados.contains(numeroAleatorio));

        // Agregamos el número generado al conjunto
        numerosGenerados.add(numeroAleatorio);

        // Devolvemos el número aleatorio sin repetición
        return numeroAleatorio;
    }
}
