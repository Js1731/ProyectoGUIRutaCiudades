package proy2.Paneles.Areas;

import javax.swing.JPanel;

import java.awt.CardLayout;
import proy2.Control;

public class PanelPrincipal extends JPanel{
    public PanelPrincipal(){
        setLayout(null);
        setBounds(0, 0, Control.VentTam.x, Control.VentTam.y);
        add(Control.AreaTrabajo = new PanelAreaTrabajo());
        
        CardLayout ContExpo = new CardLayout();
        JPanel Expositor = new JPanel();
        Expositor.setBounds(37, 287, 397, 347);
        Expositor.setLayout(ContExpo);
        Expositor.add(new PanelMatriz("Matriz de Adyacencia", Control.AreaTrabajo.MatrizAdj, Control.AreaTrabajo.Ciudades), "Matriz 1");
        ContExpo.show(Expositor, "Matriz 1");
        add(Expositor);
    }
}