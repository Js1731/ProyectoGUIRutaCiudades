package proy2.Paneles.Areas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import proy2.Paneles.Componentes.Ciudad;
import proy2.Control;
import proy2.MatrizDin;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

@SuppressWarnings(value = "serial")
public class PanelMatriz extends JPanel{

    public static final int TamCelda = 50;
    MatrizDin Matriz;
    ArrayList<Ciudad> ciudades;

    public PanelMatriz(MatrizDin M){
        setOpaque(false);
        Matriz = M;
        actualizar();
    }

    public void actualizar(){

        removeAll();
        
        setLayout(new GridLayout(Matriz.Ancho + 1, Matriz.Alto + 1));
        
        add(new JLabel(""));

        for (int i = 0; i < Matriz.Ancho; i++) {
            PanelInicial Texto = new PanelInicial(""+Control.AreaTrabajo.Ciudades.get(i).Nombre.charAt(0), true);
            add(Texto);
        }

        for (int h = 0; h < Matriz.Alto; h++) {
            PanelInicial TxtNom = new PanelInicial(""+Control.AreaTrabajo.Ciudades.get(h).Nombre.charAt(0), true);
            add(TxtNom);
            
            for (int w = 0; w < Matriz.Ancho; w++) {
                PanelInicial TxtCelda = new PanelInicial("" + ((Matriz.celda(w, h) == Control.INF) ? "∞" : Matriz.celda(w, h)), false);
                add(TxtCelda);
            }
        }

        setBounds(0, 0, TamCelda * Matriz.Ancho, TamCelda * Matriz.Alto);

        validate();
    }

    class PanelInicial extends JPanel{

        private JLabel TxtCelda;
        private boolean EsInicial = false;
        public PanelInicial(String Inicial, boolean Ini){
            setLayout(null);
            setBounds(0, 0, TamCelda, TamCelda);
            setOpaque(false);
            
            EsInicial = Ini; 

            TxtCelda = new JLabel(Inicial);
            TxtCelda.setBounds(-5, -5, TamCelda, TamCelda);
            TxtCelda.setHorizontalAlignment(SwingConstants.CENTER);
            
            if(Ini)
                TxtCelda.setForeground(Color.WHITE);

            add(TxtCelda);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if(EsInicial){            
                RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                Graphics2D g2 = (Graphics2D)g.create();
        
                g2.setRenderingHints(qualityHints);
                
                g2.setFont(Control.TextoTabla);
                g2.setColor(Control.ColNaranja);
                g2.fillOval(0,0, getWidth(),getHeight());
            }

        }
    }
}

