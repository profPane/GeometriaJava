package geometria.geo2D;

public class Retta {

    private double x1, y1; // Coordinate del primo punto
    private double x2, y2; // Coordinate del secondo punto

    // Costruttore per inizializzare la retta con due punti
    public Retta(Punto p1, Punto p2){
        this.x1 = p1.x();
        this.y1 = p1.y();
        this.x2 = p2.x();
        this.y1 = p2.y();
    }
    
    public Retta(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    // Metodo per calcolare la pendenza della retta
    public double getM() {
        if (x1 == x2) { // Caso retta verticale
            return Double.POSITIVE_INFINITY;
        } else {
            return (y2 - y1) / (x2 - x1);
        }
    }

    // Metodo per calcolare l'intercetta con l'asse y
    public double getQ() {
        return y1 - getM() * x1;
    }

    public Punto intersezione(Retta r2) {
        double m1 = this.getM();
        double b1 = this.getQ();
        double m2 = r2.getM();
        double b2 = r2.getQ();

        // Casi speciali
        if (Double.isFinite(m1) && Double.isFinite(m2) && m1 == m2 && b1 != b2) {
            return null; // Rette parallele
        } else if (Double.isFinite(m1) && Double.isInfinite(m2)) {
            // Calcolare x sull'intersezione con la retta verticale
            double x = (b2 - b1) / (m1);
            return new Punto(x, m1 * x + b1);
        } else if (Double.isInfinite(m1) && Double.isFinite(m2)) {
            // Calcolare x sull'intersezione con la retta verticale
            double x = (b1 - b2) / (m2);
            return new Punto(x, m2 * x + b2);
        } else {
            // Calcolare il punto di intersezione generico
            double x = (b2 - b1) / (m1 - m2);
            double y = m1 * x + b1;
            return new Punto(x, y);
        }
    }

    // Restituisce la Retta sotto forma di equazione y = mx + b
    public String toString() {
        double pendenza = getM();
        double intercettaY = getQ();
        if (pendenza==0) return ("y = " + intercettaY);                 // Caso retta orizzontale   
        if (pendenza==Double.POSITIVE_INFINITY) return ("x = " + x1);   // Caso retta verticale
        return ("y = " + pendenza + "x + " + intercettaY);              //tutti gli altri casi
    }
}