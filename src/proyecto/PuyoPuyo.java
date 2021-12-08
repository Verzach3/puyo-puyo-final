
package proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PuyoPuyo extends JFrame
{
    GamePane gp;			
    int width,height;
    int rows,cols;
    int puyo_len;			
    Dimension screenSize;                           
    public PuyoPuyo()
    {
        super("Puyo Puyo");
        cols=6;			
        rows=cols*2;
        screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        width=1100;
        height=918;
        puyo_len=(width/8)*2/cols;			
        gp=new GamePane(puyo_len,rows,cols);
        Container c=getContentPane();
        c.add(gp);
        setResizable(true);
        setBounds ((width/8)*3-puyo_len*3/2,(height/6)*1-puyo_len,(width/8)*2+puyo_len*3+6,(height/6)*4+25+puyo_len);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String args[]) {
        System.out.println("INICIANDO PUYO PUYO......");
        JFrame.setDefaultLookAndFeelDecorated(false);
        new PuyoPuyo();
    }
  
}