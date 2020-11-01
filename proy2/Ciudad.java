package proy2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import proy2.Paneles.Componentes.Camino;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class Ciudad extends JPanel implements MouseInputListener {
    public String Nombre;
    public ArrayList<Ciudad> Relaciones = new ArrayList<Ciudad>();
    public HashMap<Ciudad, Camino>Caminos = new HashMap<Ciudad, Camino>();
    public static final Point Tam = new Point(100, 100);
    private Point PDif = new Point();


    public Ciudad(int Px, int Py, String Nom) {
        setLayout(null);
        Nombre = Nom;
        setBackground(Color.lightGray);
        setBounds(Px, Py, Tam.x, Tam.y);
        addMouseMotionListener(this);
        addMouseListener(this);

        JLabel NombreC = new JLabel(Nombre);
        NombreC.setBounds(0, Tam.x/2, Tam.x, 50);
        add(NombreC);
    }

    @Override 
    public void mouseDragged(MouseEvent e) {
        Point P = Control.Ventana.getLocation();
        setBounds(e.getXOnScreen() - P.x - PDif.x, e.getYOnScreen() - P.y - PDif.y, Tam.x, Tam.y);
        Control.Ventana.repaint();
    }

    @Override 
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            PDif.x = e.getXOnScreen() - getLocation().x -  Control.Ventana.getLocation().x;
            PDif.y = e.getYOnScreen() - getLocation().y -  Control.Ventana.getLocation().y;
            System.out.println(getLocation().x);
            System.out.println(PDif.x);
        }else if(e.getButton() == MouseEvent.BUTTON3){
            Control.AreaTrabajo.eliminarCiudad(this);
            /*
            if(Control.ESTADO == Control.ESTCONECT){
                Control.AreaTrabajo.conectarCiudades(Control.CiudadIni, this, 5);
                Control.CiudadIni = null;
                Control.ESTADO = Control.ESTNORMAL;
                Control.Ventana.repaint();
            }else{
                Control.CiudadIni = this;
                Control.ESTADO = Control.ESTCONECT;
            }*/
        }
    }

    @Override public void mouseClicked(MouseEvent e) {}

    @Override public void mouseReleased(MouseEvent e) {}

    @Override public void mouseEntered(MouseEvent e) {}

    @Override public void mouseExited(MouseEvent e) {}

    @Override public void mouseMoved(MouseEvent e) {}
}
