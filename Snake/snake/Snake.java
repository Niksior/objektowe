package snake;



import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author niksior
 */
public class Snake extends Application {
    
	private List<Player> scores = new ArrayList<Player>();
	private Stage stage;
	ScoreBoard scoreBoard = new ScoreBoard();
	
    @Override
    public void start(Stage primaryStage) {
    	this.stage = primaryStage;
    	scores = scoreBoard.loadFromFile();
    	
    	switchMenu();
    	
    	GridPane grid = new GridPane();
        
        stage.setTitle("Snake!");
        stage.setScene(new Scene(grid));
        stage.show();
    }

	
	private void switchMenu() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Button btnScrBrd = new Button();
        btnScrBrd.setText("Scoreboard");
        Button btnNewGame = new Button();
        btnNewGame.setText("New game");
        Button btnExit = new Button();
        btnExit.setText("Exit");
        
        grid.add(btnScrBrd, 0, 0);
        grid.add(btnNewGame, 0, 1);
        grid.add(btnExit, 0, 2);
        
        btnScrBrd.setOnAction((ActionEvent e) -> {
        	showScores();
        });
        
        btnNewGame.setOnAction((ActionEvent e) -> {
        	welcomeInGame();
        });
        
        btnExit.setOnAction((ActionEvent e) -> {
        	stage.close();
        });
        
        stage.setScene(new Scene(grid, 400, 275));
        
	}
	
	private void showScores() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(2);
        grid.setVgap(4);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        int height = scores.size()*25+50;
		int lenght = 500;
		
		for(int i = 0; i < scores.size(); i++) {
			
		}
		
		stage.setScene(new Scene(grid, height, lenght));
	}


	private void welcomeInGame() {
		 Button btn = new Button();
	        btn.setText("Sign in");   
	        
	        HBox hbBtn = new HBox(10);
	        
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));
	        
	        Text scenetitle = new Text("Welcome");
	        grid.add(scenetitle, 0, 0, 2, 1);
	        
	        Label userName = new Label("User Name:");
	        grid.add(userName, 0, 1);

	        TextField userTextField = new TextField();
	        grid.add(userTextField, 1, 1);
	        
	        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	        hbBtn.getChildren().add(btn);
	        grid.add(hbBtn, 1, 4);
	        
	        btn.setOnAction((ActionEvent e) -> {
	            String tmp = userTextField.getCharacters().toString();
	            scoreBoard.newPlayer(tmp);
	            
	        });
	}
	
	
	public static void main(String[] args) {
        launch(args);
    }

}