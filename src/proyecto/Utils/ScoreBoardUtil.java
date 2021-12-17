package proyecto.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ScoreBoardUtil
 */
public class ScoreBoardUtil {

    File scores = new File("Scores//Scores.txt");

    public ScoreBoardUtil(){

    }

    public ArrayList<ScoreRecord> getScores(){
        ArrayList<ScoreRecord> records = new ArrayList<>();
        try (Scanner reader = new Scanner(scores)) {
            while (reader.hasNextLine()) {
                records.add(new ScoreRecord(reader.nextLine(), Integer.parseInt(reader.nextLine())));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return records;
    }

    public void createScore(ScoreRecord record){
        ArrayList<ScoreRecord> currentRecords = getScores();
        try (FileWriter writer = new FileWriter(scores)) {
            for (ScoreRecord scoreRecord : currentRecords) {
                writer.write(scoreRecord.getName() + "\n");
                writer.write(scoreRecord.getScore() + "\n");
            }
            writer.write(record.getName() + "\n");
            writer.write(record.getScore() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printScores(){
        ArrayList<ScoreRecord> records = getScores();
        for (ScoreRecord scoreRecord : records) {
            System.out.println(scoreRecord.getName() + ":" + scoreRecord.getScore());
        }
    }
}