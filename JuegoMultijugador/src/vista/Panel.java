package vista;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import logica.Figura;
import conexion.Cliente;
import conexion.Paquete;

public class Panel extends JPanel implements Runnable{
    private Figura figura1;
    private Paquete figura2;
    private Cliente cliente;
    private Rectangle coinX;
    private boolean coin;
    
    
    public Panel(){
        coin = true;
        coinX = new Rectangle((int)(Math.random()*((getSize().getWidth())+1)),410,10,10);
        figura1 = new Figura(0,0,20,20,5);
        figura2 = null;
        cliente = new Cliente(new Paquete(0,0,0,0,0,0,0,true));
        disenioPanel();
        agregarEventos();
        Thread hilo = new Thread(this);
        hilo.start();
    }
    public int getPuntaje1(){
        return figura1.getPuntaje();
    }
    public int getPuntaje2(){
        if(figura2 != null){
            return figura2.getPuntaje();
        }
        else
            return 0;
    }
    private void disenioPanel(){
        setBackground(Color.GRAY);
        setOpaque(true);
    }
    private boolean comprobarBordeSuperior(){
        if(figura1.getPosicionY() < 0){
            figura1.detenerVertical();
            return false;
        }
        else
            return true;
    }
    private void comprobarBordeInferior(){
        figura1.reacomodar();
    }
    private boolean comprobarBordeIzquierdo(){
        if(figura1.getPosicionX() < 0){
            figura1.detenerIzquierda();
            return false;
        }
        else
            return true;
    }
    private boolean comprobarBordeDerecho(){
        if(figura1.getPosicionX()> getSize().getWidth()-figura1.getTamanioX() ){
            figura1.detenerDerecha();
            return false;
        }
        else
            return true;
    }
    private void agregarEventos(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int tecla = e.getKeyCode();
                switch(tecla){
                    case KeyEvent.VK_UP:
                            figura1.brincar();
                    break;
                    case KeyEvent.VK_SPACE:
                            figura1.brincar();
                    break;
                    case KeyEvent.VK_RIGHT:
                        if(comprobarBordeDerecho()){
                            figura1.moverDerecha();
                        }
                    break;
                    case KeyEvent.VK_LEFT:
                        if(comprobarBordeIzquierdo()){    
                            figura1.moverIzquierda();
                        }
                    break;
                }
            }
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                int tecla = e.getKeyCode();
                if(tecla == KeyEvent.VK_RIGHT){
                    figura1.detenerDerecha();
                }
                if(tecla == KeyEvent.VK_LEFT ){
                    figura1.detenerIzquierda();
                }
            }
        });
    }
    public void comprobarFronteras(){
        comprobarBordeDerecho();
        comprobarBordeIzquierdo();
        comprobarBordeInferior();
    }
    @Override
    public void run(){
        while(true){
            try{
                comprobarFronteras();
                figura1.setHeight((int)getSize().getHeight());
                comprobarMoneda();
                cliente.enviarPaquete(new Paquete(figura1.getPosicionX(),figura1.getPosicionY(),figura1.getPuntaje(),(int)coinX.x,(int)coinX.y,(int)getSize().getWidth(),(int)getSize().getHeight(),coin));
                if(coin == true)
                    coin = false;
                figura2 = cliente.getPaquete();
                if(figura2 != null){
                    coin = figura2.isCoin();
                    coinX = new Rectangle(figura2.getCoinX(),figura2.getCoinY(),10,10);
                }
                repaint();
                Thread.sleep(3);
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }
    public void comprobarMoneda(){
        if(coinX.intersects(figura1.getPosicionX()+5,figura1.getPosicionY()+5,20,20)){
            coin = true;
            if(figura2 != null)
                coinX = new Rectangle(figura2.getCoinX(),figura2.getCoinY(),10,10);
            figura1.anota();
        }
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.yellow);
        g.fillRect((int)coinX.getX(),(int)coinX.getY(),(int)coinX.getWidth(),(int)coinX.getHeight());
        g.setColor(Color.blue);
        g.fillRect(figura1.getPosicionX(),figura1.getPosicionY(),figura1.getTamanioX(),figura1.getTamanioY());
        g.setColor(Color.red);
        if(figura2 != null)
            g.fillRect(figura2.getPosX(),figura2.getPosY(),figura1.getTamanioX(),figura1.getTamanioY());
    }
}
