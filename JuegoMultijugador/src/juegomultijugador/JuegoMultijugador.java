package juegomultijugador;
import vista.*;
public class JuegoMultijugador {
    public static void main(String[] args) {
        Runtime sistema = Runtime.getRuntime();
        long inicial = sistema.freeMemory();
        System.out.println(sistema.availableProcessors());
        new Ventana();
        System.out.println(sistema.availableProcessors());
    }
}
