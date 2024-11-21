




public class Main {

    public static void main(String[] args) {
        Coches coches = new Coches();

        // Parking 20 plazas disponibles
        Parking parking = new Parking(20);

        // Cada hilo simula la entrada y salida de los coches en el aparcamiento
        for (int i = 1; i <= coches.getNumCoches(); i++) {
            // Iniciamos el hilo para cada coche con su correspondiente ID
            new Thread(new Hilo(parking, i)).start();
        }
    }
}
