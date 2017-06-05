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

    public void restart(Stage s) {
	this.stage = s;
	scores = scoreBoard.loadFromFile();
	stage.setTitle("Snake!");
	switchMenu(); 
    }
    
    private void switchMenu() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Button btnScrBrd = new Button("Scoreboard");
        Button btnNewGame = new Button("New game");
        Button btnExit = new Button("Exit");
        
        grid.add(btnScrBrd, 0, 0);
        grid.add(btnNewGame, 0, 1);
        grid.add(btnExit, 0, 2);
        
        btnScrBrd.setOnAction((ActionEvent e) -> {
            stage.hide();
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
	     list.setItems(loadTheScoresToShow());
	     AnchorPane.setTopAnchor(list, 10.0);
	     AnchorPane.setLeftAnchor(list, 10.0);
	     AnchorPane.setRightAnchor(list, 120.0);
	     Button btnGoBack = new Button("Go back");
	     AnchorPane.setTopAnchor(btnGoBack, 10.0);
	     AnchorPane.setRightAnchor(btnGoBack, 10.0);
	     Button btnDel = new Button("Delete scores");
	     AnchorPane.setBottomAnchor(btnDel, 10.0);
	     AnchorPane.setRightAnchor(btnDel, 10.0);
	     anchorPane.getChildren().addAll(list, btnGoBack, btnDel);
	     stage.setScene(new Scene(anchorPane));
	     stage.show();
	     btnGoBack.setOnAction((ActionEvent e) -> {
	        	stage.hide();
	        	switchMenu();
	        });
	     btnDel.setOnAction((ActionEvent e) -> {
	        	clearTheScores();
	        });
	}


	private ObservableList<String> loadTheScoresToShow() {
		ObservableList<String> personData = FXCollections.observableArrayList();
	     for(int i = 0; i < scores.size(); i++) {
	    	 personData.add(scores.get(i).getName() + ": " + scores.get(i).getScore());
	     }
		return personData;
	}

	private void clearTheScores() {
            stage.hide();
            scores.clear();
            scoreBoard.saveToFile(scores);
            showScores();	
	}

	private void welcomeInGame() {
		Button btn = new Button("Sign in");  
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
	            stage.hide();
	            chooseDifficulty();
	        });
	        stage.setScene(new Scene(grid));
	        stage.show();
	}
	
    private void chooseDifficulty() {
	GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("Choose difficulty and start the game");
        grid.add(scenetitle, 0, 0, 4, 1);
        
        Button btnEasy = new Button("Easy");
        Button btnMedium = new Button("Medium");
        Button btnHard = new Button("Hard");
        
        grid.add(btnEasy, 1, 2);
        grid.add(btnMedium, 2, 2);
        grid.add(btnHard, 3, 2);
        
        btnEasy.setOnAction((ActionEvent e) -> {
        	stage.close();
        	Ssnake ssnake = new Ssnake(0.1);
        });
        
        btnMedium.setOnAction((ActionEvent e) -> {
        	stage.close();
        	Ssnake ssnake = new Ssnake(0.06);
        });
        
        btnHard.setOnAction((ActionEvent e) -> {
        	stage.close();
        	Ssnake ssnake = new Ssnake(0.02);
        });
        
        stage.setScene(new Scene(grid, 300, 200));
        stage.show();
    }
	
    public static void main(String[] args) {
        launch(args);
    }

}