package proyecto.AddedScreens;

import proyecto.PuyoPuyo;
import proyecto.Utils.ImageLoader;
import proyecto.Utils.ScoreBoardUtil;
import proyecto.Utils.ScoreRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PausedScreen extends JPanel{

    PuyoPuyo puyoInstance;
    ImageLoader imageLoader = new ImageLoader();
    JButton startButtton;
    JButton loadButton;
    JButton scoreButton;
    JButton exitButton;
    public PausedScreen(PuyoPuyo puyoInstance){
        setSize(468, 675);
        setLayout(null);
        this.puyoInstance = puyoInstance;

        //StartButton
        startButtton = new JButton();
        startButtton.setText("Reanudar");
        startButtton.setLocation(55,332);
        startButtton.setSize(358, 53);
        startButtton.addActionListener(e -> {
            setVisible(false);
            puyoInstance.gp.setVisible(true);
            puyoInstance.gp.requestFocus();
            puyoInstance.gp.timer.start();
            puyoInstance.gp.paused = false;
        });



        add(startButtton);

        //LoadButton
        loadButton = new JButton();
        loadButton.setText("Reiniciar");
        loadButton.setLocation(55,409);
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
        exitButton.setLocation(55,563);
        exitButton.setSize(358, 53);
        exitButton.addActionListener(e ->  {
            setVisible(false);
            puyoInstance.mainMenu.setVisible(true);
            puyoInstance.mainMenu.setFocusable(true);
            puyoInstance.mainMenu.requestFocus();
        });
        add(exitButton);





    }

}
