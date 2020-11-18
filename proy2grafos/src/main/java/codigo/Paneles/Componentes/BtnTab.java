package codigo.Paneles.Componentes;

import javax.swing.JLabel;
import javax.swing.JPanel;

import codigo.Control;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings(value = "serial")
public class BtnTab extends JPanel implements MouseListener {

    public JLabel TextoTab;
    public int EsNorm = 0;
    public int EstEnc = 1;
    public int EstSel = 2;
    public int Estado = 0;

    public BtnTab(String Texto, int PosX, int PosY, int TamX, int TamY) {
        setLayout(null);
        setBounds(PosX, PosY, TamX, TamY);
        setOpaque(false);

        addMouseListener(this);

        TextoTab = new JLabel(Texto);
        TextoTab.setHorizontalAlignment(JLabel.CENTER);
        TextoTab.setForeground(Color.WHITE);
        TextoTab.setFont(Control.TextoTab);
        TextoTab.setBounds(0, 0, TamX, 30);
        add(TextoTab);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //MEJORAR RESOLUCION DE FORMAS
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        Graphics2D g2 = (Graphics2D)g.create();

        g2.setRenderingHints(qualityHints);
        

        if(Estado == EstEnc){
            g2.setColor(Control.ColMorado);
            g2.fillRoundRect(0,0, TextoTab.getWidth(), TextoTab.getHeight(), 15, 15);
        }else if(Estado == EstSel){
            g2.setColor(Control.ColNaranja);
            g2.fillRoundRect(0,0, TextoTab.getWidth(), TextoTab.getHeight(), 15, 15);
        }
        
    }

    @Override public void mouseClicked(MouseEvent e) {}

    @Override public void mousePressed(MouseEvent e) {}

    @Override public void mouseReleased(MouseEvent e) {}

    @Override public void mouseEntered(MouseEvent e) {
        if(Estado != EstSel){
            Estado = EstEnc;
            repaint();
        }
    }

    @Override public void mouseExited(MouseEvent e) {
        if(Estado != EstSel){
            Estado = EsNorm;
            repaint();
        }
    }
}
