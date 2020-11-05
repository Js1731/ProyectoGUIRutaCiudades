package proy2;

import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import proy2.Paneles.Areas.PanelPrincipal;

class Listener implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        Control.ESTADO = Control.ESTNORMAL;
        System.out.println("NORMAL");
    }
}

public class App{
    public static void main(String[] args) {

        new Control();

        Dimension Pantalla = Toolkit.getDefaultToolkit().getScreenSize();


        JFrame Ventana = new JFrame();
        Control.Ventana = Ventana;

        Ventana.add(new PanelPrincipal());

        Ventana.setSize(Control.VentTam.x, Control.VentTam.y);
        Ventana.setUndecorated(true);
        Ventana.setLocation(Pantalla.width / 2 - Control.VentTam.x / 2, Pantalla.height / 2 - Control.VentTam.y / 2);
        Ventana.setVisible(true);

        Ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Ventana.addKeyListener(new Listener());

    }

}
