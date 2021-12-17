package proyecto.AddedScreens;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.border.Border;

import proyecto.PuyoPuyo;
import proyecto.Utils.Save;

/**
 * SavesMenu
 */
public class SavesMenu extends JPanel implements KeyListener {

    JList savesList;
    JButton loadButton;
    JButton exitButton;
    PuyoPuyo puyoInstance;
    //SavesList
    JScrollPane scrollPane = new JScrollPane();

    public SavesMenu(PuyoPuyo puyoInstance){
        this.puyoInstance = puyoInstance;
        setSize(468, 675);
        setLayout(null);
        savesList = new JList<>();
        updateSaves();





        //LoadButton
        loadButton = new JButton();
        loadButton.setText("Cargar");
        loadButton.setSize(154, 53);
        loadButton.setLocation(55, 562);
        loadButton.addActionListener(e -> {

            puyoInstance.gp.setVisible(true);
            puyoInstance.gp.setFocusable(true);
            puyoInstance.gp.setSize(468, 675);
            puyoInstance.savesMenu.setVisible(false);
            puyoInstance.savesMenu.setFocusable(false);
            puyoInstance.gp.init();
            puyoInstance.gp.generatePuyos();
            puyoInstance.gp.setDelays();
            puyoInstance.gp.timer.start();
            puyoInstance.gp.started = true;
            puyoInstance.saveUtil.readGame(puyoInstance.saves.get(savesList.getSelectedIndex()).getGameStatus(), puyoInstance.gp.scr);
            System.out.println(puyoInstance.saves.get(savesList.getSelectedIndex()).getName() + "Siiii");
        });
        add(loadButton);

        //ExitButton
        exitButton = new JButton();
        exitButton.setText("Salir");
        exitButton.setSize(154, 53);
        exitButton.setLocation(259, 562);
        exitButton.addActionListener(e -> {
            setVisible(false);
            puyoInstance.mainMenu.setVisible(true);
            puyoInstance.mainMenu.requestFocus();

        });
        add(exitButton);


        add(scrollPane);
        setSize(468, 675);
        addKeyListener(this);


    }

    public void updateSaves(){
        savesList.setFont(new java.awt.Font("Segoe UI", 0, 18));

        scrollPane.setSize(358, 469);
        scrollPane.setLocation(55, 43);
        DefaultListModel savesModel = new DefaultListModel();
        puyoInstance.refreshSaves();
        for (Save save : puyoInstance.saves){
            savesModel.addElement(save.getName());
        }
        savesList.setModel(savesModel);
        scrollPane.setViewportView(savesList);
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