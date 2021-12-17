package proyecto.AddedScreens;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import proyecto.PuyoPuyo;
import proyecto.Utils.ScoreBoardUtil;
import proyecto.Utils.ScoreRecord;

public class MainMenu extends JPanel implements KeyListener{

    PuyoPuyo puyoInstance; 
    public MainMenu(PuyoPuyo puyoInstance){
        this.puyoInstance = puyoInstance;
        this.addKeyListener(this);
    }


    public void paintComponent(){

    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A){
            puyoInstance.gp.newGame();
            System.out.println("Holaaa");
        }  
        
        if(e.getKeyCode() == KeyEvent.VK_1){
            ScoreBoardUtil scoreBoardUtil = new ScoreBoardUtil();
            scoreBoardUtil.printScores();
        }
        if(e.getKeyCode() == KeyEvent.VK_2){
            ScoreBoardUtil scoreBoardUtil = new ScoreBoardUtil();
            scoreBoardUtil.createScore(new ScoreRecord("Setchkooo", 999));;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
