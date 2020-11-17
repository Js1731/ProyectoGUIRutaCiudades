package proy2;

import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import java.awt.event.MouseEvent;

import proy2.Paneles.Areas.PanelPrincipal;

///CLASE PARA MOVER LA VENTANA
class Listener implements MouseInputListener {

    int DifX;
    int DifY;

    public void mousePressed(MouseEvent e) {
        DifX = e.getXOnScreen() - Control.Ventana.getLocation().x;
        DifY = e.getYOnScreen() - Control.Ventana.getLocation().y;
    }

    public void mouseDragged(MouseEvent e) {
        JFrame Vent = (JFrame) e.getSource();
        Vent.setLocation(e.getXOnScreen() - DifX, e.getYOnScreen() - DifY);

    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
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
        
        Listener Adaptador = new Listener();
        Ventana.addMouseMotionListener(Adaptador);
        Ventana.addMouseListener(Adaptador);
    }

}
