package proyecto.OriginalGameComponents;

import java.awt.*;

public class GameComponent {


    //This class does not work with the animations
    //FIXME
    

    public GameComponent(Graphics2D g2, float alpha, float alpha1, int len, int cols, int rows, int level, Image[] img, int a, int b, int pieces, int score, Image fpipe, Image bpipe, int anim, boolean reached, int[][] scr){
        g2.fillRect(0, 0, len * cols, len * rows);//background fill with white color
        g2.setColor(Color.black);
        g2.fillRect(len * cols, 0, len * 3, len * rows);//Score board is filled with black color

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //designing score board with gradient colors as borders
        g2.setPaint(new GradientPaint(cols * len, 0, new Color(50, 50, 50), cols * len + len / 2, 0, new Color(200, 200, 200), false));
        g2.fill(new Rectangle(cols * len, 0, len / 2, rows * len));
        g2.setPaint(new GradientPaint((cols + 2) * len + len / 2, 0, new Color(200, 200, 200), (cols + 3) * len, 0, new Color(50, 50, 50), false));
        g2.fill(new Rectangle((cols + 2) * len + len / 2, 0, len / 2, rows * len));
        g2.setPaint(Color.white);
        //white background for next coming puyos display
        g2.fill3DRect((cols + 3 / 2) * len, len, len, len * 2, true);
        //The next coming puyos showned or drawn here
        g2.drawImage(img[a / 2], (cols + 3 / 2) * len, len, len, len, null);
        g2.drawImage(img[b / 2], (cols + 3 / 2) * len, len * 2, len, len, null);
        //Score board designed with 3 rectangled as backgrounds for
        //Level and Number of pices and Total Score
        g2.fill3DRect((cols) * len + len / 2, (rows + 1) * len / 2 - 5, len * 2, len - 2, true);
        g2.fill3DRect((cols) * len + len / 2, (rows + 1) * len / 2 + len - 5, len * 2, len - 2, true);
        g2.fill3DRect((cols) * len + len / 2, (rows + 1) * len / 2 + 2 * len - 5, len * 2, len, true);
        g2.setPaint(Color.black);
        //Font for the text to be display
        g2.setFont(new Font("Ariel", Font.PLAIN, len / 3));
        g2.drawString("Level: " + level, (cols) * len + len / 2, rows * len / 2 + len);
        g2.drawString("pieces: " + pieces, (cols) * len + len / 2, rows * len / 2 + 2 * len);
        g2.drawString("Score:" + score, (cols) * len + len / 2, rows * len / 2 + 3 * len);
        //back part of pipe is drawn as image
        int p;
        if (cols % 2 == 0) {
            p = cols / 2;
        } else {
            p = cols / 2 + 1;
        }
        g2.drawImage(bpipe, p * len - len, 0, len, len, null);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (scr[i][j] > 0)//For all the puyos exists presently
                {
                    int k = scr[i][j];
                    if (k % 2 == 0)//if puyo value is even then simply display it
                    {
                        k = (int) k / 2;
                        g2.drawImage(img[k - 1], j * len, i * len, len, len, null);
                    } else {		//if puyo value is odd then add animation to it
                        k = (int) k / 2 + 1;
                        g2.drawImage(img[k - 1], j * len, (i - 1) * len + anim, len, len, null);
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
        //front part of pipe is drawn as image
        g2.drawImage(fpipe, p * len - len, 0, len, len, null);
        //so when the puyo are at the postion of generation we get experience of puyo coming from the pipe
    }
}
