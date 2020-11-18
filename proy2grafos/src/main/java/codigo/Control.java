package codigo;

import java.awt.Point;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import codigo.Paneles.Componentes.Ciudad;
import codigo.Paneles.Areas.PanelAreaTrabajo;
import codigo.Paneles.Areas.PanelExpositor;
import codigo.Paneles.Areas.PanelPrincipal;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;

public class Control {

    public static final int INF = 10000;
    public static final int NADIE = 100001;
    public static final int CiudadTam = 113;

    public static Point VentTam = new Point(1100, 700);

    public static PanelExpositor Expositor;
    public static JFrame Ventana;
    public static PanelAreaTrabajo AreaTrabajo;
    public static PanelPrincipal PanPrinc;

    //ESTADP DEL EDITOR
    public static final int ESTNORMAL = 0;
    public static final int ESTCONECT = 1;
    public static final int ESTBORRAR = 2;
    public static final int ESTBUSCAR = 3;
    public static final int ESTAGREGAR = 4;

    public static int ESTADO = ESTNORMAL;

    //FUENTES
    static public Color ColSel = new Color(255,102,0,255);
    static public Font FntCarta = new Font("Arial Rounded MT Bold", Font.PLAIN, 20);
    static public Font FntTitulo = new Font("Arial Rounded MT Bold", Font.BOLD, 40);
    static public Font FntTituloTexto = new Font("Arial Rounded MT Bold", Font.PLAIN, 30);
    public static final Font TextoTab = new Font("Arial", Font.BOLD, 20);
    public static final Font TextoTabla = new Font("Arial", Font.PLAIN, 15);
    public static final Font TextoCiudad = new Font("Arial", Font.BOLD, 40);

    public static final Color ColNaranja = new Color(203, 118, 53, 255);
    public static final Color ColMorado = new Color(110, 106, 164, 255);

    public static Ciudad CiudadAux;
    public static Ciudad CiudadS;

    //IMAGENES
    public static BufferedImage ImFondo;
    public static BufferedImage ImCiudad;
    public static BufferedImage ImCiudadSel;

    public static BufferedImage BtnAgregar0;
    public static BufferedImage BtnAgregar1;
    public static BufferedImage BtnAgregar2;

    public static BufferedImage BtnBorrar0;
    public static BufferedImage BtnBorrar1;
    public static BufferedImage BtnBorrar2;

    public static BufferedImage BtnConect0;
    public static BufferedImage BtnConect1;
    public static BufferedImage BtnConect2;

    public static BufferedImage BtnBuscar0;
    public static BufferedImage BtnBuscar1;
    public static BufferedImage BtnBuscar2;


    public static BufferedImage BtnAjustar0;
    public static BufferedImage BtnAjustar1;
    public static BufferedImage BtnAjustar2;

    public Control(){
        try{
            ImFondo = ImageIO.read(getClass().getResource("/Fondo.png"));
            ImCiudad = ImageIO.read(getClass().getResource("/Ciudad2.png"));
            ImCiudadSel = ImageIO.read(getClass().getResource("/CiudadSel.png"));

            BtnAgregar0 = ImageIO.read(getClass().getResource("/BtnAgregar0.png"));
            BtnAgregar1 = ImageIO.read(getClass().getResource("/BtnAgregar1.png"));
            BtnAgregar2 = ImageIO.read(getClass().getResource("/BtnAgregar2.png"));

            BtnBorrar0 = ImageIO.read(getClass().getResource("/BtnBorrar0.png"));
            BtnBorrar1 = ImageIO.read(getClass().getResource("/BtnBorrar1.png"));
            BtnBorrar2 = ImageIO.read(getClass().getResource("/BtnBorrar2.png"));

            BtnConect0 = ImageIO.read(getClass().getResource("/BtnConect0.png"));
            BtnConect1 = ImageIO.read(getClass().getResource("/BtnConect1.png"));
            BtnConect2 = ImageIO.read(getClass().getResource("/BtnConect2.png"));

            BtnBuscar0 = ImageIO.read(getClass().getResource("/BtnBuscar0.png"));
            BtnBuscar1 = ImageIO.read(getClass().getResource("/BtnBuscar1.png"));
            BtnBuscar2 = ImageIO.read(getClass().getResource("/BtnBuscar2.png"));

            BtnAjustar0 = ImageIO.read(getClass().getResource("/BtnAjustar0.png"));
            BtnAjustar1 = ImageIO.read(getClass().getResource("/BtnAjustar1.png"));
            BtnAjustar2 = ImageIO.read(getClass().getResource("/BtnAjustar2.png"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
