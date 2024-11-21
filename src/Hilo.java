import java.util.Random;

/**
 * Clase hilo que implementa la interfaz Runnable.
 * Cada hilo representa el comportamiento de un coche entrando y saliendo del aparcamiento.
 */
public class Hilo implements Runnable {
    private final Parking parking;
    private final int idCoche;      //

    /**
     * Constructor de la clase.
     * @param parking el aparcamiento donde aparca el coche.
     * @param idCoche el identificador del coche.
     */
    public Hilo(Parking parking, int idCoche) {
        this.parking = parking;
        this.idCoche = idCoche;
    }

    /**
     * Metodo que define lo que hace el hilo (coche) cuando se ejecuta.
     */
    @Override
    public void run() {
        // Bucle INFINITO que simula la entrada y salida del coche
        while (true) {
            try {
                parking.logicaAparcar(idCoche);
                // tiempo aleatorio
                Thread.sleep(new Random().nextInt(1000) + 3000);
                parking.logicaSalir(idCoche);
                // Elcoche espera aleatoriamente antes volver a entrar
                Thread.sleep(new Random().nextInt(1000) + 3000);
            } catch (InterruptedException e) {
                System.out.println("Coche " + idCoche + " ha sido interrumpido.");
            }
        }
    }
}
