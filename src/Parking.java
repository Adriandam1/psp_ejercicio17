import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja la lógica del aparcamiento.
 * Controla las plazas disponibles y la entrada/salida de los coches.
 */
public class Parking {
    private final int maxPlazas;
    private final List<Integer> plazas;  //plazas ocupadas

    /**
     * Constructor de la clase.
     * @param maxPlazas el número máximo de plazas del aparcamiento.
     */
    public Parking(int maxPlazas) {
        this.maxPlazas = maxPlazas;
        this.plazas = new ArrayList<>();
    }

    /**
     * Metodo que simula la lógica de aparcar el coche en una plaza.
     * Si hay espacio, el coche aparca; si no, espera.
     * @param idCoche el ID del coche que quiere aparcar.
     * @throws InterruptedException si el hilo se interrumpe.
     */
    public synchronized void logicaAparcar(int idCoche) throws InterruptedException {
        // Espera mientras no haya plazas disponibles
        while (plazas.size() >= maxPlazas) {
            wait();
        }
        // Añadimos el coche a una plaza
        plazas.add(idCoche);
        System.out.println("ENTRADA: Coche " + idCoche + " aparca.");
        System.out.println("Plazas libres: " + (maxPlazas - plazas.size()));
        System.out.println(getInformationPlazas(plazas));
        notifyAll();  // Notificamos a los hilos esperando
    }

    /**
     * Metodo que simula la salida de un coche.
     * El coche debe estar en el aparcamiento para poder salir.
     * @param idCoche el ID del coche que quiere salir.
     * @throws InterruptedException si el hilo se interrumpe.
     */
    public synchronized void logicaSalir(int idCoche) throws InterruptedException {
        // Espera el coche este
        while (!plazas.contains(idCoche)) {
            wait();
        }
        // Eliminamos el coche de la lista de plazas ocupadas
        plazas.remove(Integer.valueOf(idCoche));
        System.out.println("SALIDA: Coche " + idCoche + " sale.");
        System.out.println("Plazas libres: " + (maxPlazas - plazas.size()));
        System.out.println(getInformationPlazas(plazas));
        notifyAll();
    }

    /**
     * Metodo auxiliar para obtener la información de las plazas del aparcamiento.
     * @param numPlazas la lista de coches aparcados.
     * @return una cadena con el estado del aparcamiento.
     */
    public String getInformationPlazas(List<Integer> numPlazas){
        return "Parking: " + numPlazas.size() + "/20";  // Imprime el número de plazas ocupadas y el total
    }
}
