package geometria.geo3D;

import java.text.DecimalFormat;

public class Punto extends geometria.geo2D.Punto{
    //attributi
    double z;

    //costruttori
    public Punto(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }
    //costruttore per coprire il caso Punto sia 2D
    public Punto(double x, double y) {
        super(x, y);
        this.z = 0;
    }
    //metodi
    public double z(){ return this.z;}
    //
    //OVERRIDING della distanza ereditatà perchè devo trattare con 3 coordinate
    public double distanza(Punto p){ return Math.sqrt(Math.pow(this.x()-p.x(),2)+Math.pow(this.y()-p.y(),2)+Math.pow(this.z-p.z(),2)); }
    //OVERRIDING del comportamento standard del metodo toString, comune a tutti gli Object
    @Override
    public String toString() {
        DecimalFormat f = new DecimalFormat("##.00");
        return super.toString()+" "+f.format(this.z);
    }
}