package snake;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;

public final class ScoreBoard {
   
    private List<Player> scores = new ArrayList<Player>();
    private final String fileName;
    
    ScoreBoard() {
        this.fileName = "scores.txt";
    }
    
    public void saveToFile(List<Player> scores) {
        try {
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
    
    public List<Player> loadFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            while(fileIn.available() > 0) {
                 scores.add((Player) in.readObject());
            }
            in.close();
            fileIn.close();
            return scores;
       }catch(IOException i) {
         System.out.println("There was no file to load");
       }catch(ClassNotFoundException c) {
         System.out.println("Player class not found");
      }
		return scores;
    }
    
    public void newPlayer(String name) {
        scores.add(new Player(name));
        saveToFile(scores);
    }
    
    public void setScore(int score) {
    	loadFromFile();
        scores.get(scores.size()-1).setScore(score);
        clearTheList();
        saveToFile(scores);
    }
    
    private void clearTheList() {
		for(int i = scores.size() - 1; 0 <= i; i--) {
			if(scores.get(i).getScore() == -1) {
				scores.remove(i);
			}
		}
	}
    
}
