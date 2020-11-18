package codigo.Paneles.Areas;

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

import codigo.Paneles.Componentes.*;
import codigo.Control;
import codigo.MatrizDin;

@SuppressWarnings(value = "serial")
public class PanelAreaTrabajo extends JPanel implements MouseInputListener {

    public ArrayList<Ciudad> Caminos = new ArrayList<Ciudad>();
    public ArrayList<Ciudad> Ciudades = new ArrayList<Ciudad>();
    public ArrayList<Ciudad> NodosVer = new ArrayList<Ciudad>();

    public MatrizDin MatrizAdj = new MatrizDin();
    public MatrizDin MatrizDist = new MatrizDin();
    public MatrizDin MatrizCamMin = new MatrizDin();
    public MatrizDin MatrizRecorrido = new MatrizDin();
    public MatrizDin MatrizCamDijkstra = new MatrizDin();
    
    public Point PosMouse = new Point(-200, 0);
    public String Mensaje = "";

    public PanelAreaTrabajo() {

        setLayout(null);
        setBounds(485, 0, 635, 700);
        setOpaque(false);

        addMouseListener(this);
        addMouseMotionListener(this);
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

        MatrizRecorrido.eliminarColumna(ind);
        MatrizRecorrido.eliminarFila(ind);

        // ELIMINAR CAMINOS CON OTRAS CIUDADES
        for (Ciudad Vec : Ci.Caminos.keySet()) {
            remove(Ci.Caminos.get(Vec));
            Vec.Caminos.remove(Ci);
        }

        Caminos = null;
        Ciudades.remove(Ci);
        remove(Ci);

        // ACTUALIZAR MATRICES
        buscarCaminosMinimos();
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

        MatrizRecorrido.agregarColumna();
        MatrizRecorrido.agregarFila();

        MatrizDist.rellenarColumna(MatrizDist.Ancho - 1, Control.INF);
        MatrizDist.rellenarFila(MatrizDist.Alto - 1, Control.INF);
        MatrizDist.editarMatriz(MatrizDist.Ancho - 1, MatrizDist.Alto - 1, 0);

        MatrizCamMin.agregarColumna();
        MatrizCamMin.agregarFila();

        buscarCaminosMinimos();
        Ciudades.add(Ci);
        add(Ci);
        Control.Expositor._actualizar_matrices();

        return Ci;
    }

    public void conectarCiudades(Ciudad Or, Ciudad Fin, int Dist) {
        Camino Cm = new Camino(Or, Fin, Dist);

        // Actualizar Caminos entre ciudades
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

    public void buscarCaminosMinimos() {

        for(int e = 0; e < MatrizRecorrido.Alto; e++)
            MatrizRecorrido.rellenarColumna(e, e);
        
        MatrizCamMin.Valores = new ArrayList<>(MatrizDist.Valores);
        MatrizCamMin.Alto = MatrizDist.Alto;
        MatrizCamMin.Ancho = MatrizDist.Ancho;

        for(int n = 0; n < MatrizCamMin.Alto; n++)
            for(int l = 0; l < MatrizCamMin.Alto; l++)
                if(l != n){
                    int Pos1 = MatrizCamMin.celda(n, l);
                    
                    for(int p = 0; p <  MatrizCamMin.Alto; p ++){
                        if(p != n){
                            int Pos2 = MatrizCamMin.celda(p, n);
                            if(Pos1 + Pos2 < MatrizCamMin.celda(p, l)){
                                MatrizCamMin.editarMatriz(p, l, Pos1 + Pos2);
                                MatrizRecorrido.editarMatriz(p, l, n);
                            }
                        }
                    }
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
        Mensaje = "";
        int Inicio = Ciudades.indexOf(IN);
        int Final = Ciudades.indexOf(FIN);

        // INICIAR TABLA MATRIZ DIJKSTRA
        MatrizCamDijkstra.borrarMatriz();
        MatrizCamDijkstra.editarMatriz(1, Ciudades.size() - 1, 0);
        MatrizCamDijkstra.rellenarColumna(0, Control.INF);
        MatrizCamDijkstra.rellenarColumna(1, Control.NADIE);
        MatrizCamDijkstra.editarMatriz(0, Inicio, 0);

        // BUSCAR CAMINOS MINIMOS
        verificarCaminos(Ciudades.get(Inicio));

        // GENERAR CAMINO
        ArrayList<Ciudad> Cam = new ArrayList<Ciudad>();
        int Nodo = Final;

        while (Nodo != Inicio && Nodo != Control.NADIE) {
            Cam.add(Ciudades.get(Nodo));
            Nodo = MatrizCamDijkstra.celda(1, Nodo);
        }
        Cam.add(Ciudades.get(Inicio));

        if(Nodo == Control.NADIE){
            Mensaje = "No existe un camino entre " + IN.Nombre + " y " + FIN.Nombre;
            return null;
        }else
            return Cam;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        // MEJORAR RESOLUCION DE FORMAS
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHints(qualityHints);
        g2.setStroke(new BasicStroke(10));


        if(Mensaje.isEmpty()){
            g2.setColor(Control.ColNaranja);
            g2.setFont(Control.TextoTab);
            g2.drawString(Mensaje, 20, 50);
        }

        g2.setColor(Color.lightGray);
        g2.setFont(Control.TextoCiudad);

        if (Ciudades.isEmpty() && !Control.PanPrinc.PnNomCiu.Activo && Control.ESTADO != Control.ESTAGREGAR)
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

        // DIBUJAR CAMINO
        if (Caminos != null) {
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

        // DIBUJAR SILUETA DE CIUDAD
        if (Control.ESTADO == Control.ESTAGREGAR)
            g2.drawImage(Control.ImCiudad, PosMouse.x, PosMouse.y, this);
    }

    // AGREGAR CIUDAD
    @Override
    public void mouseReleased(MouseEvent e) {

        if (Control.ESTADO == Control.ESTAGREGAR && !Control.PanPrinc.PnNomCiu.Activo) {
            Control.PanPrinc.PnNomCiu.X = e.getX();
            Control.PanPrinc.PnNomCiu.Y = e.getY();
            Control.PanPrinc.PnNomCiu._mover(new Point(650, 300));
            Control.PanPrinc.PnNomCiu.JTNombre.requestFocus();
            Control.PanPrinc.PnNomCiu.JTNombre.setText("");
            Control.PanPrinc.PnNomCiu.Activo = true;
        }

    }

    // ACTUALIZAR POSICION DE SILUETA
    @Override
    public void mouseMoved(MouseEvent e) {
        if (Control.ESTADO == Control.ESTAGREGAR && !Control.PanPrinc.PnNomCiu.Activo) {
            PosMouse = new Point(
                    e.getXOnScreen() - Control.Ventana.getLocation().x - getLocation().x - Control.CiudadTam / 2,
                    e.getYOnScreen() - Control.Ventana.getLocation().y - getLocation().y - Control.CiudadTam / 2);
            System.out.print(PosMouse.x + ", ");
            System.out.println(PosMouse.y);
            repaint();
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }
}
