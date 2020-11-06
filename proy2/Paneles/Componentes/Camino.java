package proy2.Paneles.Componentes;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import proy2.Control;

@SuppressWarnings(value = "serial")
public class Camino extends JPanel{
    
    private Ciudad CiudadOr;
    private Ciudad CiudadFin;
    private int Distancia = 0;
    private JLabel LbDist;
    
    public Camino(Ciudad Or, Ciudad Fin, int D){
        setLayout(null);

        CiudadOr = Or;
        CiudadFin = Fin;
        Distancia = D;

        //POSICION DEL PANEL
        int IntX = (CiudadOr.getLocation().x + CiudadFin.getLocation().x + Control.CiudadTam)/2;
        int IntY = (CiudadOr.getLocation().y + CiudadFin.getLocation().y + Control.CiudadTam)/2;
        setBounds(IntX, IntY, 30, 30);

        //INICIAR LABEL
        LbDist = new JLabel(""+Distancia);
        LbDist.setBounds(12,5,30,30);
        LbDist.setForeground(Color.BLACK);
        add(LbDist);
    }

    @Override
    protected void paintComponent(Graphics g) {

        int IntX = (CiudadOr.getLocation().x + CiudadFin.getLocation().x + Control.CiudadTam)/2;
        int IntY = (CiudadOr.getLocation().y + CiudadFin.getLocation().y + Control.CiudadTam)/2;
        setBounds(IntX -17, IntY - 17, 35, 35);
        g.setColor(Color.white);
        g.fillOval(0, 0, 35, 35);

    }
}
