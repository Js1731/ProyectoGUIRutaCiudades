package proy2.Paneles.Areas;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import proy2.Paneles.Componentes.Camino;
import proy2.Ciudad;
import proy2.Control;
import proy2.MatrizDin;

public class PanelAreaTrabajo extends JPanel{
        
    ArrayList<ArrayList<Ciudad>> Caminos = new ArrayList<ArrayList<Ciudad>>();
    ArrayList<Ciudad> Ciudades = new ArrayList<Ciudad>();
    MatrizDin MatrizAdj = new MatrizDin();
    MatrizDin MatrizDist = new MatrizDin();
    MatrizDin MatrizCamMin = new MatrizDin();
    final int INF = 10000;

    public PanelAreaTrabajo(){

        setLayout(null);
        setBounds(485, 0, 635, 700);
        setBackground(Color.darkGray);

        agregarCiudad("Panama", 50,50);
        agregarCiudad("Chiriqui", 100,100);
        agregarCiudad("Herrera", 200,50);
        agregarCiudad("Colon", 50,400);
        agregarCiudad("Cocle", 100,300);
        agregarCiudad("Los Santos", 200,500);
        agregarCiudad("Panama Oeste", 700,500);


        conectarCiudades(Ciudades.get(0), Ciudades.get(1), 2);
        conectarCiudades(Ciudades.get(2), Ciudades.get(1), 2);
        conectarCiudades(Ciudades.get(0), Ciudades.get(2), 10);
        conectarCiudades(Ciudades.get(0), Ciudades.get(3), 10);
        conectarCiudades(Ciudades.get(4), Ciudades.get(0), 10);
        
        //eliminarCiudad(Ciudades.get(1));

        //Caminos.add(new ArrayList<Ciudad>(Arrays.asList(C1, C2, C3)));
        generarMatrizAnj();
        buscarCaminosMinimos();
        Caminos.add(buscarCaminoDijsktra(3, 2));
    }

    public void eliminarCiudad(Ciudad Ci){
        int ind = Ciudades.indexOf(Ci);
        
        MatrizAdj.eliminarColumna(ind);
        MatrizAdj.eliminarFila(ind);
        
        MatrizCamMin.eliminarColumna(ind);
        MatrizCamMin.eliminarFila(ind);

        MatrizDist.eliminarColumna(ind);
        MatrizDist.eliminarFila(ind);

        for (Ciudad Vec: Ci.Caminos.keySet()){
            remove(Ci.Caminos.get(Vec));
            Vec.Caminos.remove(Ci);
            Vec.Relaciones.remove(Ci);
        }

        Ciudades.remove(Ci);
        remove(Ci);
        Control.PanPrinc.MatrizAdy.actualizar();
        Control.PanPrinc.MatrizDist.actualizar();
        Control.PanPrinc.MatrizCamMin.actualizar();

        Control.Ventana.repaint();
    }

    public Ciudad agregarCiudad(String Nombre, int Px, int Py){
        Ciudad Ci = new Ciudad(Px, Py, Nombre);
        Ciudades.add(Ci);
        add(Ci);
        MatrizDist.agregarColumna();
        MatrizDist.agregarFila();
        MatrizDist.rellenarColumna(MatrizDist.Ancho - 1, INF);
        MatrizDist.rellenarFila(MatrizDist.Alto - 1, INF);
        MatrizDist.editarMatriz(MatrizDist.Ancho - 1, MatrizDist.Alto - 1, 0);

        return Ci;

    }

    public void conectarCiudades(Ciudad Or, Ciudad Fin, int Dist){
        Or.Relaciones.add(Fin);
        Fin.Relaciones.add(Or);

        MatrizDist.editarMatriz(Ciudades.indexOf(Or), Ciudades.indexOf(Fin), Dist);
        MatrizDist.editarMatriz(Ciudades.indexOf(Fin), Ciudades.indexOf(Or), Dist);


        Camino Cm = new Camino(Or, Fin, Dist);
        Or.Caminos.put(Fin, Cm);
        Fin.Caminos.put(Or, Cm);
        add(Cm);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ciudad ciu : Ciudades) {
            Point PI = ciu.getLocation();
            for (Ciudad ciuVec : ciu.Relaciones) {
                Point PF = ciuVec.getLocation();
                g.setColor(Color.BLACK);
                g.drawLine(PI.x + Ciudad.Tam.x/2, PI.y + Ciudad.Tam.y/2, PF.x + Ciudad.Tam.x/2, PF.y + Ciudad.Tam.y/2);
            }
        }

        for (ArrayList<Ciudad> arrayList : Caminos) {

            int PuntosX[] = new int[arrayList.size()];
            int PuntosY[] = new int[arrayList.size()];
            
            for (int i = 0; i < arrayList.size(); i++) {
                Point PosCiudad = arrayList.get(i).getLocation();
                PuntosX[i] = PosCiudad.x + Ciudad.Tam.x/2;
                PuntosY[i] = PosCiudad.y + Ciudad.Tam.x/2;
                
            }
            g.setColor(Color.CYAN);
            g.drawPolyline(PuntosX, PuntosY, arrayList.size());
        }

    }

    public void generarMatrizAnj(){
        for (int ciu = 0; ciu < Ciudades.size(); ciu++) {
            ArrayList<Ciudad> ciurel = Ciudades.get(ciu).Relaciones;
            for (int ciuvec = 0; ciuvec < Ciudades.size(); ciuvec ++) {
                if(ciurel.contains(Ciudades.get(ciuvec)))
                    MatrizAdj.editarMatriz(ciuvec, ciu, 1);
                else
                    MatrizAdj.editarMatriz(ciuvec, ciu, 0);
            }
        }  
        MatrizAdj.imprimirMatriz();
        MatrizDist.imprimirMatriz();
    }

    public void buscarCaminosMinimos(){

        int i, j, k;

        MatrizCamMin.Valores = new ArrayList<>(MatrizDist.Valores);
        MatrizCamMin.Alto = MatrizDist.Alto;
        MatrizCamMin.Ancho = MatrizDist.Ancho;

        for (k = 0; k < MatrizCamMin.Alto; k++)  
            for (i = 0; i < MatrizCamMin.Alto; i++)  
                for (j = 0; j < MatrizCamMin.Alto; j++)  {
                    int Val = MatrizCamMin.celda(k, i) + MatrizCamMin.celda(j, k);
                    if (Val < MatrizCamMin.celda(j, i))  
                        MatrizCamMin.editarMatriz(j, i, Val);  
                }  

        MatrizCamMin.imprimirMatriz();
    }

    public ArrayList<Ciudad> buscarCaminoDijsktra(int Inicio, int Final){
        
        ArrayList<Ciudad> Camino = new ArrayList<Ciudad>();
        int MatrizAux[][] = new int[MatrizAdj.Alto][MatrizAdj.Ancho];
        int minNodo = Inicio;
        for(int paso = 0; paso < MatrizAdj.Alto; paso++){
            for(int nodo = 0; nodo < MatrizAdj.Ancho; nodo++){
                MatrizAux[nodo][paso] = Math.max(MatrizDist.celda(minNodo, nodo), MatrizAux[nodo][paso]);
            }

            for(int nodo = 0; nodo < MatrizAdj.Alto; nodo++)
                
                if(MatrizAux[nodo][paso] < MatrizAux[minNodo][paso])
                    minNodo = nodo;
            
            for(int a = 0; a < MatrizAdj.Ancho; a++)
                MatrizAux[minNodo][a] = INF;
            
            Camino.add(Ciudades.get(minNodo));
        }

        return Camino;
    }  
}
