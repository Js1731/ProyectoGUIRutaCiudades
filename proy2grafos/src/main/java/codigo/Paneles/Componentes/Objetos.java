package codigo.Paneles.Componentes;

import javax.swing.JPanel;

import codigo.Control;

import java.awt.Point;

@SuppressWarnings(value = "serial")
public class Objetos extends JPanel {
    protected int TamX = 0;
    protected int TamY = 0;

    // MOVER A POSICION
    public void _mover(final Point PosFin) {
        Thread Mover = new Thread(new Runnable() {

            @Override
            public void run() {
                Point PosAct = getLocation();
                Point Vel = new Point();

                while (PosAct.distance(PosFin) > 10) {
                    PosAct = getLocation();

                    Vel.x = Math.round((PosFin.x - PosAct.x) * 0.25f);
                    Vel.y = Math.round((PosFin.y - PosAct.y) * 0.25f);

                    setBounds(PosAct.x + Vel.x, PosAct.y + Vel.y, TamX, TamY);

                    Control.Ventana.repaint();
                    System.out.println(PosAct.y);
                    //ESPERAR
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                }

                setLocation(PosFin);
			}
        });

        Mover.start();
    }
}