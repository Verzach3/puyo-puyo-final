
package proyecto;


import javax.swing.*;

import proyecto.AddedScreens.*;
import proyecto.Utils.Save;
import proyecto.Utils.SaveUtil;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 *
 * @author Verzach3
 */
public class PuyoPuyo extends JFrame {
    public GamePane gp;		
    public MainMenu mainMenu;
    public SavesMenu savesMenu;
    public PausedScreen pausedScreen;
    public GameOverScreen gameOverScreen;
    public ScoresMenu scoresMenu;
    int width,height;
    int rows,cols;
    int puyo_len;			
    Dimension screenSize;
    public SaveUtil saveUtil = new SaveUtil();
    public ArrayList<Save> saves = saveUtil.getSaves();
    public PuyoPuyo()
    {
        this.setTitle("Puyo_Puyo");
        cols=6;			// Can be set to any value, game rows*cols depends on this value
        rows=cols*2;
        screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        width=1250;
        height=730;
        puyo_len=(width/8)*2/cols;
        gp=new GamePane(puyo_len,rows,cols, this);
        mainMenu = new MainMenu(this);
        savesMenu = new SavesMenu(this);
        scoresMenu = new ScoresMenu(this);
        pausedScreen = new PausedScreen(this);
        gameOverScreen = new GameOverScreen(this);
        add(gp);
        add(mainMenu);
        add(savesMenu);
        add(scoresMenu);
        add(pausedScreen);
        add(gameOverScreen);
        setResizable(false);
        setBounds ((width/8)*3-puyo_len*3/2,(height/6)*1-puyo_len,(width/8)*2+puyo_len*3+6,(height/6)*4+25+puyo_len);
        mainMenu.setFocusable(true);
        gp.setVisible(false);
        savesMenu.setVisible(false);
        scoresMenu.setVisible(false);
        pausedScreen.setVisible(false);
        gameOverScreen.setVisible(false);
        mainMenu.setVisible(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(484, 714);

        //loop();

    }


    public void update(){

    }


    public static void main(String args[]) {
        System.out.println("INICIANDO PUYO PUYO......");
        new PuyoPuyo();
    }

    public void refreshSaves(){
        saves = saveUtil.getSaves();
    }

  
}