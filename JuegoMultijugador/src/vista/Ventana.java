package vista;

import java.awt.*;
import javax.swing.*;
import java.awt.Toolkit;

public class Ventana extends JFrame implements Runnable{
    private Panel panel;
    private JLabel label1;
    private JLabel label2;
    
    public Ventana(){
        agregarPuntajes();
        configurarVentana();
        agregarPanel();
        Thread hilo = new Thread(this);
        hilo.start();
    }
    private void agregarPuntajes(){
        label1 = new JLabel("Jugador 1: "+0);
        label2 = new JLabel("Jugador 1: "+0);
        setLayout(new BorderLayout());
        JPanel puntajes = new JPanel();
        puntajes.setLayout(new BorderLayout());
        JPanel j1 = new JPanel();
        JPanel j2 = new JPanel();
        j1.setLayout(new BorderLayout());
        j1.add(label1);
        j2.setLayout(new BorderLayout());
        j2.add(label2);
        puntajes.add(j1,BorderLayout.WEST);
        puntajes.add(j2,BorderLayout.EAST);
        add(puntajes,BorderLayout.NORTH);
    }
    private void agregarPanel(){
        panel = new Panel();
        add(panel,BorderLayout.CENTER);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }
    private void configurarVentana(){
        int tamanioPantallaX , tamanioPantallaY;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        tamanioPantallaX = (int)pantalla.getScreenSize().getSize().getWidth();
        tamanioPantallaY = (int)pantalla.getScreenSize().getSize().getHeight();
        setBounds(tamanioPantallaX/4,tamanioPantallaY/4,tamanioPantallaX/2,tamanioPantallaY/2);
        setVisible(true);
    }

    @Override
    public void run() {
        try{
            while(true){
                label1.setText("Jugador 1: "+panel.getPuntaje1());
                label2.setText("Jugador 2: "+panel.getPuntaje2());
                Thread.sleep(10);
            }
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }
}
