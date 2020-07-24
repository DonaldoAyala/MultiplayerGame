package conexion;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import logica.*;

public class Servidor implements Runnable{
    private volatile ServerSocket servidor;
    private volatile Socket conexion;
    private volatile Paquete paquete1;
    private volatile Paquete paquete2;
    private int coinX;
    private int coinY;
    private int height;
    private int width;
    
    public Servidor() {
        coinX = 0;
        coinY = 0;
        iniciarSockets();
        start();
    }
    private void iniciarSockets(){
        try{
            servidor = new ServerSocket(1309);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    private void start(){
        Thread hilo = new Thread(this);
        hilo.start();
    }
    @Override
    public void run(){
        int jugador = 1;    
        try{
            while(true){
                conexion = servidor.accept();
                new HiloServidor(conexion, jugador);
                jugador ++;
                System.out.println("Nueva Conexion");
                Thread.sleep(1000);
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }
    class HiloServidor implements Runnable{
        private Socket socket;
        private ObjectInputStream flujoEntrada;
        private ObjectOutputStream flujoSalida;
        private int jugador;
        
        public HiloServidor(Socket socket,int jugador){
            this.socket = socket;
            Thread hilo = new Thread(this);
            this.jugador = jugador;
            hilo.start();
        }
        @Override
        public void run(){
            try{
                System.out.println("Jugador "+jugador+" activo");
                flujoEntrada = new ObjectInputStream(socket.getInputStream());
                flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                
                while(true){    
                    if(jugador == 1){
                        Object objeto = flujoEntrada.readObject();
                        System.out.println("Entrando info del jug 1");
                        paquete1 = (Paquete)objeto;
                        if(paquete1.isCoin()){
                            paquete1.setCoin(false);
                            coinX = (int)(Math.random()*((960)+1));
                            coinY = (int)(Math.random()*((540)+1));
                            System.out.println("CoinX: "+coinX+" CoinY: "+coinY);
                        }
                        paquete1.setCoinX(coinX);
                        paquete1.setCoinY(coinY);
                        flujoSalida.writeObject(paquete2);
                    }else{
                        Object objeto = flujoEntrada.readObject();
                        System.out.println("Entrando info del jug 1");
                        paquete2 = (Paquete)objeto;
                        if(paquete2.isCoin()){
                            paquete2.setCoin(false);
                            coinX = (int)(Math.random()*((960)+1));
                            coinY = (int)(Math.random()*((540)+1));
                            System.out.println("CoinX: "+coinX+" CoinY: "+coinY);
                        }
                        paquete2.setCoinX(coinX);
                        paquete2.setCoinY(coinY);
                        flujoSalida.writeObject(paquete1);
                        System.out.println("Enviando info del jug 2");
                    }
                }
            }catch(IOException ioe){
                ioe.printStackTrace();
            }catch(ClassNotFoundException cnfe){
                cnfe.printStackTrace();
            }
        }
    
    }
    public static void main(String[] args) {
        new Servidor();
    }
}