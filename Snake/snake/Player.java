package snake;

public class Player implements java.io.Serializable {
    private final String nick;
    private int score;

    Player (String name, int scr) {
        this.nick = name;
        this.score = scr;
    }
    
    Player() {
        this.nick = "Error";
        this.score = 404;
    }
    
    Player(String name) {
        this.nick = name;
        this.score = -1;
    }
    
    public void setScore(int scr) {
        score = scr;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public String getName() {
        return this.nick;
    }
}
