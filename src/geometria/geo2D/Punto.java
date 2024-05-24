package geometria.geo2D;

import java.text.DecimalFormat;

public class Punto {
    //attributi
    private final double x;
    private final double y;
    //costruttori
    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }
    //metodi
    public double x() { return x; }
    public double y() { return y; }
    //distanza tra due punti tra QUESTO (this) punto e quello ricevuto come parametro
    public double distanza(Punto punto){ return Math.sqrt(Math.pow(this.x - punto.x(),2)+Math.pow(this.y - punto.y,2)); }
    //OVERRIDING del comportamento standard del metodo toString, comune a tutti gli Object
    @Override
    public String toString() {
        DecimalFormat f = new DecimalFormat("##.00");
        return f.format(this.x)+"  "+f.format(this.y);
    }
}