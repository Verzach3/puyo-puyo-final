package proyecto.AddedScreens;

import proyecto.PuyoPuyo;
import proyecto.Utils.ImageLoader;
import proyecto.Utils.ScoreBoardUtil;
import proyecto.Utils.ScoreRecord;

import javax.swing.*;

public class GameOverScreen extends JPanel{

    PuyoPuyo puyoInstance;
    ImageLoader imageLoader = new ImageLoader();
    JButton startButtton;
    JButton loadButton;
    JButton scoreButton;
    JButton exitButton;
    ScoreBoardUtil scoreBoardUtil = new ScoreBoardUtil();
    public GameOverScreen(PuyoPuyo puyoInstance){
        setSize(468, 675);
        setLayout(null);
        this.puyoInstance = puyoInstance;

        //NameInput
        JLabel infoLabel = new JLabel();
        infoLabel.setText("Ingresa Tu Nombre");
        infoLabel.setSize(163, 23);
        infoLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));
        infoLabel.setLocation(55, 244);
        JTextField nameField = new JTextField();
        nameField.setSize(358, 28);
        nameField.setLocation(55, 280);
        add(infoLabel);
        add(nameField);



        //StartButton
        startButtton = new JButton();
        startButtton.setText("Guardar Puntaje");
        startButtton.setLocation(55,332);
        startButtton.setSize(358, 53);
        startButtton.addActionListener(e -> {
            setVisible(false);
            scoreBoardUtil.createScore(new ScoreRecord(nameField.getText(), puyoInstance.gp.score));
            puyoInstance.mainMenu.setVisible(true);
        });
        add(startButtton);




        //LoadButton
        loadButton = new JButton();
        loadButton.setText("Nuevo Juego");
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
