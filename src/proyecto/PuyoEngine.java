package proyecto;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


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

    public Image loadImage(String route){
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(route))).getImage();
    }


    public void paint(Graphics g){
        super.paint(g);
      // TODO document why this method is empty
    }

    public void update(){


    }
    
    File archivo = new File("Puntajes//scoreboard.txt");

    public String ReadScore() {
        String combinar = "";
        try {
            String lineaActual = "";

            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            while ((lineaActual = entrada.readLine()) != null) {
                StringTokenizer tokens = new StringTokenizer(lineaActual, "");
                while (tokens.hasMoreTokens()) {
                    posicion++;
                    combinar += "---> Posicion N#" + posicion + " " + tokens.nextToken() + " Puntos" + "\n";
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error");
        } catch (IOException ex) {
            Logger.getLogger(GamePane.class.getName()).log(Level.SEVERE, null, ex);
        }
        return combinar;
    }
    
    public void scoreBoard() { //Sistema de puntos almacena en un documento de texto
        //Los puntajes alcanzados
        
        //TODO falta implementar el score de GamePane para que lea los puntajes
        PrintWriter data;
        int score = 0;
        if (archivo.exists()) {
            try {
                FileWriter scoreBoard = new FileWriter(archivo, true);
                data = new PrintWriter(scoreBoard);
                data.print(String.valueOf(score));
                data.close();
                scoreBoard.close();
            } catch (Exception ex) {
                System.err.println("Error, " + ex);
            }

        } else {
            System.err.println("Error,el archivo no existe!");
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
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

