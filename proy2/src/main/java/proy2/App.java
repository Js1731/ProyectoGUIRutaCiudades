package proy2;

import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JFrame;

import proy2.Paneles.Areas.PanelPrincipal;

public class App {
    public static void main( String[] args ){

        Dimension Pantalla = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame Ventana = new JFrame();
        Control.Ventana = Ventana;
        
        Ventana.setSize(Control.VentTam.x,Control.VentTam.y);
        Ventana.setUndecorated(true);
        Ventana.setLocation(Pantalla.width/2 - Control.VentTam.x/2, Pantalla.height/2 - Control.VentTam.y/2);
        Ventana.setVisible(true);
        Ventana.add(new PanelPrincipal());
        Ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
