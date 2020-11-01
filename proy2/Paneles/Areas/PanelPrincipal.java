package proy2.Paneles.Areas;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;

import proy2.Control;
import proy2.Paneles.Componentes.BtnCintaOp;
import proy2.Paneles.Componentes.BtnTab;

public class PanelPrincipal extends JPanel{

    public PanelMatriz MatrizAdy;
    public PanelMatriz MatrizDist;
    public PanelMatriz MatrizCamMin;
    CardLayout ContExpo = new CardLayout();
    JPanel Expositor = new JPanel();

    public PanelPrincipal(){
        setLayout(null);
        setBounds(0, 0, Control.VentTam.x, Control.VentTam.y);
        Control.PanPrinc = this;

        
        BtnCintaOp Agregar = new BtnCintaOp(500, 100){
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                System.out.println("Opcion 1");
            }
        };

        add(Agregar);


        add(Control.AreaTrabajo = new PanelAreaTrabajo());


        
        
        MatrizAdy = new PanelMatriz(Control.AreaTrabajo.MatrizAdj, Control.AreaTrabajo.Ciudades);
        MatrizDist = new PanelMatriz(Control.AreaTrabajo.MatrizDist, Control.AreaTrabajo.Ciudades);
        MatrizCamMin = new PanelMatriz(Control.AreaTrabajo.MatrizCamMin, Control.AreaTrabajo.Ciudades);
        
        
        Expositor.setBounds(37, 287, Control.AreaTrabajo.MatrizAdj.Ancho * 50, Control.AreaTrabajo.MatrizAdj.Alto * 50);
        Expositor.setLayout(ContExpo);
        Expositor.add( MatrizAdy, "Matriz 1");
        Expositor.add( MatrizDist, "Matriz 2");
        Expositor.add( MatrizCamMin, "Matriz 3");
        ContExpo.show(Expositor, "Matriz 1");
        add(Expositor);

        BtnTab TabAdy = new BtnTab("Adyacencia", 50, 200, 100, 50){
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Control.PanPrinc.ContExpo.show(Expositor, "Matriz 1");
                Control.PanPrinc.Expositor.setBounds(37, 287, Control.AreaTrabajo.MatrizAdj.Ancho * 50, Control.AreaTrabajo.MatrizAdj.Alto * 50);
            }
        };
        add(TabAdy);

        BtnTab TabDis = new BtnTab("Distancia", 210, 200, 100, 50){
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Control.PanPrinc.ContExpo.show(Expositor, "Matriz 2");
                Control.PanPrinc.Expositor.setBounds(37, 287, Control.AreaTrabajo.MatrizAdj.Ancho * 50, Control.AreaTrabajo.MatrizAdj.Alto * 50);
            }
        };
        add(TabDis);

        BtnTab TabCamMin = new BtnTab("Camino Minimo", 370, 200, 100, 50){
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Control.PanPrinc.ContExpo.show(Expositor, "Matriz 3");
                Control.PanPrinc.Expositor.setBounds(37, 287, Control.AreaTrabajo.MatrizAdj.Ancho * 50, Control.AreaTrabajo.MatrizAdj.Alto * 50);
            }
        };
        add(TabCamMin);
        

    
    }
}