package proy2;

import java.util.ArrayList;

public class MatrizDin {
    public ArrayList<Integer> Valores = new ArrayList<Integer>();
    public int Ancho = 0;
    public int Alto = 0;

    /**
     * Crea una nueva Matriz Dinamica vacia
     */
    public MatrizDin(){}

    /**
     * Crea una nueva Matriz Dinamica con las dimensiones 
     * especificadas
     * @param ancho Ancho de la Matriz
     * @param alto Alto de la Matriz
     */
    public MatrizDin(int ancho, int alto){
        for (int h = 0; h < alto; h++) 
            for(int w = 0; w < ancho; w++)
                Valores.add(0);
        
        Ancho = ancho;
        Alto = alto;
    }

    public int celda(int posx, int posy){
        return Valores.get(posy * Ancho + posx);
    }

    public void editarMatriz(int posx, int posy, int dato){

        if(posx > Ancho - 1){
            int anchoIni = (Ancho - 1);
            for (int i = 0; i < posx - anchoIni; i++) 
                this.agregarColumna();
        }
        
        if(posy > Alto - 1){
            int altoIni = (Alto - 1);
            for (int i = 0; i < posy - altoIni; i++) 
                this.agregarFila();
        }
            

        int posFin = posy * Ancho + posx;
        Valores.remove(posFin);
        Valores.add(posFin, dato);
    }

    public void eliminarColumna(int pos){
        for (int i = 0; i < Alto; i++) {
            Valores.remove(pos + i*Ancho - i);
        }
        Ancho --; 
    }

    public void agregarColumna(){
        for (int i = 0; i < Alto; i++) {
            Valores.add((i + 1)*Ancho + i, 0);
        }
        Ancho ++;
    }

    public void agregarFila(){
        for (int i = 0; i < Ancho; i++) {
            Valores.add(0);
        }
        Alto ++;
    }

    public void eliminarFila(int pos){
        for (int i = 0; i < Ancho; i++) {
            Valores.remove(pos * Ancho);
        }
        Alto --; 
    }

    public void imprimirMatriz(){
        for (int h = 0; h < Alto; h++){
            for(int w = 0; w < Ancho; w++)
                System.out.print(Valores.get(h * Ancho + w) + " ");
            System.out.println("");
        }
        System.out.println("");
    }

    public void rellenarFila(int pos, int dato){
        for (int j = 0; j < Ancho; j++) {
            this.editarMatriz(j, pos, dato);
        }
    }

    public void rellenarColumna(int pos, int dato){
        for (int j = 0; j < Alto; j++) {
            this.editarMatriz(pos, j, dato);
        }
    }
}
