package proy2.Paneles.Componentes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import proy2.Control;

import java.awt.Point;
import java.awt.event.MouseEvent;

@SuppressWarnings(value = "serial")
public class Ciudad extends JPanel implements MouseInputListener {

    public String Nombre;
    public HashMap<Ciudad, Camino> Caminos = new HashMap<Ciudad, Camino>();
    private Point PDif = new Point();

    public Ciudad(int Px, int Py, String Nom) {
        setLayout(null);

        Nombre = Nom;
        
        setBounds(Px, Py, Control.CiudadTam, Control.CiudadTam + 30);
        setOpaque(false);
        
        addMouseMotionListener(this);
        addMouseListener(this);

    }

    @Override 
    public void mouseDragged(MouseEvent e) {
        if(Control.ESTADO == Control.ESTNORMAL){
            Point P = Control.Ventana.getLocation();
            setBounds(e.getXOnScreen() - P.x - PDif.x, e.getYOnScreen() - P.y - PDif.y, Control.CiudadTam, Control.CiudadTam + 30);
            Control.Ventana.repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(Control.ImCiudad, 0, 0, this);
        g.drawString(Nombre, 0, Control.CiudadTam + 25);
    }

    @Override 
    public void mousePressed(MouseEvent e) {

        if(Control.ESTADO == Control.ESTBORRAR)
            Control.AreaTrabajo.eliminarCiudad(this);
        else if(Control.ESTADO == Control.ESTCONECT){
            if(Control.CiudadAux != null && Control.CiudadAux != this){
                Control.PanPrinc.PnDisCiu._mover(new Point(650, 300));
                Control.PanPrinc.PnDisCiu.JTNombre.setText("");
                Control.PanPrinc.PnDisCiu.Activo = true;
                Control.PanPrinc.PnDisCiu.CiudadFin = this;
                Control.Ventana.repaint();
            }else{
                Control.CiudadAux = this;
            }
        }else if(Control.ESTADO == Control.ESTBUSCAR){
            if(Control.CiudadAux != null && Control.CiudadAux != this){
                Control.AreaTrabajo.Caminos =  Control.AreaTrabajo.buscarCaminoDijsktra(Control.CiudadAux, this);
                Control.CiudadAux = null;
                Control.Ventana.repaint();
            }else{
                Control.CiudadAux = this;
            }
        }else if(Control.ESTADO == Control.ESTAGREGAR){
            
        }else{
            PDif.x = e.getXOnScreen() - getLocation().x -  Control.Ventana.getLocation().x;
            PDif.y = e.getYOnScreen() - getLocation().y -  Control.Ventana.getLocation().y;
        }
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
}
