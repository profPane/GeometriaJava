import geometria.geo3D.Punto;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        // istanzio il generatore di numeri pseudocasuali
        Random miaRandom = new Random();
        // istanzio la spezzata di punti nello spazio
        Punto mioPunto = 
            new Punto(miaRandom.nextDouble()*100-50,miaRandom.nextDouble()*100-50);
        geometria.geo2D.Spezzata miaSpezzata = new geometria.geo2D.Spezzata(mioPunto);
        for (int i = 0; i < miaRandom.nextInt(10)+3; i++) { //aggiungo fino a 10 vertici
            miaSpezzata.aggiungiPunto(new geometria.geo2D.Punto(miaRandom.nextDouble()*100-50,miaRandom.nextDouble()*100-50));
        }
        //chiudo la spezzata
        miaSpezzata.chiudi();
        miaSpezzata.visualizza2D();
    }
}