package proyecto.OriginalGameComponents;

import java.awt.*;

public class PausedComponent {

    public PausedComponent(Graphics2D g2, float alpha, float alpha1, int len, int cols, int rows){
        g2.setPaint(Color.white);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha1));
        if (alpha1 < 0.9f) {
            alpha1 = alpha1 + 0.02f;
        }
        g2.fill(new Rectangle(0, 0, len * cols, len * rows));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setPaint(Color.blue);
        g2.setFont(new Font("Ariel", Font.PLAIN, len / 2));
        g2.drawString("Juego Pausado", len * 3 / 2, rows * len / 2);
        g2.setFont(new Font("Ariel", Font.PLAIN, len / 3));
        g2.drawString("   Precione <p> para volver al juego", len / 2, (rows + 1) * len / 2);
        g2.drawString("  Precione <Escape> para salir del juego", len / 2, (rows + 2) * len / 2);
        g2.drawString(" Precione <Enter> para reiniciar el juego", len / 2, (rows + 3) * len / 2);
    }
}
