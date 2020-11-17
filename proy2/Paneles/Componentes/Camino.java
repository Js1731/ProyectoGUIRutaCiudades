package proy2.Paneles.Componentes;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

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

        //setBackground(Color.cyan);
        setOpaque(false);

        //POSICION DEL PANEL
        int IntX = (CiudadOr.getLocation().x + CiudadFin.getLocation().x + Control.CiudadTam)/2;
        int IntY = (CiudadOr.getLocation().y + CiudadFin.getLocation().y + Control.CiudadTam)/2;
        setBounds(IntX, IntY, 100, 30);

        //INICIAR LABEL
        LbDist = new JLabel(""+Distancia + " km");
        LbDist.setBounds(0,5,100,30);
        LbDist.setHorizontalAlignment(JLabel.CENTER);
        LbDist.setForeground(Color.BLACK);
        add(LbDist);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // MEJORAR RESOLUCION DE FORMAS
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHints(qualityHints);

        int IntX = (CiudadOr.getLocation().x + CiudadFin.getLocation().x + Control.CiudadTam)/2;
        int IntY = (CiudadOr.getLocation().y + CiudadFin.getLocation().y + Control.CiudadTam)/2;
        setBounds(IntX - 50, IntY - 10, 100, 35);
        g2.setColor(Color.white);
        g2.fillOval(30, 0, 40, 35);

    }
}
