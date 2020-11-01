package proy2;

import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;

import proy2.Paneles.Areas.PanelAreaTrabajo;
import proy2.Paneles.Areas.PanelPrincipal;

public class Control {

    public static Point VentTam = new Point(1100, 700);

    public static JFrame Ventana;
    public static PanelAreaTrabajo AreaTrabajo;
    public static PanelPrincipal PanPrinc;

    public static final int ESTNORMAL = 0;
    public static final int ESTCONECT = 1;
    public static int ESTADO = ESTNORMAL;

    public static Ciudad CiudadIni;
    public static Ciudad CiudadFin;

    public static void actualizarTablas(){

    }
    
}
