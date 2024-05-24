package geometria.geo2D.ui;

//librerie del progetto
import geometria.geo2D.Punto;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

//Cornice (di tipo JFrame) contiene la "tela" (Canvas o JPanel) su cui disegnerò
public class Cornice extends JFrame {
	public Cornice(ArrayList<Punto> punti, double max) {
		super();
		//cosa fare se premo la X, in questo caso chiudo l'applocazione EXIT_ON_CLOSE
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setto la modalità di disposizione degli oggi nel "pannello" dei contenuti
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//come JPanel metto un Canvas ("tela") che visualizza punti compresi tra -max e max
		JPanel postIt = new Canvas2D(punti, (int) max);
		//scelgo una dimensione di partenza della mia tela
		postIt.setPreferredSize(new Dimension(510, 510));
		//aggiungo la "tela" al pannello dei contenuti della "cornice"
		getContentPane().add(postIt);
		//compatto il frame per occupare giusto lo spazio necessario ai contenuti
		this.pack();
	}
}