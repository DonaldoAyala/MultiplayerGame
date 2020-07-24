package vista;
import java.awt.Rectangle;
import logica.Figura;

public class BoundChecker implements Runnable{
    private Panel panel;
    private Rectangle rectangulo;
    private int altura;
    private int ancho;
    
    public BoundChecker(Panel panel){
        this.panel = panel;
        
    }
    public void verificarFronteras(){
        if(rectangulo.getX() < 0 )
            System.out.println("nothing");
    }
    
    @Override
    public void run() {
        try{
            verificarFronteras();
            Thread.sleep(2);
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
            
    }
    
    
}
