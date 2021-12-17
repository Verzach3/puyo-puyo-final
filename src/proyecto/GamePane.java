package proyecto;

import proyecto.GameComponents.*;
import proyecto.Utils.ImageLoader;
import proyecto.Utils.SaveUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class GamePane extends JPanel implements ActionListener {
    
    ImageLoader imageLoader = new ImageLoader();
    public static int[][] scr; // scr or(screen) array holds the information about puyos to display
    static int rows, cols;
    int currentMenu = 0;
    Node tetris; // Formation of Tetris is checked using this object
    public Timer timer;
    Timer timer1;
    Timer timer2;
    Timer anim_timer; // different timers used for animation of puyos
    Image img[] = new Image[4]; // holds 4 puyo images
    Image fpipe, bpipe; // holds the front and back part of pipe image
    Image background = imageLoader.loadImage("/proyecto/Resources/Background.png");
    Image nextPuyosImage = imageLoader.loadImage("/proyecto/Resources/Next.png");
    Image imageBackground = imageLoader.loadImage("/proyecto/Resources/ImageBackground.png");
    Image scoreImage = imageLoader.loadImage("/proyecto/Resources/Score.png");
    // 2 images of pipe used to get the experience of puyos coming out from pipe
    Toolkit tk; // used to load the images
    Random rand; // this object used to generate puyos randomly in color.
    int rot; // used for the rotation of the puyos
    int len; // length of the puyo ie. width and height
    boolean reached; // generated puyo reached the bottom or in movement
    int count; // count of puyos when formed tetris to delete
    public boolean started; // Game is started or not
    boolean gameOver;
    public boolean paused;
    int a, b; // The two puyos generate to be next are stored in a and b
    int level, score, pieces, removed_puyos;// pieces is number of joint puyos(single piece) generated
    // number of removed puyos by forming tetris
    int minscore;
    int anim; // to build the pixel by pixel animation (movement of generated puyos)
    float alpha, alpha1;
    boolean levelflag;
    int speedCounter = 0;
    public int points = 0;
    int posicion = 0;
    Sound musicaNivel;
    PuyoPuyo puyoInstance;
    int customDelay = 1000;

    public void newGame(){
        setVisible(true);
        setFocusable(true);
        setSize(468, 675);
        puyoInstance.mainMenu.setVisible(false);
        puyoInstance.mainMenu.setFocusable(false);
        init();
        generatePuyos();
        setDelays();
        timer.start();
        started = true;
    }

    public GamePane(int l, int r, int c, PuyoPuyo puyoInstance) {
        this.puyoInstance = puyoInstance;
        len = l; // length of puyos is set by the Puyo_Puyo class where it is calculated and sent
                 // here
        rows = r + 1; // number of rows is taken 12+1 for the easyness of generating puyos from pipe.
        // extra one row is occupied by pipe at the top. Only 12 rows are used for
        // puyos.
        cols = c; // 6 columns
        init(); // initializing game data
        // puyo images are selected depending on the length of puyos
        // length of puyo is calculated depending on the resolution of screen
        // In this game i used two types of puyos for
        // (i)800*600 and below resolutions
        // (ii)1024*768 and above resolutions
        // So images are loaded ofter length of puyos is calculated
        loadImages();
        generatePuyos(); // to start the generating puyos
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_S) {

                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT && !reached && !paused)// move puyos left if each puyo not
                                                                                     // reached to ground
                {
                    moveLeft();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !reached && !paused) {

                    moveRight();
                } else if (e.getKeyCode() == KeyEvent.VK_UP && !paused) {

                    if (!reached) {
                        rotate();
                    }
                    if (!started && level < 19) // Before strting the game
                    {
                        level++;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && !paused) {

                    moveDown();
                    if (!started && level > 0) // Before strting the game
                    {
                        level--;
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    speed(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    speed(false);
                }

                if (e.getKeyCode() == KeyEvent.VK_B) {
                    removePuyosRandomly();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    timer.stop();
                    paused = true; // game is paused
                    setVisible(false);
                    puyoInstance.pausedScreen.setVisible(true);
                    puyoInstance.pausedScreen.requestFocus();
                }
            }
        });
        setFocusable(true); // to set the keyboard focus on this game pane
    }

    public GamePane() {

    }

    public void init()// all variables declared above are initialized here
    {
        scr = new int[rows][cols];
        rot = 1;
        reached = true;
        count = 0;
        started = false;
        gameOver = false;
        paused = false;
        a = 0;
        b = 0;
        level = 0;
        score = 0;
        pieces = -1;
        removed_puyos = 0;
        minscore = 50;
        anim = 0;
        alpha = 0.0f;
        alpha1 = 0.0f;
        levelflag = true;
        tk = Toolkit.getDefaultToolkit();
        rand = new Random();
        customDelay = 1000;
        timer = new Timer(customDelay, this); // generates action event for each 1075 milli seconds when timer is started
        timer.setInitialDelay(0); // generates first event ofter 0 ms when timer starts
        timer1 = new Timer(1000, this);
        timer2 = new Timer(500, this);
        anim_timer = new Timer(50, this);
        anim_timer.start(); // starting the timer
    }

    public void loadImages()// loading images into the image array and pipe objects
    {
        String s = "";
        if (len >= 42) {
            s = "_";
        }
        /*
         * for (int i = 0; i < img.length; i++) {
         * img[i] = tk.getImage("images\\puyo_" + s + (i + 1) + ".png");
         * }
         */
        img[1] = imageLoader.loadImage("/proyecto/Resources/Red_Puyo.png");
        img[0] = imageLoader.loadImage("/proyecto/Resources/Blue_Puyo.gif");
        img[2] = imageLoader.loadImage("/proyecto/Resources/Yellow_Puyo.gif");
        img[3] = imageLoader.loadImage("/proyecto/Resources/Green_Puyo.gif");

        fpipe = tk.getImage("images\\pipe" + s + "1.png");
        bpipe = tk.getImage("images\\pipe" + s + ".png");
    }

    public void speed(Boolean v) {

        if (v) {
            customDelay += 100;
        } else {

            customDelay -= 100;
        }
        timer.setDelay(customDelay);
        timer.restart();

    }

    // Este metodo se encarga de eliminar los puyos aleatoriamente de una columna
    // completamente
    public void removePuyosRandomly() {
        int random = (int) (Math.random() * 5); // Genera un numero de 0 a 5
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                count = 3;

                tetris = new Node(i, j); // 1 borra 4 puyos en X, 2 borra 3 puyos en X, 3 borra 2 puyos en X,4 borra 1
                                         // puyo en X

                if (j > 4) {
                    removePuyos(random); // Llama el metodo RemovePuyos el cual se encarga de eliminar los puyos por
                                         // medio de un numero recibido de 0 a 5, cada numero indica una columna.
                }

            }
        }
    }

    // Este metodo recibe un numero random el cual es la columna de la cual va se
    // eliminada con los puyos
    public void removePuyos(int random) {
        Node n = tetris;
        while (n != null) {
            scr[n.getX()][random] = 0; // Recibe un numero random de 0 a 5, para eliminar esa columna seleccionada
            n = n.getPrev();
        }
    }

    /*
     * public void reproducirPorNivel() {
     * 
     * if (level == 0) {
     * musicaNivel = new Sound("nivel0.wav");
     * } else if (level == 1) {
     * musicaNivel = new Sound("Music//nivel1.wav");
     * } else if (level == 2) {
     * musicaNivel = new Sound("Music//nivel2.wav");
     * } else if (level == 3) {
     * musicaNivel = new Sound("Music//nivel3.wav");
     * } else if (level == 4) {
     * musicaNivel = new Sound("Music//nivel4.wav");
     * } else if (level == 5) {
     * musicaNivel = new Sound("Music//nivel5.wav");
     * } else if (level == 6) {
     * musicaNivel = new Sound("Music//nivel6.wav");
     * } else if (level == 7) {
     * musicaNivel = new Sound("Music//nivel7.wav");
     * } else if (level == 8) {
     * musicaNivel = new Sound("Music//nivel8.wav");
     * } else if (level == 9) {
     * musicaNivel = new Sound("Music//nivel9.wav");
     * } else if (level == 10) {
     * musicaNivel = new Sound("Music//nivel10.wav");
     * } else if (level == 11) {
     * musicaNivel = new Sound("Music//nivel11.wav");
     * } else if (level == 14) {
     * musicaNivel = new Sound("Music//nivel14.wav");
     * } else if (level == 12) {
     * musicaNivel = new Sound("Music//nivel12.wav");
     * }
     * else if(level==19){
     * musicaNivel = new Sound("Music//nivel20.wav");
     * }
     * 
     * }
     */

    public void setDelays() {
        int delay = 0, delay1 = 0;
        for (int i = 0; i <= level; i++)// to set the delays depending on the level
        {
            delay += 20 * (4 - i / 5);
            delay1 += 4 - i / 5;
        }
        if (level == 20) {
            delay += 25;
            delay1 += 1;
        }
        timer.setDelay(customDelay - delay);
        anim_timer.setDelay(52 - delay1);
        anim_timer.restart();
    }

    public void generatePuyos()// To generate Puyos at the top of the game window
    {

        // Checking Top of the game window is occupied by any puyos
        // if occupied then game is over
        int p;
        if (cols % 2 == 0) {
            p = cols / 2 - 1;
        } else {
            p = cols / 2;
        }
        if (scr[0][p] == 0 && scr[1][p] == 0) {

            scr[0][p] = a; // a and b are randomly generated Puyos
            scr[1][p] = b;
        } else {

            timer.stop();
            gameOver = true; // game is over
            return;
        }
        int r; // Generating puyos randomly for the next fall which are viewed at the right
               // side of window
        // Odd numbers 1,3,5,7(for 4 colors) are used for generating puyos which are in
        // movement
        // Even numbers 2,4,6,6 are used for fallen puyos on bottom of the window
        while ((r = rand.nextInt(8)) % 2 == 0)
            ;
        a = r;
        while ((r = rand.nextInt(8)) % 2 == 0)
            ;
        b = r;
        pieces++;
        rot = 1;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) // If Event is generated by the timer object
        {

            movePuyos();
        } else if (e.getSource() == timer1) // If Event is generated by the timer1 object
        {

            erase_puyos();
        } else if (e.getSource() == timer2) {

            fillVacated();
            timer2.stop();
        }
        // timer1 and timer2 are used for make delay b/w erasing puyos and filling
        // vacated places by puyos
        repaint();
    }

    public void movePuyos()// Moving generated puyos Downword
    {
        int flag = 0;
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                if (scr[i][j] % 2 == 1) {
                    if (i == rows - 1) // for each moving puyo ie. on last row 12
                    {
                        scr[i][j] += 1; // When generated puyo reached the ground ie. 12 row
                        reached = true; // then make it as fallen puyo by making it as even number
                        // reached the ground
                    } else if (scr[i + 1][j] == 0) // if the next row is empty
                    {
                        scr[i + 1][j] = scr[i][j]; // for each generated puyo in moment increase the row number
                        scr[i][j] = 0;
                        flag = 1; // to build the movement for that puyo
                    } else {
                        scr[i][j] += 1; // If the next row contain any puyo then stop the movement
                        reached = true; // of puyo by making it even
                    }
                    anim = 0; // for pixel by pixel animation of puyos here animation starts
                }
            }
        }
        if (flag == 0) // if flag is not set mean that there is no puyo is in moment
        {
            erase_puyos(); // so erase puyo which form tetris
        }
    }

    public void erase_puyos() {
        int flag = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (scr[i][j] > 0) // for all color puyos
                {
                    count = 1;
                    tetris = new Node(i, j);// create a node for puyo
                    chkForTetris(i, j); // check that node attached to the tetris puyos of same color
                    if (count >= 4) // if tetris forms
                    {
                        removeAllTetris();// remove the puyos which form tetris
                        flag = 1;
                        ///////////////////////////////////////
                        // I made change here to remove the bug in this revised game (by moving 4 lines
                        /////////////////////////////////////// of code to the below if block)
                        // ie. erasing all the chain combos formed at a time will be removed at a time.
                        ///////////////////////////////////////
                    }
                }
            }
        }
        if (flag == 1) {
            timer.stop(); // stop the generating puyos
            timer1.start(); // erase puyos if there is any other form tetris with delay
            timer2.start(); // fill vacated places of erased puyos with remain by the law of gravity with
                            // delay
            return;
        }
        timer1.stop(); // If there is no puyos form tetris then stop timer for erasing puyos
        minscore = 50; // min score for each puyo is initiated
        generatePuyos(); // start generating puyos
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    public void chkForTetris(int x, int y) {
        if (y < cols - 1 && scr[x][y] == scr[x][y + 1] && existsInTetris(x, y + 1)) { // check for the same color puyo
                                                                                      // at the right side of
            count++; // current puyo which is not already added to the current tetris
            addToTetris(x, y + 1); // If there is then add that to the current tetris
            chkForTetris(x, y + 1); // Then check this node can connected to any same color puyo
        }
        if (x < rows - 1 && scr[x][y] == scr[x + 1][y] && existsInTetris(x + 1, y)) {
            count++;
            addToTetris(x + 1, y); // check at the down side
            chkForTetris(x + 1, y);
        }
        if (y > 0 && scr[x][y] == scr[x][y - 1] && existsInTetris(x, y - 1)) {
            count++;
            addToTetris(x, y - 1); // check at the left side
            chkForTetris(x, y - 1);
        }
        // I found a bug here and i rectified it by adding below if block
        // Before iam not checked up side
        // but if below type of chain combo formed by puyos
        // * *
        // ***
        // then up right puyo cannot be added to the tetris to remove
        if (x > 0 && scr[x][y] == scr[x - 1][y] && existsInTetris(x - 1, y)) {
            count++;
            addToTetris(x - 1, y); // check at the up side
            chkForTetris(x - 1, y);
        }
    }

    public void addToTetris(int x, int y)// adding another node to the present tetris
    {
        tetris.setNext(new Node(x, y));
        tetris.getNext().setPrev(tetris);// It is totally the linked list concept used here
        tetris = tetris.getNext();
    }

    public boolean existsInTetris(int x, int y)// comparing with the all the nodes in present tetris
    { // that it is already exists or not
        Node n = tetris;
        while (n != null) {
            if (n.getX() == x && n.getY() == y) {
                return false;
            }
            n = n.getPrev();
        }
        return true;
    }

    public void removeAllTetris() {
        Node n = tetris;
        while (n != null) {
            scr[n.getX()][n.getY()] = 0; // removing puyos which are in tetris
            n = n.getPrev();
        }
        removed_puyos += count;
        if (removed_puyos >= 50) // Change the level of the game depending on
        { // number of removed puyos.
            if (levelflag) {
                level += 1;
            } else {
                level -= 1; // Ofter playing the final level(here 20) the level decreased by 1
            }
            if (level == 20) // It is decreased up to 15 and then it increases 20
            {
                levelflag = false; // So The Game is endlessly continued if player can play all levels perfectly
                                   // without any minstake at any level.
            }
            if (level == 15 && !levelflag) {
                levelflag = true;
            }
            setDelays();
            removed_puyos = 0;
        }
        score += minscore * (count - 3) * count;// Score is calculated by using this formula
        minscore = minscore * count; // minscore in formula depends on the number of puyos formed in last tetris
        // Scoring is totally depends on the length of the chain and
        // number of chains formed by puyos at a single time
    }

    public void fillVacated() // vacated places formed by removed puyos are filled with the other puyos by the
                              // law of gravity
    {
        for (int i = rows - 2; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                if (scr[i][j] > 0) // for all puyos
                {
                    int k;
                    for (k = i + 1; k <= rows - 1; k++) {
                        if (scr[k][j] > 0) // any puyo exist below the current puyo up to the ground
                        {
                            scr[k - 1][j] = scr[i][j]; // then move on to it
                            if (i != k - 1) {
                                scr[i][j] = 0;
                            }
                            break;
                        } else if (k == rows - 1) // if no puyo exist below the current puyo up to the ground
                        {
                            scr[rows - 1][j] = scr[i][j]; // then move on to the ground
                            if (i != rows - 1) {
                                scr[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    public void moveLeft() // to move the coming down puyos one step left
    {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (scr[i][j] > 0 && scr[i][j] % 2 == 1 && j > 0)// for all the coming down puyos which are not in first
                                                                 // column(or left column)
                {

                    if (j < cols - 1 && scr[i][j + 1] % 2 == 1 && scr[i][j - 1] == 0) // if two puyo are in horizontal
                    { // if there is no puyo in left
                        scr[i][j - 1] = scr[i][j]; // move two puyo to one step left
                        scr[i][j] = scr[i][j + 1];
                        scr[i][j + 1] = 0;
                    } else if (scr[i][j - 1] == 0 && scr[i + 1][j - 1] == 0)// if two puyo are in vertical & there is no
                                                                            // puyos in left
                    {
                        scr[i][j - 1] = scr[i][j];
                        scr[i + 1][j - 1] = scr[i + 1][j]; // move two puyo to one step left
                        scr[i][j] = 0;
                        scr[i + 1][j] = 0;
                    }
                    return;
                }
            }
        }
    }

    public void moveRight() // to move the coming down puyos one step right
    {
        for (int i = 0; i < rows; i++) {
            for (int j = cols - 1; j >= 0; j--) {
                if (scr[i][j] > 0 && scr[i][j] % 2 == 1 && j < cols - 1)// for all the coming down puyos which are not
                                                                        // in last column(or right column)
                {
                    if (j > 0 && scr[i][j - 1] % 2 == 1 && scr[i][j + 1] == 0)// if two puyo are in horizontal
                    { // if there is no puyo in right
                        scr[i][j + 1] = scr[i][j];
                        scr[i][j] = scr[i][j - 1]; // move two puyo to one step right
                        scr[i][j - 1] = 0;
                    } else if (scr[i][j + 1] == 0 && scr[i + 1][j + 1] == 0)// if two puyo are in vertical & there is no
                                                                            // puyos in right
                    {
                        scr[i][j + 1] = scr[i][j];
                        scr[i + 1][j + 1] = scr[i + 1][j]; // move two puyo to one step right
                        scr[i][j] = 0;
                        scr[i + 1][j] = 0;
                    }
                    return;
                }
            }
        }
    }

    public void rotate() // to rotate the coming down puyos in clock wise direction by 90 degrees
    {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (scr[i][j] > 0 && scr[i][j] % 2 == 1)// for all the coming down puyos
                {
                    // alway consider the puyo left and top for horizontal and vertical positions as
                    // present
                    // because checking is taking from top to bottom
                    // Two puyos can make only four different positions rotating 90 degrees each
                    // time
                    if (rot == 1) // first postion=vertical
                    { // moving down puyo to the left of first puyo makes 90 degrees rotaion to get
                      // second postion
                        if (j > 0 && scr[i][j - 1] == 0)// is left side is empty or not
                        {
                            scr[i][j - 1] = scr[i + 1][j];
                            scr[i + 1][j] = 0; // If it is empty location move there
                            rot = 2; // change to second position
                        } else if (j < cols - 1 && scr[i][j + 1] == 0)// is left side is not empty, chk for right
                                                                      // position
                        {
                            scr[i][j + 1] = scr[i][j];// move present puyo to the right
                            scr[i][j] = scr[i + 1][j];// move down puyo to the present location
                            scr[i + 1][j] = 0;
                            rot = 2; // change to second position
                        }
                    } // second position=horizontal
                    else if (rot == 2 && i > 1)// moving present puyo to the up of right puyo makes 90 degrees rotaion
                                               // to get third position
                    {
                        scr[i - 1][j + 1] = scr[i][j];// move present puyo to the up of the right puyo
                        scr[i][j] = 0;
                        rot = 3; // change to third position
                    } // third position=vertical (invert to the first position in two puyos positions)
                    else if (rot == 3) // moving present puyo to the up of right puyo makes 90 degrees rotaion to get
                                       // fourth position
                    {
                        if (j < cols - 1 && scr[i + 1][j + 1] == 0)// is right side of the down puyo is empty or not
                        {
                            scr[i + 1][j + 1] = scr[i][j]; // If it is empty location move there
                            scr[i][j] = 0;
                            rot = 4; // change to fourth position
                        } else if (j > 0 && scr[i + 1][j - 1] == 0)// is left side of the down puyo is empty or not
                        {
                            scr[i + 1][j - 1] = scr[i + 1][j]; // move down puyo to the left
                            scr[i + 1][j] = scr[i][j]; // move present puyo to the down
                            scr[i][j] = 0;
                            rot = 4; // change to fourth position
                        }
                    } // fourth position=horizontal (invert to the second postion in two puyos
                      // positions)
                    else if (rot == 4 && i < rows - 1)// moving right puyo to the down of the current puyo makes 90
                                                      // degrees rotaion to get first position
                    {
                        if (scr[i + 1][j] == 0)// is down position is empty or not
                        {
                            scr[i + 1][j] = scr[i][j + 1];// move right puyo to the down of the present puyo
                            scr[i][j + 1] = 0;
                            rot = 1; // change to first position
                        }
                    }
                    return;
                }
            }
        }
    }

    public void moveDown()// Moving generated moving puyos by one step down
    {
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                if (scr[i][j] % 2 == 1)// For all moving puyos
                {
                    if (i == rows - 1) // if puyo is in last row
                    {
                        scr[i][j] = scr[i][j] + 1;// making it as grounded
                        reached = true;
                    } else if (scr[i + 1][j] > 0 && scr[i + 1][j] % 2 == 0)// if next row of puyo contains another puyo
                    {
                        scr[i][j] = scr[i][j] + 1;// then stop the current puyo at the current postion
                        reached = true;
                    } else {
                        scr[i + 1][j] = scr[i][j];// Move present puyo one step down
                        scr[i][j] = 0;
                    }
                }
            }
        }
        repaint();
    }


    BarComponent barComponent = new BarComponent();

    public void paintComponent(Graphics g) {
        g.setColor(Color.white); // Graphics principal, NO MOVER
        Graphics2D g2 = (Graphics2D) g;// Graphics2D principal, NO MOVER

        // Probando Colores por nivel es algo random xd no es que vaya ser asi jsjssj
        // ATT: Kahyberth
        if (level == 2) {

            g.setColor(Color.BLACK);
        }
        if (level == 3) {
            g.setColor(Color.ORANGE);
        }
        if (level == 19) {
            for (int i = 0; i < 100; i++) {
                int rr = (int) (Math.random() * 200 + 1);
                int gg = (int) (Math.random() * 200 + 1);
                int bb = (int) (Math.random() * 200 + 1);
                Color colorRandom = new Color(rr, gg, bb);
                g.setColor(colorRandom);
            }

        }
        // End testing colors

        // This paints the game
        // new GameComponent(g2, alpha, alpha1, len, cols, rows, level, img, a, b,
        // pieces, score, fpipe, bpipe, anim, reached, scr);

        if (started) {
            g2.drawImage(background, 0, 0, null);
            g2.drawImage(imageBackground, 0, 53, null);
            g2.setColor(Color.black);
            //g2.fillRect(len * cols, 0, len * 3, len * rows);// Score board is filled with black color

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // designing score board with gradient colors as borders
            //g2.setPaint(new GradientPaint(cols * len, 0, new Color(50, 50, 50), cols * len + len / 2, 0, new Color(200, 200, 200), false));
            //g2.fill(new Rectangle(cols * len, 0, len / 2, rows * len));
            //g2.setPaint(new GradientPaint((cols + 2) * len + len / 2, 0, new Color(200, 200, 200), (cols + 3) * len, 0, new Color(50, 50, 50), false));
            //g2.fill(new Rectangle((cols + 2) * len + len / 2, 0, len / 2, rows * len));
            g2.setPaint(Color.white);
            // white background for next coming puyos display
            g2.fill3DRect((cols + 3 / 2) * len, len, len, len * 2, true);
            g2.drawImage(nextPuyosImage, 356, 34, this);
            // The next coming puyos showned or drawn here
            g2.drawImage(img[a / 2], (cols + 3 / 2) * len, len, len, len, null);
            g2.drawImage(img[b / 2], (cols + 3 / 2) * len, len * 2, len, len, null);
            // Score board designed with 3 rectangled as backgrounds for
            // Level and Number of pices and Total Score
            g2.drawImage(scoreImage, ((cols) * len + len / 2) + 5, (rows + 1) * len / 2 - 5, null);

            g2.drawImage(scoreImage, ((cols) * len + len / 2) + 5, ((rows + 1) * len / 2 + len - 5) + 5, null);
            
            g2.drawImage(scoreImage, ((cols) * len + len / 2) + 5, ((rows + 1) * len / 2 + 2 * len - 5) + 10, null);
            g2.setPaint(Color.white);
            // Font for the text to be display
            g2.setFont(new Font("Ariel", Font.PLAIN, len / 3));
            g2.drawString("Level: " + level, ((cols) * len + len / 2) + 30, rows * len / 2 + len);
            g2.drawString("Pieces: " + pieces, ((cols) * len + len / 2) + 30, (rows * len / 2 + 2 * len) + 5);
            g2.drawString("Score:" + score, ((cols) * len + len / 2) + 30, (rows * len / 2 + 3 * len) + 10);
            g2.setPaint(Color.black);
            // back part of pipe is drawn as image
            int p;
            if (cols % 2 == 0) {
                p = cols / 2;
            } else {
                p = cols / 2 + 1;
            }
            g2.drawImage(bpipe, p * len - len, 0, len, len, null);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (scr[i][j] > 0)// For all the puyos exists presently
                    {
                        int k = scr[i][j];
                        if (k % 2 == 0)// if puyo value is even then simply display it
                        {
                            k = (int) k / 2;
                            g2.drawImage(img[k - 1], (j * len) + 13, (i * len) - 60, len, len, null);
                        } else { // if puyo value is odd then add animation to it
                            k = (int) k / 2 + 1;
                            g2.drawImage(img[k - 1], (j * len) + 13, ((i - 1) * len + anim) - 60, len, len, null);
                            anim += 2;
                            if (anim >= len) {
                                anim = len;
                            }
                            if (reached && i == 2) {
                                reached = false;
                            }
                        }

                    }
                }
            }
            // front part of pipe is drawn as image
            g2.drawImage(fpipe, p * len - len, 0, len, len, null);
            // so when the puyo are at the postion of generation we get experience of puyo
            // coming from the pipe
        }

        if (!started)// if game is not started display the information and controls
        {
            // Paints the information
            new NotStartedComponent(g2, alpha, alpha1, len, cols, rows, level);
        }
        if (gameOver)// if game is over dim the game by using alpha composite and display the
                     // information
        {
            setVisible(false);
            puyoInstance.gameOverScreen.setVisible(true);

        }
        if (paused)// if game is paused dim the game by using alpha composite and display the
                   // information
        {
            // Paints the paused
            new PausedComponent(g2, alpha, alpha1, len, cols, rows);

        }
    }



}
