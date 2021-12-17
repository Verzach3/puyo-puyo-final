package proyecto.AddedScreens;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import proyecto.PuyoPuyo;
import proyecto.Utils.Save;
import proyecto.Utils.SaveUtil;

/**
 * SavesMenu
 */
public class SavesMenu extends JPanel implements KeyListener {

    JList savesList;
    JButton loadButton;
    JButton exitButton;
    PuyoPuyo puyoInstance;

    public SavesMenu(PuyoPuyo puyoInstance){
        this.puyoInstance = puyoInstance;
        setSize(468, 675);
        setLayout(null);

        //SavesList
        JList savesList = new JList<>();
        savesList.setSize(358, 469);
        savesList.setLocation(55, 43);
        DefaultListModel savesModel = new DefaultListModel();
        puyoInstance.refreshSaves();
        for (Save save : puyoInstance.saves){
            savesModel.addElement(save.getName());
        }
        savesList.setModel(savesModel);

        add(savesList);
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