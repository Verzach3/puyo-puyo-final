package proyecto.AddedScreens;

import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

import proyecto.PuyoPuyo;
import proyecto.Utils.ImageLoader;
import proyecto.Utils.ScoreBoardUtil;
import proyecto.Utils.ScoreRecord;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MainMenu extends JPanel implements KeyListener{

    PuyoPuyo puyoInstance;
    ImageLoader imageLoader = new ImageLoader(); 
    JLabel headerLogo;
    JButton startButtton;
    JButton loadButton;
    JButton scoreButton;
    JButton exitButton;
    public MainMenu(PuyoPuyo puyoInstance){
        setSize(468, 675);
        setLayout(null);
        this.puyoInstance = puyoInstance;
        this.addKeyListener(this);
        
        //HeaderLogo
        headerLogo = new JLabel();
        headerLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/Resources/headerLogo.png")));
        headerLogo.setLocation(125, 5);
        headerLogo.setSize(300, 300);
        
        
        
        
        add(headerLogo);

        //StartButton
        startButtton = new JButton();
        startButtton.setText("Empezar");
        startButtton.setLocation(55,332);
        startButtton.setSize(358, 53);
        startButtton.addActionListener(e -> puyoInstance.gp.newGame());



        add(startButtton);

        //LoadButton
        loadButton = new JButton();
        loadButton.setText("Cargar");
        loadButton.setLocation(55,409);
        loadButton.setSize(358, 53);
        loadButton.addActionListener(e -> {
            puyoInstance.savesMenu.updateSaves();
            puyoInstance.mainMenu.setVisible(false);
            puyoInstance.savesMenu.setVisible(true);
            puyoInstance.savesMenu.setFocusable(true);
            puyoInstance.savesMenu.requestFocus();
        });
        add(loadButton);

        //ScoreButton
        scoreButton = new JButton();
        scoreButton.setText("Puntajes");
        scoreButton.setLocation(55,486);
        scoreButton.setSize(358, 53);
        scoreButton.addActionListener(e -> {
            puyoInstance.scoresMenu.update();
            puyoInstance.mainMenu.setVisible(false);
            puyoInstance.scoresMenu.setVisible(true);
            puyoInstance.scoresMenu.setFocusable(true);
            puyoInstance.scoresMenu.stopThread = false;
            puyoInstance.scoresMenu.requestFocus();
        });
        add(scoreButton);

        //ExitButton
        exitButton = new JButton();
        exitButton.setText("Salir");
        exitButton.setLocation(55,563);
        exitButton.setSize(358, 53);
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);





    }


    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_1){
            ScoreBoardUtil scoreBoardUtil = new ScoreBoardUtil();
            scoreBoardUtil.printScores();
        }
        if(e.getKeyCode() == KeyEvent.VK_2){
            ScoreBoardUtil scoreBoardUtil = new ScoreBoardUtil();
            scoreBoardUtil.createScore(new ScoreRecord("Setchkooo", 999));;
        }
        if(e.getKeyCode() == KeyEvent.VK_3){
            ScoreBoardUtil scoreBoardUtil = new ScoreBoardUtil();
            scoreBoardUtil.getScores();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
