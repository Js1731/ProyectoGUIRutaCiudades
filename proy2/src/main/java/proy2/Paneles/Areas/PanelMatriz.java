package proy2.Paneles.Areas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import proy2.Ciudad;
import proy2.MatrizDin;

public class PanelMatriz extends JPanel{

    //private MatrizDin Matriz;
    private ArrayList<Ciudad> Ciudades;

    public PanelMatriz(String Nom, MatrizDin Matriz, ArrayList<Ciudad> Ciudades){
        setLayout(new GridLayout(Matriz.Ancho + 1, Matriz.Alto + 1));
        setBounds(0, 0, 397, 347);
        setBackground(Color.gray);
        JLabel JL = new JLabel(Nom);
        JL.setBounds(10,10,100,50);

        add(new JPanel());

        for (int i = 0; i < Matriz.Ancho; i++) {
            add(new JLabel(""+Ciudades.get(i).Nombre.charAt(0)));
        }

        for (int h = 0; h < Matriz.Alto; h++) {
            add(new JLabel(""+Ciudades.get(h).Nombre.charAt(0)));
            for (int w = 0; w < Matriz.Ancho; w++) {
                add(new JLabel(""+Matriz.celda(w, h)));
            }
        }
    }
}
