package codigo.Paneles.Areas;

import java.awt.GridLayout;

import codigo.Control;

import javax.swing.JLabel;
import codigo.MatrizDin;

@SuppressWarnings(value = "serial")
public class PanelMatrizRec extends PanelMatriz {

    public PanelMatrizRec(MatrizDin M) {
        super(M);
    }

    @Override
    public void actualizar() {

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
                PanelInicial TxtCelda = new PanelInicial("" + ((Matriz.celda(w, h) == Control.INF) ? "∞" : ""+Control.AreaTrabajo.Ciudades.get(Matriz.celda(w, h)).Nombre.charAt(0)), false);
                add(TxtCelda);
            }
        }

        setBounds(0, 0, TamCelda * Matriz.Ancho, TamCelda * Matriz.Alto);

        validate();
    }
}
