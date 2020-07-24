package PruebasDinamicas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class VentanaDeFisicas extends JFrame{
    
    public VentanaDeFisicas(){
        inicializarVentana();
    }
    public void inicializarVentana(){
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        int pantallaX = (int)miPantalla.getScreenSize().getWidth();
        int pantallaY = (int)miPantalla.getScreenSize().getHeight();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(pantallaX /4, pantallaY/4,pantallaX/2,pantallaY/2);
        GravedadPanel panel = new GravedadPanel();
        add(panel);
        setVisible(true);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }
}
