package proy2.Paneles.Componentes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import proy2.Ciudad;

public class Camino extends JPanel{
    
    Ciudad Origen, Final;
    int Distancia = 0;
    
    public Camino(Ciudad Or, Ciudad Fin, int D){
        setLayout(null);
        int a = 0;
        Origen = Or;
        Final = Fin;
        Distancia = D;
        int IntX = (Or.getLocation().x + Fin.getLocation().x + Ciudad.Tam.x)/2;
        int IntY = (Or.getLocation().y + Fin.getLocation().y + Ciudad.Tam.x)/2;
        setBounds(IntX, IntY, 30, 30);

        JLabel LbDist = new JLabel(""+Distancia);
        LbDist.setBounds(0,0,30,30);
        LbDist.setForeground(Color.WHITE);
        add(LbDist);
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        int IntX = (Origen.getLocation().x + Final.getLocation().x + Ciudad.Tam.x)/2;
        int IntY = (Origen.getLocation().y + Final.getLocation().y + Ciudad.Tam.x)/2;
        setBounds(IntX, IntY, 30, 30);

    }
}
