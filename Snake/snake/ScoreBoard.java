package snake;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author niksior
 */
public final class ScoreBoard {
   
    private final List<Player> scores = new ArrayList<Player>();
    private final String fileName;
    
    ScoreBoard() {
        this.fileName = "scores.txt";
        loadFromFile();
    }
    
    public void saveToFile() {
        try{
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for(int i=0; i < scores.size(); i++) {
            	out.writeObject(scores.get(i));
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
            while(fileIn.available() > 0) {
                 scores.add((Player) in.readObject());
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
        scores.add(new Player(name, 0));
        saveToFile();
    }
    
    public void setScore(int score) {
        scores.get(scores.size()-1).setScore(score);
        saveToFile();
    }
    
}
