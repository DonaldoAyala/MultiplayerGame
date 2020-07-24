package conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class Cliente {
    private Socket socket;
    private Paquete paquete2;
    private ObjectOutputStream flujoSalida;
    private ObjectInputStream flujoEntrada;
    
    public Cliente(Paquete paquete1){
        crearSockets();
    }
    private void crearSockets(){
        try{
            socket = new Socket("10.100.75.19",1309);
            flujoSalida = new ObjectOutputStream(socket.getOutputStream());
            flujoEntrada = new ObjectInputStream(socket.getInputStream());
        }catch(UnknownHostException uhe){
            uhe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    public void enviarPaquete(Paquete paquete){
        try{
            flujoSalida.writeObject(paquete);
            Object objeto = flujoEntrada.readObject();
            if(objeto != null)
                paquete2 = (Paquete)objeto;
        }catch(IOException ioe){
            ioe.printStackTrace();
        }catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
    }
    public Paquete getPaquete(){
        return paquete2;
    }
}
