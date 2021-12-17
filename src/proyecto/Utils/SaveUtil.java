package proyecto.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * SaveUtil
 */
public class SaveUtil {

    ArrayList<Save> saves = new ArrayList<>();
    int[][] currentGame;
    int[][] game;
    String saveName;

    File saveFolder = new File("Saves//");

    public void checkDirectoy() {

        // Check for the Saves folder and create it if not exist
        if (!saveFolder.exists()) {
            saveFolder.mkdirs();
        }

    }

    public void saveGame(int[][] game, String saveName) throws IOException {
        this.game = game;
        this.saveName = saveName;
        checkDirectoy();
        File saveFile = new File("Saves//" + System.currentTimeMillis()); // This makes the save file name unique to
                                                                          // evade errors
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

        System.out.println("saveGame called");

    }

    public void readGameFromFile(String saveName, int[][] currentGame) throws FileNotFoundException {

        this.saveName = saveName;
        this.currentGame = currentGame;
        File saveFile = new File("Saves//" + saveName);
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

        System.out.println(currentGame.length + "  " + currentGame[0].length);
    }

    public void readGame(int[][] loadedGame, int[][] currentGame){
        this.currentGame = currentGame;
        System.out.println(currentGame.length + "  " + currentGame[0].length);
        for (int i = 0; i < currentGame.length; i++) {
            for (int j = 0; j < currentGame[0].length; j++) {
                currentGame[i][j] = loadedGame[i][j];
            }
        }
    }

    public ArrayList<Save> getSaves() {
        File saveDir = new File("Saves//");
        File[] savesList = saveDir.listFiles();
        saves.clear();
        for (File file : savesList) {
            String saveName;
            int[][] gameStatus = new int[13][6];
            File saveFile = new File("Saves//" + file.getName());
            try (Scanner reader = new Scanner(saveFile)) {
                saveName = reader.nextLine();
                for (int i = 0; i < gameStatus.length; i++) {
                    for (int j = 0; j < gameStatus[0].length; j++) {
                        gameStatus[i][j] = reader.nextInt();
                    }
                }
                saves.add(new Save(saveName, gameStatus));
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        for (Save save : saves) {
            System.out.println(save.getName());
        }

        return saves;
    }

}