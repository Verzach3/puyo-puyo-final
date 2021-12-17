package proyecto.AddedScreens;

import proyecto.PuyoPuyo;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * SavesMenu
 */
public class ScoresMenu extends JPanel implements KeyListener {

    PuyoPuyo puyoInstance;
    public ScoresMenu(PuyoPuyo puyoInstance){
        this.puyoInstance = puyoInstance;
        setSize(468, 675);
        addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_1){
            System.out.println("Funciona");
        }
        if(e.getKeyCode() == KeyEvent.VK_2){
            setVisible(false);
            puyoInstance.mainMenu.setVisible(true);
            puyoInstance.mainMenu.setFocusable(true);
            puyoInstance.mainMenu.requestFocus();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }


}