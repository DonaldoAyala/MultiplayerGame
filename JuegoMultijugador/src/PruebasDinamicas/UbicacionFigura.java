package PruebasDinamicas;


public class UbicacionFigura {
    private int x;
    private int y;
    private int sizeX;
    private int sizeY;
    private int velocidad;

    public UbicacionFigura(int x, int y, int sizeX, int sizeY,int velocidad) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.velocidad = velocidad;
    }
    
    public void walkY(int distancia){
        y += distancia;
    }
    public void walkX(int distancia){
        x += distancia;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
