import java.util.Scanner;


//java fx stuff
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;


public class Main extends Application implements EventHandler<ActionEvent> {
	static int difficulty;


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
	
	Stage window;
	Scene difficultySelect, game;
	//GUI Experimentation
	public void start(Stage primaryStage) {
		window = primaryStage;
		
		//button 1
		Label intro = new Label("Welcome to Speed Dialer!\nSelect a difficulty");
		Button button1 = new Button("1");
		button1.setOnAction(e -> {
			difficulty = 1;
			window.setScene(game);
			System.out.println(difficulty);
		});
		
		//button 2
		Button button2 = new Button("2");
		button2.setOnAction(e -> {
			difficulty = 2;
			window.setScene(game);
			System.out.println(difficulty);
		});
		
		//quit button
		Button quitButton = new Button("back");
		quitButton.setOnAction(e -> window.setScene(difficultySelect));
		
		//Layout1 - children in vertical column (CHOOSE DIFFICULTY)
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(intro, button1, button2);
		difficultySelect = new Scene(layout1, 300,400);
		
		//Layout 2
		StackPane layout2 = new StackPane();
		layout2.getChildren().add(quitButton);
		game = new Scene(layout2,600,300);
		
		//Display scene1
		window.setScene(difficultySelect);
		window.setTitle("Speed Dialer");
		window.show();
		
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
