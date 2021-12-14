package proyecto.GameComponents;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainMenuComponent{
    boolean isActive = true;
    int normalHeight = 30;
    int selectedHeight = 50;


    public void paintComponent(Graphics2D g){
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g.setColor(Color.black);
        g.fillRect(0, 150, 566, normalHeight);
        g.drawString("Hola", 10, 10);


    }



    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
