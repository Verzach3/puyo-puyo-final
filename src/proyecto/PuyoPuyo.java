
package proyecto;

import proyecto.GameComponents.BarComponent;
import proyecto.GameComponents.MainMenuComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Verzach3
 */
public class PuyoPuyo extends PuyoEngine
{
    GamePane gp;			
    int width,height;
    int rows,cols;
    int puyo_len;			
    Dimension screenSize;                           
    public PuyoPuyo()
    {
        this.setTitle("Puyo_Puyo");
        cols=6;			// Can be set to any value, game rows*cols depends on this value
        rows=cols*2;
        screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        width=1250;
        height=730;
        puyo_len=(width/8)*2/cols;
        gp=new GamePane(puyo_len,rows,cols);
        add(gp);
        setResizable(true);
        setBounds ((width/8)*3-puyo_len*3/2,(height/6)*1-puyo_len,(width/8)*2+puyo_len*3+6,(height/6)*4+25+puyo_len);
        //gp.setFocusable(false);
        //gp.setVisible(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(484, 714);

        debugKeys();

        loop();

    }



    public void update(){

    }

    public static void main(String args[]) {
        System.out.println("INICIANDO PUYO PUYO......");
        new PuyoPuyo();
    }

    public void debugKeys(){
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                super.windowLostFocus(e);
                System.out.println(gp.getWidth());
            }
        });
    }
  
}