package quizgato;

import java.awt.Color;
import java.awt.*;
import javax.swing.JPanel;

public class Dibujar {

    public static void crearDibujo(JPanel pnl) {
        Graphics g = pnl.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, pnl.getSize().width, pnl.getSize().height);
    }

    public static void trazarLinea(JPanel pnl, int x1, int y1, int x2, int y2) {
        Graphics g = pnl.getGraphics();
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
    }

    public static void trazarRectangulo(JPanel pnl, int xSup, int ySup, int anchura, int altura) {
        Graphics g = pnl.getGraphics();
        g.setColor(Color.BLACK);
        g.drawRect(xSup, ySup, anchura, altura);
    }

    public static void trazarCirculo(JPanel pnl, int xSup, int ySup, int anchura, int altura) {
        Graphics g = pnl.getGraphics();
        g.setColor(Color.BLACK);
        g.drawOval(xSup, ySup, anchura, altura);
    }

}
