package conexion;
import java.io.Serializable;
import logica.*;


public class Paquete implements Serializable{
    private int posx;
    private int posy;
    private int puntaje;
    private int coinX;
    private int coinY;
    private int width;
    private int height;
    private boolean coin;
    
    public Paquete(int posx,int posy,int puntaje,int coinX,int coinY, int height,int width,boolean coin){
        this.posx = posx;
        this.posy = posy;
        this.puntaje = puntaje;
        this.coinX = coinX;
        this.coinY = coinY;
        this.height = height;
        this.width = width;
        this.coin = coin;
    }

    public boolean isCoin() {
        return coin;
    }

    public void setCoin(boolean coin) {
        this.coin = coin;
    }
    
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getCoinX() {
        return coinX;
    }

    public void setCoinX(int coinX) {
        this.coinX = coinX;
    }

    public int getCoinY() {
        return coinY;
    }

    public void setCoinY(int coinY) {
        this.coinY = coinY;
    }
    
    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    public void setFigura(int posx, int posy){
        this.posx = posx;
        this.posy = posy;
    }
    public int getPosX(){
        return posx;
    }
    public int getPosY(){
        return posy;
    }
}
