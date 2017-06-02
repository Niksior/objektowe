package snake;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
/**
 *
 * @author niksior
 */

public class SnakeMenu {
	
	public Scene menu() {
		 
        HBox hbBtn = new HBox(10);
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
        
        hbBtn.getChildren().add(btnScrBrd);
        grid.add(hbBtn, 0, 0);
        hbBtn.getChildren().add(btnNewGame);
        grid.add(hbBtn, 0, 1);
        hbBtn.getChildren().add(btnExit);
        grid.add(hbBtn, 0, 2);
        
        
        
        Scene scene = new Scene(grid, 400, 275);
        return scene;
    }
       
}
    
