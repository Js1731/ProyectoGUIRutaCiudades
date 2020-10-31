package proy2;

import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JFrame;

public class App {
    public static void main( String[] args ){

        Dimension Pantalla = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame Ventana = new JFrame();
        Control.Ventana = Ventana;
        
        Ventana.setSize(1000,700);
        Ventana.setUndecorated(true);
        Ventana.setLocation(Pantalla.width/2 - 1000/2, Pantalla.height/2 - 350);
        Ventana.setVisible(true);
        Ventana.add(Control.PanelP = new PanelPrinc());
        Ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
