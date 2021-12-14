
package proyecto;

import proyecto.PaintComponents.BarComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowStateListener(e -> System.out.println("Width: " + this.getWidth() + " Height: " +this.getHeight()));
        setSize(566, 732);
        loop();

    }

    BarComponent barComponent = new BarComponent();
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;



    }

    public void update(){

    }

    public static void main(String args[]) {
        System.out.println("INICIANDO PUYO PUYO......");
        JFrame.setDefaultLookAndFeelDecorated(false);
        new PuyoPuyo();
    }
  
}