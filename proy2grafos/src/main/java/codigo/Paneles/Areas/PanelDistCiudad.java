package codigo.Paneles.Areas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JTextField;

import codigo.Control;
import codigo.Paneles.Componentes.Ciudad;
import codigo.Paneles.Componentes.Objetos;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

@SuppressWarnings(value = "serial")
public class PanelDistCiudad extends Objetos implements ActionListener {

    public boolean Activo = false;
    public Ciudad CiudadFin;
    public JTextField JTNombre = new JTextField();
    int X,Y;

    public PanelDistCiudad(int X, int Y) {

        this.X = X;
        this.Y = Y;

        TamX = 300;
        TamY = 100;

        setLayout(null);
        setBounds(650, 1000, 300, 100);
        setOpaque(false);


        JTNombre.setBounds(100, 45, 100, 20);
        add(JTNombre);

        JButton BtAceptar = new JButton("Aceptar");
        BtAceptar.addActionListener(this);
        BtAceptar.setBounds(100, 70, 100, 20);
        add(BtAceptar);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // MEJORAR RESOLUCION DE FORMAS
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHints(qualityHints);

        g2.setFont(Control.TextoTab);

        g2.setColor(Color.lightGray);
        g2.fillRoundRect(0, 0, 300, 100, 25, 25);
        g2.setColor(Color.white);
        g2.fillRoundRect(5, 5, 290, 90, 25, 25);
        g2.setColor(Color.gray);
        g2.drawString("Cual es la distancia?", 20, 30);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        _mover(new Point(650, 1000));

        Control.AreaTrabajo.conectarCiudades(Control.CiudadAux, CiudadFin, Integer.parseInt(JTNombre.getText()));
        Control.Ventana.repaint();
        Control.CiudadS = null;
        Control.CiudadAux = null;
        Activo = false;
    }
}
