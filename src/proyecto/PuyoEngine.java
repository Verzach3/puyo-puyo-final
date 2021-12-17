package proyecto;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class PuyoEngine extends JFrame implements KeyListener, MouseListener {

    public static int FPS;

    boolean canMove = false;
    
    int posicion = 0;
    public void init(String name, int width, int height){

        initFrame(name, width, height);
        initCanvas();


        loop();
    }


    public void initCanvas(){
        this.setSize(600, 800);
        this.setLayout(null);


    }

    public void initFrame(String name, int width, int height){
        setTitle(name);
        setSize(width, height);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addKeyListener(this);
        addMouseListener(this);
        setUndecorated(true);

    }




    public void loop(){

        int fps = 0;

        long timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();

        final double ns = 1000000000.0 / 60;
        double delta = 0;

        //noinspection InfiniteLoopStatement
        while(true){

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1) {
                if (fps <= 60) {
                    fps++;

                    update();
                    canMove = true;
                    repaint();

                    delta--;

                }
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;

                FPS = fps;
                canMove = false;
                fps = 0;

            }

        }

    }



    public void update(){

    }
    


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

