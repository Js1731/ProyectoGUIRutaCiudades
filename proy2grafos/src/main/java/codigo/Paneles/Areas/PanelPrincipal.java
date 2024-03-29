package codigo.Paneles.Areas;

import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.Graphics;

import codigo.Control;
import codigo.Paneles.Componentes.BtnCintaOp;
import codigo.Paneles.Componentes.BtnTab;

@SuppressWarnings(value = "serial")
public class PanelPrincipal extends JPanel{
    
    private BtnTab TabAdy, TabDis, TabCam, TabRec;
    private BtnCintaOp Agregar, Conectar, Borrar, Normal, Buscar;
    public PanelNomCiudad PnNomCiu = new PanelNomCiudad(0, 0);
    public PanelDistCiudad PnDisCiu = new PanelDistCiudad(0, 0);

    public PanelPrincipal() {
        setLayout(null);
        setBounds(0, 0, Control.VentTam.x, Control.VentTam.y);

        Control.PanPrinc = this;

        
        add(PnNomCiu);
        add(PnDisCiu);

        add(new PnIntro());
        

        // BOTONES DE LA CINTA DE OPCIONES
        Agregar = new BtnCintaOp(700, 620, Control.BtnAgregar0, Control.BtnAgregar1, Control.BtnAgregar2) {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                cambiar_Op(this);
                Control.ESTADO = Control.ESTAGREGAR;
                Control.CiudadAux = null;
                System.out.println("Estado actual : " + Control.ESTADO);
            }
        };

        add(Agregar);

        Conectar = new BtnCintaOp(750, 620, Control.BtnConect0, Control.BtnConect1, Control.BtnConect2) {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                cambiar_Op(this);
                Control.CiudadAux = null;
                Control.ESTADO = Control.ESTCONECT;
                System.out.println("Estado actual : " + Control.ESTADO);
            }
        };

        add(Conectar);

        Borrar = new BtnCintaOp(800, 620, Control.BtnBorrar0, Control.BtnBorrar1, Control.BtnBorrar2) {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                cambiar_Op(this);

                Control.CiudadAux = null;
                Control.ESTADO = Control.ESTBORRAR;
                System.out.println("Estado actual : " + Control.ESTADO);
            }
        };

        add(Borrar);

        Buscar = new BtnCintaOp(610, 620, Control.BtnBuscar0, Control.BtnBuscar1, Control.BtnBuscar2) {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                cambiar_Op(this);

                Control.CiudadAux = null;
                Control.ESTADO = Control.ESTBUSCAR;
                System.out.println("Estado actual : " + Control.ESTADO);
            }
        };

        add(Buscar);

        // BOTONES DE LA CINTA DE OPCIONES

        Normal = new BtnCintaOp(880, 620, Control.BtnAjustar0, Control.BtnAjustar1 , Control.BtnAjustar2) {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                cambiar_Op(this);
                Control.ESTADO = Control.ESTNORMAL;
                Control.CiudadAux = null;
                System.out.println("Estado actual : " + Control.ESTADO);
            }
        };

        add(Normal);

        // PANELES SECUNDARIOS
        add(Control.AreaTrabajo = new PanelAreaTrabajo());
        add(Control.Expositor = new PanelExpositor());

        /// PESTANAS PARA SELECCIONAR TABLAS
        TabAdy = new BtnTab("Adyacencia", 37, 250, 130, 30) {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                cambiar_tab("MATADY", this);
            }
        };
        add(TabAdy);
        TabAdy.Estado = TabAdy.EstSel;

        TabDis = new BtnTab("Distancia", 172, 250, 100, 30) {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                cambiar_tab("MATDIS", this);
            }
        };
        add(TabDis);

        TabCam = new BtnTab("Camino Minimo", 277, 250, 160, 30) {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                cambiar_tab("MATCAM", this);
            }
        };
        add(TabCam);

        TabRec = new BtnTab("Recorrido", 150, 640, 160, 30) {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                cambiar_tab("MATREC", this);
            }
        };
        add(TabRec);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Control.ImFondo, 0, 0, this);
    }

    public void cambiar_tab(String TabIndex, BtnTab Tab){
        Control.Expositor._cambiar_matriz(TabIndex);
        
        TabAdy.Estado = TabAdy.EsNorm;
        TabDis.Estado = Tab.EsNorm;
        TabCam.Estado = Tab.EsNorm;
        TabRec.Estado = Tab.EsNorm;

        Tab.Estado = Tab.EstSel;

        Control.Ventana.repaint();
    }

    public void cambiar_Op(BtnCintaOp Tab){

        Control.AreaTrabajo.Mensaje = "";

        Agregar.Select = false;
        Conectar.Select = false;
        Borrar.Select = false;
        Buscar.Select = false;
        Normal.Select = false;


        Tab.Select = true;
        Tab.repaint();

        Control.Ventana.repaint();
    }

}