package codigo.Paneles.Componentes;

import java.awt.Graphics;

import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

@SuppressWarnings(value = "serial")
public class BtnCintaOp extends JPanel implements MouseListener {

    private BufferedImage ImBtnNormal;
    private BufferedImage ImBtnEncima;
    private BufferedImage ImBtnSel;
    public boolean Select = false;
    private boolean Encima = false;


    public BtnCintaOp(int X, int Y, BufferedImage ImNorm, BufferedImage ImEn, BufferedImage ImSel) {
        setBounds(X, Y, 50, 50);
        setOpaque(false);

        ImBtnNormal = ImNorm;
        ImBtnSel    = ImSel;
        ImBtnEncima = ImEn;

        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(Select)
            g.drawImage(ImBtnSel, 0, 0, this);
        else if (Encima)
            g.drawImage(ImBtnEncima, 0, 0, this);
        else
            g.drawImage(ImBtnNormal, 0, 0, this);
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    
    @Override public void mouseEntered(MouseEvent e) {
        Encima  = true;
        repaint();
    }
    @Override public void mouseExited(MouseEvent e) {
        Encima = false;
        repaint();
    }
}
