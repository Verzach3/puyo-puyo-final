package proyecto.OriginalGameComponents;

import java.awt.*;

public class NotStartedComponent {

    public NotStartedComponent(Graphics2D g2, float alpha, float alpha1, int len, int cols, int rows, int level){
        g2.setPaint(Color.yellow);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));//Aplica transparencia
        g2.fill(new Rectangle(0, rows * len / 4, (cols + 3) * len, (rows + 1) * len / 2));
        g2.setPaint(Color.blue);
        g2.setFont(new Font("Ariel", Font.PLAIN, len / 2));
        g2.drawString("  Nivel: " + level, len * 3, rows * len / 3);
        g2.setFont(new Font("Ariel", Font.PLAIN, len / 3));
        g2.drawString("Use <↑> y <↓> para cambiar el nivel del juego", len / 4, (rows + 2) * len / 3);
        g2.setFont(new Font("Ariel", Font.BOLD, len / 3));
        g2.drawString("Precione <Enter> para iniciar el juego", len, rows * len / 2);
        g2.setFont(new Font("Ariel", Font.PLAIN, len / 3));
        g2.drawString("           Use las flechas para mover el puyo.", 0, (rows + 1) * len / 2);
        g2.drawString("Puedes rotar la posición el puyo usando las flechas.", len, (rows + 2) * len / 2);
        g2.drawString("      Hay puntos cada vez que hay 4 puyos del mismo color", len / 3, (rows + 3) * len / 2);
        g2.drawString("Precione <p> para pausar el juego", len, (rows + 5) * len / 2);
        g2.drawString("Precione <Escape> para salir del juego", len, (rows + 6) * len / 2);
        g2.drawString("           Para ver los puntajes preciona <C>", 0, (rows + 7) * len / 2);
    }
}
