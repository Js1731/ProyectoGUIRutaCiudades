package proy2.Paneles.Componentes;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BtnTab extends JPanel implements MouseListener {

    public BtnTab(String Texto, int PosX, int PosY, int TamX, int TamY) {
        setLayout(null);
        JLabel TextoTab = new JLabel(Texto);
        setBounds(PosX, PosY, TamX, TamY);
        TextoTab.setBounds(0, 0, 100, 50);
        setBackground(Color.LIGHT_GRAY);

        addMouseListener(this);

        add(TextoTab);
    }

    public void Seleccionar(){};

    @Override
    public void mouseClicked(MouseEvent e) {
        Seleccionar();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
