package proy2.Paneles.Areas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import proy2.Ciudad;
import proy2.Control;
import proy2.MatrizDin;

public class PanelMatriz extends JPanel{

    //private MatrizDin Matriz;
    private ArrayList<Ciudad> Ciudades;

    int CeldaTam = 50;

    MatrizDin Matriz;
    ArrayList<Ciudad> ciudades;

    public PanelMatriz(MatrizDin M, ArrayList<Ciudad> C){

        setBackground(Color.gray);

        Matriz = M;
        Ciudades = C;
        actualizar();
    }

    public void actualizar(){

        removeAll();
        
        setLayout(new GridLayout(Matriz.Ancho + 1, Matriz.Alto + 1));
        
        add(new JLabel(""));

        for (int i = 0; i < Matriz.Ancho; i++) {
            JLabel Texto = new JLabel(""+Ciudades.get(i).Nombre.charAt(0));
            Texto.setHorizontalAlignment(SwingConstants.CENTER);
            add(Texto);
        }

        for (int h = 0; h < Matriz.Alto; h++) {
            JLabel TxtNom = new JLabel(""+Ciudades.get(h).Nombre.charAt(0));
            TxtNom.setHorizontalAlignment(SwingConstants.CENTER);
            add(TxtNom);
            
            for (int w = 0; w < Matriz.Ancho; w++) {
                JLabel TxtCelda = new JLabel("" + ((Matriz.celda(w, h) == Control.AreaTrabajo.INF) ? "INF" : Matriz.celda(w, h)));
                TxtCelda.setHorizontalAlignment(SwingConstants.CENTER);
                add(TxtCelda);
            }
        }

        setBounds(0, 0, CeldaTam * Matriz.Ancho, CeldaTam * Matriz.Alto);

        validate();
    }
}

