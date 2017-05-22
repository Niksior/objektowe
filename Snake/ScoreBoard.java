package snake;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author niksior
 */
public final class ScoreBoard {
   
    private final Player scores[];
    private int amountOfPlayers;
    private final String fileName;
    
    ScoreBoard() {
        this.fileName = "~/scores.txt";
        this.scores = new Player[500];
        this.amountOfPlayers = 0;
        loadFromFile();
    }
    
    public void saveToFile() {
        try{
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for(int i=0; i < amountOfPlayers; i++) {
		out.writeObject(scores[i]);
            }
            out.close();
            fileOut.close();
        }
	catch(IOException e) {
            System.out.println("There was no file to save");
	}
    }
    
    public void loadFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            if(in.available() > 0) {
                 scores[amountOfPlayers] = (Player) in.readObject();
                 amountOfPlayers++;
            }
            in.close();
            fileIn.close();
       }catch(IOException i) {
         System.out.println("There was no file to load");
         System.out.println("Creating file");
       }catch(ClassNotFoundException c) {
         System.out.println("Employee class not found");
      }
    }
    
    public void newPlayer(String name) {
        scores[amountOfPlayers] = new Player(name, 0);
        amountOfPlayers++;
        saveToFile();
    }
    
    public void setScore(int score) {
        scores[amountOfPlayers--].setScore(score);
        saveToFile();
    }
    
}
