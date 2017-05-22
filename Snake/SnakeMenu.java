package snake;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author niksior
 */

//napisać siatkę
//kompiluj javac
//zrób jar -cvfm myJar.jar myManifest.txt *.class
//java -jar myJar.jar
public class SnakeMenu extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
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
        primaryStage.setTitle("Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
