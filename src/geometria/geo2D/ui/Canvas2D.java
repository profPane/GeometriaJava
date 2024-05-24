package geometria.geo2D.ui;

//librerie del progetto
import geometria.geo2D.Punto;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JPanel;



class Canvas2D extends JPanel {
    // todo: le proporzioni dinamiche
    int xMin, xMax, yMin, yMax; // valore degli estremi del piao cartesiano
    //int l = 500, h = 500;// salvo dimensioni della finestra in delle variabili per renderle più facili da
    double zoom;
    //static public float a = 30;
    ArrayList<Punto> punti;

    int max;

    public Canvas2D(ArrayList<Punto> punti, int max) {
        this.punti = punti;
        //imposto i limiti di visualizzazione del piano cartesian
        this.xMin=(int) -(max*1.05); //per stabilire i bordi del piano cartesiano
        this.xMax=(int) (max*1.05); //per stabilire i bordi del piano cartesiano
        this.yMin=xMin;
        this.yMax=xMax;
        this.max = max;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //chiamo per prima cosa il costruttore di SUPER

        //imposto le proporzioni, cioè di quanto devo ingrandire le distanze per usare tutto il foglio
        //uso il minimo tra altezza e larghezza del foglio meno il 5% per preseverare i bordi
        if (this.getHeight()<this.getWidth()) {
            zoom = ((this.getHeight()/2)/max)/1.05; //es: (500/2)pixels / 50 punti
        } else {
            zoom = ((this.getWidth()/2)/max)/1.05;
        } 

        setBackground(Color.WHITE);
        //intervallo contiene la proporzione tra dimensioni del Canvas e valori da visualizzare
        
        g.setColor(Color.black); //prendo il pennarello nero
        g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1); //disegno il bordo del foglio

        //disegno gli assi
        g.setColor(Color.red); //prendo il pennarello rosso
        //asse delle x
        g.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2); 
        //asse delle y
        g.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight()); 
        //e le etichette degli assi
        g.drawString("" + xMin, 5, (this.getHeight() / 2) - 5);
        g.drawString("" + xMax, (this.getWidth() - 20), (this.getHeight() / 2) - 5);
        g.drawString("" + yMax, (this.getWidth() / 2 + 5), 15);
        g.drawString("" + yMin, (this.getWidth() / 2 + 5), (this.getHeight() - 5));

        //disegno la spezzata, formata da linee tra i suoi punti
        //il canvas ha solo coordinate positive, devo compensare i valori dei punti con
        int cx=this.getWidth()/2; //quanto devo spostare X=0 per stare al centro del foglio
        int cy=this.getHeight()/2; //quanto devo spostare Y=0 per stare al centro del foglio
        
        //mi preparo l'oggetto che mi permette di creare una stringa a partire da un numero
        //scegliendo il formato (quantità di numeri decimali, cifre totali, etc etc)
        DecimalFormat df = new DecimalFormat("0.00");
        //visito l'array di Punto e traccio le linee, parto da 1 perche le linee sono 1 meno dei Punto
        for (int i = 1; i < punti.size(); i++) {
            //calcolo le coordinate dei punti sul Canvas rispettando le proporzioni
            int x1 = (int) ( punti.get(i-1).x()*zoom); //proporziono x1 alle dimensioni del foglio
            int y1 = (int) ( punti.get(i-1).y()*zoom); //proporziono y1 alle dimensioni del foglio
            int x2 = (int) ( punti.get(i).x()*zoom);   //proporziono x1 alle dimensioni del foglio
            int y2 = (int) ( punti.get(i).y()*zoom);   //proporziono y2 alle dimensioni del foglio
            //correggo le coordinate per spostare l'origine da in alto a destra al centro del mio piano
            //i valori negativi di X devono "scendere" oltre la meta del foglio
            x1=cx+x1; // l'origine delle X sta al punto 250 del mio "foglio"            
            y1=cy-y1; // l'origine delle Y sta al punto 250 del mio "foglio", i valori negativi (es)
            x2=cx+x2; // l'origine delle X sta al punto 250 del mio "foglio"
            y2=cy-y2; // l'origine delle Y sta al punto 250 del mio "foglio" 

            g.setColor(Color.LIGHT_GRAY);
            //disegno le coordinate del punto sotto forma di stringa
            String punto = "P" + (i-1) + " ("+df.format(punti.get(i-1).x())+" "+df.format(punti.get(i-1).y())+")";
            g.drawString(punto, x1-5,y1-5);   
            //disegno il segmento
            g.setColor(Color.BLUE);
            g.drawLine(x1, y1, x2, y2);
            //disegno il Punto nei vertici, è un Oval (Ellise), con i diametri uguali (5pixel)
            g.setColor(Color.DARK_GRAY);
            g.fillOval(x1-2, y1-2, 5, 5);
        }
    }
}