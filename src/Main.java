import java.util.Random;
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


public abstract class Main extends Application implements EventHandler<ActionEvent> {
	static int difficulty;

	/*
	 * Get Int() makes sure the input is between 1 and 8 and isn't a string
	 */
	public static int getInt () {
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		int num = 5; //default difficulty
		boolean goodInput = false;
		do {
			//Makes sure the input isn't a String
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

	/*
	 * getIntGame makes sure that what the user types during gameplay is only numbers
	 */
	public static int getIntGame() {
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		int num = 0;
		boolean goodInput = false;
		do {
			try {
				num = keyboard.nextInt();
			} catch (Exception e){
				System.out.println("Not an Integer");
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

	//GUI Experimentation
	Stage window;
	Scene difficultySelect, game;
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
		boolean run = true;
		double highScore = 999999999;
		double currentScore = 0;
		//launch(args); //Comment out to run w/o GUI
		System.out.println("Welcome to SpeedDialer!");
		do {
			introduction();
			Thread.sleep(1000);
			System.out.println("Enter the following number as fast as possible:");
			Thread.sleep(2000);
			System.out.print("Ready..?  ");
			Thread.sleep(1000);
			System.out.println("GO!");
			int theNumber = (int)(Math.random() * Math.pow(10, difficulty));
			System.out.println(theNumber);
			int response;
			double timeStart = System.currentTimeMillis();
			do {
				response = getIntGame();
			} while (response!=theNumber);
			double timeEnd = System.currentTimeMillis();
			//Calculates score in ms
			currentScore = timeEnd - timeStart;
			if (currentScore < highScore) {
				highScore = currentScore;
				System.out.println("New high score! " + highScore + " ms");
			} else {
				System.out.println("Your score: " + currentScore + " ms");
				System.out.println("High score: " + highScore + " ms");
			}
		} while (run);
	}

}
