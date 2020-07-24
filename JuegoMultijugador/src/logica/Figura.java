package logica;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

public class Figura implements Runnable,Serializable{
    private volatile int posicionX;
    private volatile int posicionY;
    private int tamanioX;
    private int tamanioY;
    private volatile int velocidadX;
    private volatile float velocidadY;
    private boolean derecha;
    private boolean izquierda;
    private int height;
    private int puntaje;
    
    public Figura(){
        puntaje = 0;
        posicionX = 0;
        posicionY = 0;
        tamanioX = 50;
        tamanioY = 50;
        velocidadX = 0;
        velocidadY = 0;
        derecha = false;
        izquierda = false;
        Thread hilo = new Thread(this);
        hilo.start();
    }
    public Figura(int posicionX, int posicionY, int tamanioX, int tamanioY, int velocidad) {
        puntaje = 0;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.tamanioX = tamanioX;
        this.tamanioY = tamanioY;
        velocidadX = 0;
        velocidadY = 0;
        height = 200;
        Thread hilo = new Thread(this);
        hilo.start();
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void gravedad(){
        velocidadY += 0.25;
        posicionY += (int)velocidadY;
    }
    public void anota(){
        puntaje++;
    }
    public int getPuntaje(){
        return puntaje;
    }
    public void brincar(){
            velocidadY = -8;
    }
    private void moverFiguraDerecha(){
        if(derecha)
            posicionX += 2;
    }
    private void moverFiguraIzquierda(){
        if(izquierda)
            posicionX += -2;
    }
    public void moverDerecha(){
        derecha = true;
    }
    public void moverIzquierda(){
        izquierda = true;
    }
    public void detenerIzquierda(){
        izquierda = false;
    }
    public void detenerDerecha(){
        derecha = false;
    }
    public int getPosicionX(){
        return posicionX;
    }
    public int getPosicionY(){
        return posicionY;
    }
    public int getTamanioX(){
        return tamanioX;
    }
    public int getTamanioY(){
        return tamanioY;
    }
    public void detenerVertical(){
        if(velocidadY > 0 || velocidadY<0 )
            velocidadY = 0;
    }
    public void reacomodar(){
        if(posicionY > height - tamanioY){
            posicionY = height-tamanioY;
            velocidadY = 0;
        }
        
    }
    @Override
    public void run() {
        while(true){
            try {
                moverFiguraDerecha();
                moverFiguraIzquierda();
                gravedad();
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}