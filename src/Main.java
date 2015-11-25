import java.util.Scanner;

//java fx stuff
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application implements EventHandler<ActionEvent> {
	static int difficulty;
	Button button;
	Button button2;

	public static int getInt () {
		Scanner keyboard = new Scanner(System.in);
		int num = 5;
		boolean goodInput = false;
		do {
			try {
				num = keyboard.nextInt();
			} catch (Exception e){
				System.out.println("Not an Integer");
				break;
			}
			//It has to be between 1 and 8
			if (num < 1 || num > 8) {
				System.out.println("Not between 1 and 8");
			} else {
				goodInput = true;
			}
		} while (!goodInput);
		return num;
	}

	public static int getIntGame() {
		Scanner keyboard = new Scanner(System.in);
		int num = 0;
		boolean goodInput = false;
		do {
			try {
				num = keyboard.nextInt();
			} catch (Exception e){
				System.out.println("Not an Integer");
				break;
			}
			goodInput = true;
		} while (!goodInput);
		return num;
	}

	public static void introduction () {
		System.out.println("Enter a difficulty level between 1 and 8:");
		difficulty = getInt();
		System.out.println("Difficulty set to " + difficulty);
	}

	public static int phoneNumberGen(int difficulty) {
		int multiple = 0;
		switch (difficulty) {
		case 1: multiple = 10;
		break;
		case 2: multiple = 1000;
		break;
		case 3: multiple = 10000;
		break;
		case 4: multiple = 100000;
		break;
		case 5: multiple = 1000000;
		break;
		case 6: multiple = 10000000;
		break;
		case 7: multiple = 100000000;
		break;
		case 8: multiple = 1000000000;
		break;
		}
		int number = (int)(Math.random() * multiple);
		return number;
	}

	//GUI Experimentation
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Speed Dialer");
		button = new Button("Click me");
		button2 = new Button("Run");

		button.setOnAction(this);

		StackPane layout = new StackPane();
		layout.getChildren().add(button);

		Scene scene = new Scene(layout, 300, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void handle(ActionEvent event) {
		if (event.getSource()==button){
			System.out.println("Work");
		}
	}
	//end GUI Experimentation

	public static void main(String[] args) throws InterruptedException {
		launch(args); //Comment out to run w/o GUI
		boolean run = true;
		System.out.println("Welcome to SpeedDialer!");
		int highScore = 0;
		int currentScore = 0;
		do {
			introduction();
			Thread.sleep(1000);
			System.out.println("Enter the following number as fast as possible:");
			Thread.sleep(2000);
			System.out.print("Ready..?   ");
			Thread.sleep(1000);
			System.out.println("GO!");
			int theNumber = phoneNumberGen(difficulty);
			System.out.println(theNumber);
			int response;
			double timeStart = System.currentTimeMillis();
			do {
				response = getIntGame();
			} while (response!=theNumber);
			double timeEnd = System.currentTimeMillis();
			//Calculates score
			currentScore = (int)(10000 - (timeStart - timeEnd) * -1);
			if (currentScore > highScore) {
				highScore = currentScore;
				System.out.println("New high score! " + highScore);
			} else {
				System.out.println("Your score: " + currentScore);
				System.out.println("High score: " + highScore);
			}
		} while (run);
	}

}
