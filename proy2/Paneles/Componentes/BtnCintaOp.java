package proy2.Paneles.Componentes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import proy2.Control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

@SuppressWarnings(value = "serial")
public class BtnCintaOp extends JPanel implements MouseListener {

    private BufferedImage ImBtnNormal;
    private BufferedImage ImBtnSel;
    public boolean Select = false;


    public BtnCintaOp(int X, int Y, BufferedImage ImNorm, BufferedImage ImSel) {
        setBounds(X, Y, 50, 50);
        setOpaque(false);

        ImBtnNormal = ImNorm;
        ImBtnSel    = ImSel;

        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(Select)
            g.drawImage(ImBtnSel, 0, 0, this);
        else
            g.drawImage(ImBtnNormal, 0, 0, this);
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
