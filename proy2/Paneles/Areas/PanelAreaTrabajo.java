package proy2.Paneles.Areas;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import proy2.Paneles.Componentes.*;
import proy2.Control;
import proy2.MatrizDin;

@SuppressWarnings(value = "serial")
public class PanelAreaTrabajo extends JPanel implements MouseInputListener {

    public ArrayList<Ciudad> Caminos = new ArrayList<Ciudad>();
    public ArrayList<Ciudad> Ciudades = new ArrayList<Ciudad>();
    public MatrizDin MatrizAdj = new MatrizDin();
    public MatrizDin MatrizDist = new MatrizDin();
    public MatrizDin MatrizCamMin = new MatrizDin();
    public MatrizDin MatrizCamDijkstra = new MatrizDin();
    public ArrayList<Ciudad> NodosVer = new ArrayList<Ciudad>();
    public Point PosMouse = new Point(-200,0);

    public PanelAreaTrabajo() {

        setLayout(null);
        setBounds(485, 0, 635, 700);
        setOpaque(false);

        addMouseListener(this);
        addMouseMotionListener(this);

        MatrizCamDijkstra.editarMatriz(1, 4, 0);
        MatrizCamDijkstra.rellenarColumna(0, Control.INF);

        buscarCaminosMinimos();
    }

    public void eliminarCiudad(Ciudad Ci) {
        int ind = Ciudades.indexOf(Ci);

        // ELIMINAR CIUDAD DE LAS MATRICES
        MatrizAdj.eliminarColumna(ind);
        MatrizAdj.eliminarFila(ind);

        MatrizCamMin.eliminarColumna(ind);
        MatrizCamMin.eliminarFila(ind);

        MatrizDist.eliminarColumna(ind);
        MatrizDist.eliminarFila(ind);

        // ELIMINAR CAMINOS CON OTRAS CIUDADES
        for (Ciudad Vec : Ci.Caminos.keySet()) {
            remove(Ci.Caminos.get(Vec));
            Vec.Caminos.remove(Ci);
        }

        Caminos = null;

        Ciudades.remove(Ci);
        remove(Ci);

        buscarCaminosMinimos();

        // ACTUALIZAR MATRICES
        Control.Expositor._actualizar_matrices();
        Control.Ventana.repaint();
    }

    public Ciudad agregarCiudad(String Nombre, int Px, int Py) {
        Ciudad Ci = new Ciudad(Px, Py, Nombre);

        // AGREGAR CIUDAD A MATRICES
        MatrizAdj.agregarColumna();
        MatrizAdj.agregarFila();

        MatrizDist.agregarColumna();
        MatrizDist.agregarFila();

        MatrizCamMin.agregarColumna();
        MatrizCamMin.agregarFila();

        MatrizDist.rellenarColumna(MatrizDist.Ancho - 1, Control.INF);
        MatrizDist.rellenarFila(MatrizDist.Alto - 1, Control.INF);

        MatrizDist.editarMatriz(MatrizDist.Ancho - 1, MatrizDist.Alto - 1, 0);

        buscarCaminosMinimos();
        Ciudades.add(Ci);
        add(Ci);
        Control.Expositor._actualizar_matrices();
        return Ci;
    }

    public void conectarCiudades(Ciudad Or, Ciudad Fin, int Dist) {
        Camino Cm = new Camino(Or, Fin, Dist);
        Or.Caminos.put(Fin, Cm);
        Fin.Caminos.put(Or, Cm);

        MatrizAdj.editarMatriz(Ciudades.indexOf(Or), Ciudades.indexOf(Fin), 1);
        MatrizAdj.editarMatriz(Ciudades.indexOf(Fin), Ciudades.indexOf(Or), 1);

        MatrizDist.editarMatriz(Ciudades.indexOf(Or), Ciudades.indexOf(Fin), Dist);
        MatrizDist.editarMatriz(Ciudades.indexOf(Fin), Ciudades.indexOf(Or), Dist);

        buscarCaminosMinimos();

        Control.Expositor._actualizar_matrices();
        add(Cm);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //MEJORAR RESOLUCION DE FORMAS
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        Graphics2D g2 = (Graphics2D)g.create();

        g2.setRenderingHints(qualityHints);
        g2.setStroke(new BasicStroke(10));
        g2.setColor(Color.lightGray);
        g2.setFont(Control.TextoCiudad);

        if(Ciudades.isEmpty() && !Control.PanPrinc.PnNomCiu.Activo && Control.ESTADO != Control.ESTAGREGAR)
            g2.drawString("Agrega una ciudad", 120, 350);

        // DIBUJAR CONEXIONES
        for (Ciudad ciu : Ciudades) {
            Point PIni = ciu.getLocation();
            for (Ciudad ciuVec : ciu.Caminos.keySet()) {
                Point PFin = ciuVec.getLocation();
                g2.setColor(Control.ColMorado);
                g2.drawLine(PIni.x + Control.CiudadTam / 2, PIni.y + Control.CiudadTam / 2 + 10,
                        PFin.x + Control.CiudadTam / 2, PFin.y + Control.CiudadTam / 2 + 10);
            }
        }

        if(Caminos != null){
            int PuntosX[] = new int[Caminos.size()];
            int PuntosY[] = new int[Caminos.size()];

            for (int i = 0; i < Caminos.size(); i++) {
                Point PosCiudad = Caminos.get(i).getLocation();
                PuntosX[i] = PosCiudad.x + Control.CiudadTam / 2;
                PuntosY[i] = PosCiudad.y + Control.CiudadTam / 2 + 10;
            }

            g2.setColor(Control.ColNaranja);
            g2.drawPolyline(PuntosX, PuntosY, Caminos.size());
        }
    
        if(Control.ESTADO == Control.ESTAGREGAR)
            g2.drawImage(Control.ImCiudad, PosMouse.x, PosMouse.y, this);
    }

    public void buscarCaminosMinimos() {

        int i, j, k;

        MatrizCamMin.Valores = new ArrayList<>(MatrizDist.Valores);
        MatrizCamMin.Alto = MatrizDist.Alto;
        MatrizCamMin.Ancho = MatrizDist.Ancho;

        for (k = 0; k < MatrizCamMin.Alto; k++)
            for (i = 0; i < MatrizCamMin.Alto; i++)
                for (j = 0; j < MatrizCamMin.Alto; j++) {
                    int Val = MatrizCamMin.celda(k, i) + MatrizCamMin.celda(j, k);
                    if (Val < MatrizCamMin.celda(j, i))
                        MatrizCamMin.editarMatriz(j, i, Val);
                }

        MatrizCamMin.imprimirMatriz();
    }

    public void verificarCaminos(Ciudad Nodo) {
        for (Ciudad Vecino : Nodo.Caminos.keySet()) {
            int IndexNodo = Ciudades.indexOf(Nodo);
            int IndexVec = Ciudades.indexOf(Vecino);
            int Distancia = MatrizCamDijkstra.celda(0, IndexNodo) + MatrizDist.celda(IndexNodo, IndexVec);

            if (Distancia < MatrizCamDijkstra.celda(0, IndexVec)) {
                MatrizCamDijkstra.editarMatriz(0, IndexVec, Distancia);
                MatrizCamDijkstra.editarMatriz(1, IndexVec, IndexNodo);
                MatrizCamDijkstra.imprimirMatriz();
                verificarCaminos(Vecino);
            }
        }
    }

    public ArrayList<Ciudad> buscarCaminoDijsktra(Ciudad IN, Ciudad FIN) {

        int Inicio = Ciudades.indexOf(IN);
        int Final = Ciudades.indexOf(FIN);

        MatrizCamDijkstra.borrarMatriz();
        MatrizCamDijkstra.editarMatriz(1, Ciudades.size() - 1, 0);
        MatrizCamDijkstra.rellenarColumna(0, Control.INF);
        MatrizCamDijkstra.editarMatriz(0, Inicio, 0);
        MatrizCamDijkstra.imprimirMatriz();
        verificarCaminos(Ciudades.get(Inicio));

        ArrayList<Ciudad> Cam = new ArrayList<Ciudad>();
        int Nodo = Final;

        while (Nodo != Inicio) {
            Cam.add(Ciudades.get(Nodo));
            Nodo = MatrizCamDijkstra.celda(1, Nodo);
        }
        Cam.add(Ciudades.get(Inicio));

        MatrizCamDijkstra.imprimirMatriz();
        return Cam;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
            
        if(Control.ESTADO == Control.ESTAGREGAR && !Control.PanPrinc.PnNomCiu.Activo){
            Control.PanPrinc.PnNomCiu.X = e.getX();
            Control.PanPrinc.PnNomCiu.Y = e.getY();
            Control.PanPrinc.PnNomCiu._mover(new Point(650, 300));
            Control.PanPrinc.PnNomCiu.JTNombre.requestFocus();
            Control.PanPrinc.PnNomCiu.JTNombre.setText("");
            Control.PanPrinc.PnNomCiu.Activo = true;
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(Control.ESTADO == Control.ESTAGREGAR && !Control.PanPrinc.PnNomCiu.Activo){
            PosMouse = new Point(e.getXOnScreen() - Control.Ventana.getLocation().x - getLocation().x - Control.CiudadTam/2,
                                 e.getYOnScreen() - Control.Ventana.getLocation().y - getLocation().y - Control.CiudadTam/2);
            System.out.print(PosMouse.x + ", ");
            System.out.println(PosMouse.y);
            repaint();   
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}


    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}
}
