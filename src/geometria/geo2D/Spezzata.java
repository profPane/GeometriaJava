package geometria.geo2D;

import geometria.geo2D.ui.Cornice;
import java.awt.EventQueue;
import java.util.ArrayList;

public class Spezzata {

    protected ArrayList<Punto> punti;

    // costruttori
    public Spezzata() {
    }

    // come da richiesta
    public Spezzata(Punto origine) {
        this.punti = new ArrayList<>();
    }

    // metodi
    public void aggiungiPunto(geometria.geo2D.Punto p) {
        punti.add(p);
    }

    public void chiudi() {
        this.punti.add(punti.get(0));
    }

    public int quanti() { //quanti segmenti
        if (punti.size()<3) return -1;
        return punti.size() - 1;
    }

    public Segmento segmento(int numero){
        return new Segmento(punti.get(numero), punti.get(numero+1));
    }

    public int size() { //quanti punti
        return punti.size();
    }

    public double lunghezza() {
        double lunghezza = 0;
        for (int i = 0; i < punti.size() - 1; i++) {
            lunghezza += punti.get(i).distanza(punti.get(i+1));
        }
        return lunghezza;
    }

    public Punto eliminaP(Punto P) {
        if (punti.size() < 3)
            return null; // meno di 3 segmenti, rimarebbe un solo segmento
        // per iniziare uso la distanza tra P e il primo punto della spezzata
        int quale = 0;
        double distanza = (new Segmento(punti.get(0), P)).lunghezza();
        for (int i = 1; i < punti.size(); i++) {
            double d = punti.get(i).distanza(P);
            if (d < distanza) {
                quale = i;
                distanza = d;
            }
        }
        Punto eliminato = punti.remove(quale);
        return eliminato;
    }

    //restituisce il punto più lontano dal punto specificato
    public double max_dist(Punto p){
        double max=0;
        for (Punto punto : punti) if (punto.distanza(p)>max) max=punto.distanza(p);
        return max;
    }

    //restituisce la coordinata piu grande in valore assoluto
    public double max_coord(){
        double max=0;
        for (Punto punto : punti) {
            if (Math.abs(punto.x())>max) max=Math.abs(punto.x());
            if (Math.abs(punto.y())>max) max=Math.abs(punto.y());
        }
        return max;
    }

    public double getx(int punto) {
        return punti.get(punto).x();
    }

    public double gety(int punto) {
        return punti.get(punto).y();
    }

    public void visualizza2D() {
        EventQueue.invokeLater(() -> {
            try {
                Cornice frame =new Cornice(punti, 50);
                frame.setVisible(true);
            } catch (Exception e) {
            }
        });
    }

    @Override
    public String toString() {
        StringBuilder elementi = new StringBuilder();
        for (int i = 0; i < punti.size() - 1; i++) {
            elementi.append("Segmento: ")
                    .append(i + 1).append(" da p1:")
                    .append(punti.get(i).toString())
                    .append(" |---| p2:")
                    .append(punti.get(i + 1).toString())
                    .append("\n");
        }
        return elementi.toString();
    }
}