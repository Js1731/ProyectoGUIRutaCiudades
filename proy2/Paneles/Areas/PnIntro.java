package proy2.Paneles.Areas;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import proy2.Control;

import java.awt.Graphics2D;
import java.awt.RenderingHints;


@SuppressWarnings(value = "serial")
public class PnIntro extends JPanel {

    private int Alfa = 255;

    public PnIntro() {
        System.out.println("MOSTRAR INTRO");
        setBounds(0, 0, 1400, 699);

        Thread Intro = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {}


                while (Alfa > 0) {

                    Alfa -= 5;
                    Control.Ventana.repaint();
                    Control.PanPrinc.repaint();
                    
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {}
                }

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {}

                setBounds(0, 0, 0, 0);
            }
        });
        
        Intro.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        int PosX = 80;
        int PosY = 100;

        g.setColor(new Color(Color.darkGray.getRed(), Color.darkGray.getGreen(), Color.darkGray.getBlue(), Alfa));
        g.fillRect(0, 0, 1400, 699);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(new Color(255,255,255, Alfa));
        g2d.setFont(Control.FntTitulo);

        g2d.drawString("Proyecto #2 Aplicacion de Grafos", PosX, PosY);

        g2d.setColor(new Color(180,180,180, Alfa));
        g2d.setFont(Control.FntTituloTexto);
        g2d.drawString("Grupo 1IL 121          Estructuras de Datos II", PosX, PosY + 50);
        g2d.drawString("Joshua Lopez    8-970-791", PosX, PosY + 150);
        g2d.drawString("Jhorlin Triana  20-14-4815", PosX, PosY + 190);
        g2d.drawString("Aldahir Johnson  8-971-1158", PosX, PosY + 230);
    }
}