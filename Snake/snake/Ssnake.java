package snake;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Ssnake  {
	
	private Stage stage;
	public double frameRate;
	
	Ssnake(double d) {
		this.frameRate = d;
		this.stage = new Stage();
		try {
			runner();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public static final int BLOCK_SIZE = 15;
    public static final int WIDTH = 30 * BLOCK_SIZE;
    public static final int HEIGHT = 25 * BLOCK_SIZE;

    private Direction direction = Direction.RIGHT;
    private boolean moved = false;
    private boolean running = false;

    private Timeline timeline = new Timeline();

    private ObservableList<Node> snake;

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);

        Group snakeBody = new Group();
        snake = snakeBody.getChildren();

        Rectangle food = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
        food.setFill(Color.RED);
        food.setTranslateX((int)(Math.random() * (WIDTH-BLOCK_SIZE)) / BLOCK_SIZE * BLOCK_SIZE);
        food.setTranslateY((int)(Math.random() * (HEIGHT-BLOCK_SIZE)) / BLOCK_SIZE * BLOCK_SIZE);
		
		Rectangle background = new Rectangle(WIDTH, HEIGHT);
		background.setFill(createGridPattern());
        
        KeyFrame frame = new KeyFrame(Duration.seconds(frameRate), event -> {
            if (!running)
                return;
            boolean toRemove = snake.size() > 1;
            Node tail = toRemove ? snake.remove(snake.size()-1) : snake.get(0);
            checkDirection(tail);
            moved = true;
            if (toRemove) {
            	snake.add(0, tail);
            }
            checkCollission(tail);
            moveTheSnake(tail, food);
        });

        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        root.getChildren().addAll(background, food, snakeBody);
        return root;
    }

    private void checkDirection(Node tail) {
    	switch (direction) {
        case UP:
            tail.setTranslateX(snake.get(0).getTranslateX());
            tail.setTranslateY(snake.get(0).getTranslateY() - BLOCK_SIZE);
            break;
        case DOWN:
            tail.setTranslateX(snake.get(0).getTranslateX());
            tail.setTranslateY(snake.get(0).getTranslateY() + BLOCK_SIZE);
            break;
        case LEFT:
            tail.setTranslateX(snake.get(0).getTranslateX() - BLOCK_SIZE);
            tail.setTranslateY(snake.get(0).getTranslateY());
            break;
        case RIGHT:
            tail.setTranslateX(snake.get(0).getTranslateX() + BLOCK_SIZE);
            tail.setTranslateY(snake.get(0).getTranslateY());
            break;
        default:
        	break;
    	}	
    }

	private void moveTheSnake(Node tail, Rectangle food) {
    	double tailX = tail.getTranslateX();
        double tailY = tail.getTranslateY();
    	if (tail.getTranslateX() == food.getTranslateX()
                && tail.getTranslateY() == food.getTranslateY()) {
            food.setTranslateX((int)(Math.random() * (WIDTH-BLOCK_SIZE)) / BLOCK_SIZE * BLOCK_SIZE);
            food.setTranslateY((int)(Math.random() * (HEIGHT-BLOCK_SIZE)) / BLOCK_SIZE * BLOCK_SIZE);

            Rectangle rect = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
            rect.setTranslateX(tailX);
            rect.setTranslateY(tailY);
            rect.setFill(Color.CHARTREUSE);
            snake.add(rect);
        }
    }
    
    private void checkCollission(Node tail) {
    	//with snake 
    	for (Node rect : snake) {
             if (rect != tail && tail.getTranslateX() == rect.getTranslateX()
                     && tail.getTranslateY() == rect.getTranslateY()) {
             	stopGame();
                break;
             }
         }
    	 //with border
    	 if (tail.getTranslateX() < 0 || tail.getTranslateX() >= WIDTH
                 || tail.getTranslateY() < 0 || tail.getTranslateY() >= HEIGHT) {
         	stopGame();
         }
    }

    private void stopGame() {
        timeline.stop();
        ScoreBoard scrbrd = new ScoreBoard();
        int winner = (int) ((snake.size()-1)*(1/frameRate))*3;
        scrbrd.setScore(winner);
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Your score: " + winner);
        grid.add(scenetitle, 0, 0, 4, 1);
        
        Button btnBack = new Button("Go Back");
        
        grid.add(btnBack, 1, 1);
        
        stage.setScene(new Scene(grid, 300, 200));
        stage.show();
        
        btnBack.setOnAction((ActionEvent e) -> {
            Snake newGame = new Snake();
            stage.close();
            newGame.restart(stage);
        });
    }
    

    private void startGame() {
    	direction = Direction.RIGHT;
        Rectangle head = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
        head.setFill(Color.CHARTREUSE);
        snake.add(head);
        timeline.play();
        running = true;
    }

    public void runner() throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(event -> {
            if (!moved)
                return;
            arrowKeys(event);
            moved = false;
        });
        stage.setScene(scene);
        stage.show();
        startGame();
    }
    
    private void arrowKeys(KeyEvent event) {
    	switch (event.getCode()) {
        case UP:
            if (direction != Direction.DOWN)
                direction = Direction.UP;
            break;
        case DOWN:
            if (direction != Direction.UP)
                direction = Direction.DOWN;
            break;
        case LEFT:
            if (direction != Direction.RIGHT)
                direction = Direction.LEFT;
            break;
        case RIGHT:
            if (direction != Direction.LEFT)
                direction = Direction.RIGHT;
            break;
        default:
        	break;
    	}
    }

    public ImagePattern createGridPattern() {

        double w = BLOCK_SIZE;
        double h = BLOCK_SIZE;

        Canvas canvas = new Canvas(w, h);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setStroke(Color.DARKCYAN);
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, w, h);
        gc.strokeRect(0.5, 0.5, w, h);

        Image image = canvas.snapshot(new SnapshotParameters(), null);
        ImagePattern pattern = new ImagePattern(image, 0, 0, w, h, false);
        
        return pattern;

    }

  
}
