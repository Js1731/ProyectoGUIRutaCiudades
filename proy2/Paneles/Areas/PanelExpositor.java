package proy2.Paneles.Areas;

import javax.swing.JPanel;

import proy2.Control;

import java.awt.CardLayout;

@SuppressWarnings(value = "serial")
public class PanelExpositor extends JPanel{
    
    private CardLayout ExpoCont = new CardLayout();
    private PanelMatriz MatAdyacencia = new PanelMatriz(Control.AreaTrabajo.MatrizAdj);
    private PanelMatriz MatDistancia  = new PanelMatriz(Control.AreaTrabajo.MatrizDist);
    private PanelMatriz MatCamMin     = new PanelMatriz(Control.AreaTrabajo.MatrizCamMin);
    private PanelMatrizRec MatRecorrido     = new PanelMatrizRec(Control.AreaTrabajo.MatrizRecorrido);

    public PanelExpositor(){
        setLayout(ExpoCont);
        
        setOpaque(false);
        add(MatAdyacencia, "MATADY");
        add(MatDistancia , "MATDIS");
        add(MatCamMin    , "MATCAM");
        add(MatRecorrido , "MATREC");

        ajustarTabla();

        ExpoCont.show(this, "MATADY");
    }

    private void ajustarTabla(){
        int Ancho = Control.AreaTrabajo.MatrizAdj.Ancho * PanelMatriz.TamCelda;
        int Alto = Control.AreaTrabajo.MatrizAdj.Alto * PanelMatriz.TamCelda;
        int PosX = 35  + 400/2 - Ancho/2;
        int PosY = 287 + 350/2 - Alto/2;

        setBounds(PosX, PosY, Ancho, Alto);
    }

    public void _cambiar_matriz(String Panel){
        ExpoCont.show(this, Panel);
        ajustarTabla();
    }

    public void _actualizar_matrices(){
        MatAdyacencia.actualizar();
        MatDistancia.actualizar();
        MatCamMin.actualizar();
        (MatRecorrido).actualizar();
        ajustarTabla();
    }
}
