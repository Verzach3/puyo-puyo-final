package proyecto.AddedScreens;

import proyecto.PuyoPuyo;
import proyecto.Utils.ScoreBoardUtil;
import proyecto.Utils.ScoreRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * SavesMenu
 */
public class ScoresMenu extends JPanel {
    boolean stopThread = false;

    public void update() {

        ArrayList<ScoreRecord> scoresArray = scoreBoardUtil.getScores();
        String[][] parsedScores = new String[scoresArray.size()][2];
        String[] columNames = new String[]{"Nombre", "Puntaje"};

        for (int i = 0; i < parsedScores.length; i++) {
            parsedScores[i][0] = scoresArray.get(i).getName();
            parsedScores[i][1] = String.valueOf(scoresArray.get(i).getScore());

        }


        scoresTable.setModel(new DefaultTableModel(parsedScores, columNames));
        scoresTable.setEnabled(false);
        System.out.println("Updated");
    }


    PuyoPuyo puyoInstance;
    ScoreBoardUtil scoreBoardUtil = new ScoreBoardUtil();
    JTable scoresTable = new JTable();
    JList scoresList = new JList<>();

    public ScoresMenu(PuyoPuyo puyoInstance) {
        this.puyoInstance = puyoInstance;
        setSize(468, 675);
        setLayout(null);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setSize(358, 469);
        scrollPane.setLocation(55, 43);
        scrollPane.setViewportView(scoresTable);


        //ExitButton
        JButton exitButton = new JButton();
        exitButton.setText("Salir");
        exitButton.setLocation(55, 562);
        exitButton.setSize(368, 53);
        exitButton.addActionListener(e -> {
            setVisible(false);
            puyoInstance.mainMenu.setVisible(true);
            puyoInstance.mainMenu.setFocusable(true);
            puyoInstance.mainMenu.requestFocus();
        });
        add(exitButton);
        add(scrollPane);


    }


}