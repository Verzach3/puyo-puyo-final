package proyecto.Utils;

public class Save {
    String name;
    int[][] gameStatus;

    public Save(String name, int[][] gameStatus){
        this.name = name;
        this.gameStatus = gameStatus;
    }

    public String getName(){
        return name;
    }

    public int[][] getGameStatus(){
        return gameStatus;
    }
}
