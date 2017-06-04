package snake;



import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
        stage.setTitle("Snake!");

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
        	stage.hide();
        	clearTheList();
        	showScores();
        });
        
        btnNewGame.setOnAction((ActionEvent e) -> {
        	stage.hide();
        	welcomeInGame();
        });
        
        btnExit.setOnAction((ActionEvent e) -> {
        	stage.close();
        });
        
        stage.setScene(new Scene(grid, 300, 200));
        stage.show();
	}
	
	private void showScores() {
		 AnchorPane anchorPane = new AnchorPane();
	     ListView<String> list = new ListView<String>();
	     ObservableList<String> personData = FXCollections.observableArrayList();
	     for(int i = 0; i < scores.size(); i++) {
	    	 personData.add(scores.get(i).getName() + ": " + scores.get(i).getScore());
	     }
	     list.setItems(personData);
	     
	     AnchorPane.setTopAnchor(list, 10.0);
	     AnchorPane.setLeftAnchor(list, 10.0);
	     AnchorPane.setRightAnchor(list, 65.0);
	     Button btnGoBack = new Button("Back");
	     AnchorPane.setTopAnchor(btnGoBack, 10.0);
	     AnchorPane.setRightAnchor(btnGoBack, 10.0);
	     anchorPane.getChildren().addAll(list, btnGoBack);
	     stage.setScene(new Scene(anchorPane));
	     stage.show();
	     btnGoBack.setOnAction((ActionEvent e) -> {
	        	stage.hide();
	        	switchMenu();
	        });
	}


	private void welcomeInGame() {
		 Button btn = new Button();
	        btn.setText("Sign in");   
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
	        
	        grid.add(btn, 2, 2);
	          
	        btn.setOnAction((ActionEvent e) -> {
	            String tmp = userTextField.getCharacters().toString();
	            scoreBoard.newPlayer(tmp);
	        });
	        stage.setScene(new Scene(grid));
	        stage.show();
	}
	
	private void clearTheList() {
		for(int i = scores.size() - 1; 0 <= i; i--) {
			if(scores.get(i).getScore() == -1) {
				scores.remove(i);
			}
		}
	}
	
	public static void main(String[] args) {
        launch(args);
    }

}