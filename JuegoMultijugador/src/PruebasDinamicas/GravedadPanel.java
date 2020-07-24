package PruebasDinamicas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GravedadPanel extends JPanel implements Runnable{
    private UbicacionFigura figura;
    private volatile int x;
    private volatile int y;
    private float velocidad;
    private volatile boolean derecha;
    private volatile boolean izquierda;
    
    public GravedadPanel(){
        int windowSizeX = (int)getSize().getWidth();
        int windowSizeY = (int)getSize().getHeight();
        x = 0;
        y = 0;
        velocidad = 0;
        figura = new UbicacionFigura(windowSizeX/2,windowSizeY/2,20,20,0);
        setBackground(Color.black);
        setOpaque(true);
        agregarEventos();
        Thread hilo = new Thread(this);
        hilo.start();
        derecha = false;
        izquierda = false;
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.red);
        g.fillOval(x,y,20,20);
        
    }
    public void gravedad(){
        if(y >= getSize().getHeight()-20 && velocidad >= 0){
            velocidad *= -.93;            
        }else if( y < 0 ){
            velocidad *= -.93;
        }
        velocidad += 0.2;
            y += (int)velocidad;
    }
    public void derecha(){
        if(derecha)
            x += 2;
    }
    public void izquierda(){
        if(izquierda)
            x += -2;
    }
    @Override
    public void run(){
        try{
            while(true){
                repaint();
                gravedad();
                derecha();
                izquierda();
                Thread.sleep(10);
            }
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }

    private void agregarEventos(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int tecla = e.getKeyCode();
                switch(tecla){
                    case KeyEvent.VK_RIGHT:
                        derecha = true;
                    break;
                    case KeyEvent.VK_LEFT:
                        izquierda = true;
                    break;
                    case KeyEvent.VK_SPACE:
                        if(velocidad < 0)
                            velocidad += -15;
                        else if(velocidad > 0)
                            velocidad += 15;
                        else
                            velocidad = 15;
                    break;
                }
            }
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                int tecla = e.getKeyCode();
                switch(tecla){
                    case KeyEvent.VK_RIGHT:
                        derecha = false;
                    break;
                    case KeyEvent.VK_LEFT:
                        izquierda = false;
                    break;
                }
            }
        });
    }
    
    
}
