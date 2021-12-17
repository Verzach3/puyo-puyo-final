package proyecto.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * SaveUtil
 */
public class SaveUtil {

    File saveFolder = new File("Saves//");
    public void checkDirectoy(){
        
        //Check for the Saves folder and create it if not exist
        if(!saveFolder.exists()){
            saveFolder.mkdirs();
        }
        
    }
    

    Thread saveThread = new Thread() {
        public void run(){
            checkDirectoy();
            File saveFile = new File("Saves//" + System.currentTimeMillis()); //This makes the save file name unique to evade errors
            try (FileWriter writer = new FileWriter(saveFile)) {
                writer.write(saveName + "\n");
                for (int i = 0; i < game.length; i++) {
                    for (int j = 0; j < game[0].length; j++) {
                        writer.write(String.valueOf(game[i][j]) + "\n");
                    }
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    int[][] game;
    String saveName;
    public void saveGame(int[][] game, String saveName) throws IOException{
        this.game = game;
        this.saveName = saveName;
        if (!saveThread.isAlive()) {
            saveThread.start();
        }
        

        System.out.println("saveGame called");
        
    }

    Thread readThread = new Thread(){
        public void run(){
            File saveFile = new File("Saves//"+ saveName);
            try (Scanner reader = new Scanner(saveFile)) {
                reader.nextLine();
                for (int i = 0; i < currentGame.length; i++) {
                    for (int j = 0; j < currentGame[0].length; j++) {
                        currentGame[i][j] = reader.nextInt();
                    }
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    int[][] currentGame;
    public void readGame(String saveName, int[][] currentGame) throws FileNotFoundException{
        this.saveName = saveName;
        this.currentGame = currentGame;
        if (!readThread.isAlive()) {
            readThread.start();
        }
    }
}