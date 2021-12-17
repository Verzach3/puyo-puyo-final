package proyecto.AddedScreens;

import proyecto.GamePane;
import proyecto.PuyoPuyo;
import proyecto.Utils.ImageLoader;
import proyecto.Utils.SaveUtil;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class PausedScreen extends JPanel {

    PuyoPuyo puyoInstance;
    ImageLoader imageLoader = new ImageLoader();
    JButton startButtton;
    JButton loadButton;
    JButton saveButton;
    JButton exitButton;

    public PausedScreen(PuyoPuyo puyoInstance) {
        setSize(468, 675);
        setLayout(null);
        this.puyoInstance = puyoInstance;

        //StartButton
        startButtton = new JButton();
        startButtton.setText("Reanudar");
        startButtton.setLocation(55, 332);
        startButtton.setSize(358, 53);
        startButtton.addActionListener(e -> {
            setVisible(false);
            puyoInstance.gp.setVisible(true);
            puyoInstance.gp.requestFocus();
            puyoInstance.gp.timer.start();
            puyoInstance.gp.paused = false;
        });

        add(startButtton);

        //ScoreButton
        saveButton = new JButton();
        saveButton.setText("Guardar");
        saveButton.setLocation(55, 486);
        saveButton.setSize(358, 53);
        saveButton.addActionListener(e -> {
            SaveUtil saveUtil = new SaveUtil();
            String saveName = JOptionPane.showInputDialog("Ingrese el nombre de el guardado");
            if (saveName != null) {
                try {
                    saveUtil.saveGame(GamePane.scr, saveName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(saveButton);


        //LoadButton
        loadButton = new JButton();
        loadButton.setText("Reiniciar");
        loadButton.setLocation(55, 409);
        loadButton.setSize(358, 53);
        loadButton.addActionListener(e -> {
            setVisible(false);
            puyoInstance.gp.paused = false;
            puyoInstance.gp.newGame();
            puyoInstance.gp.requestFocus();

        });
        add(loadButton);


        //ExitButton
        exitButton = new JButton();
        exitButton.setText("Salir");
        exitButton.setLocation(55, 563);
        exitButton.setSize(358, 53);
        exitButton.addActionListener(e -> {
            setVisible(false);
            puyoInstance.mainMenu.setVisible(true);
            puyoInstance.mainMenu.setFocusable(true);
            puyoInstance.mainMenu.requestFocus();
        });
        add(exitButton);


        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setVisible(false);
                    puyoInstance.gp.setVisible(true);
                    puyoInstance.gp.requestFocus();
                    puyoInstance.gp.timer.start();
                    puyoInstance.gp.paused = false;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


    }

}
