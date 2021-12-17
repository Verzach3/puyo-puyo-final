package proyecto.OriginalGameComponents;

import java.awt.*;

public class GameOverComponent {

    public GameOverComponent(Graphics2D g2, float alpha, float alpha1, int len, int cols, int rows){
        g2.setPaint(Color.white);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        if (alpha < 0.9f) {
            alpha = alpha + 0.02f;//to build the animation of alpha blending
        }
        g2.fill(new Rectangle(0, 0, len * cols, len * rows));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setPaint(Color.red);
        g2.setFont(new Font("Ariel", Font.PLAIN, len / 2));
        g2.drawString("Game Over", len * 3 / 2, rows * len / 2);
        g2.setPaint(Color.blue);
        g2.setFont(new Font("Ariel", Font.PLAIN, len / 3));
        g2.drawString("Precione <Enter> para reiniciar el juego", len / 2, (rows + 1) * len / 2);
    }
}
