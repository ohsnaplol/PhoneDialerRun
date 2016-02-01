import java.util.HashMap;
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


public class Game extends Application implements EventHandler<ActionEvent> {
	static int difficulty = 5; //Default difficulty
	static HashMap<Integer, Double> HiScore = new HashMap<Integer, Double>();
	public static Scanner keyboard = new Scanner(System.in);
	
	public static void Menu () throws InterruptedException {
		System.out.println("1. Play\n2. Set Difficulty (" + difficulty + ")\n3. View High Score\n4. Exit");
		int response = keyboard.nextInt();
		switch(response) {
		case 1: play();
			break;
		case 2: setDifficulty();
			break;
		case 3: printScores();
			break;
		case 4: System.exit(0);;
		}
		
	}
	
	public static void printScores() throws InterruptedException {
		System.out.println("Difficulty | High Score");
		for (int i = 1; i < 20; i++) {
			if (HiScore.get(i) != 999999999.0) {
				System.out.printf("%-13d%.0f%n", i, HiScore.get(i));
			}
		}
		Menu();
	}
	
	/*
	 * getDifficulty makes sure the input is between 1 and 19 and isn't a string
	 */
	public static void setDifficulty () throws InterruptedException {
		System.out.print("Enter Difficulty: ");
		int num = 8;
		while(true) {
			try {
				num = keyboard.nextInt();
			} catch (Exception e){
				System.out.println("Not an Integer");
				break;
			}
			if (num < 1 || num > 19) {
				System.out.println("Cannot be less difficult than 1 or more than 19");
			} else {
				break;
			}
		}
		difficulty = num;
		Menu();
	}

	/*
	 * getIntGame makes sure that what the user types during gameplay is only numbers
	 */
	public static long getIntGame() {
		long num = 0;
		boolean goodInput = false;
		do {
			try {
				num = keyboard.nextLong();
			} catch (Exception e){
				System.out.println("Not an Integer");
			}
			goodInput = true;
		} while (!goodInput);
		return num;
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
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	//end GUI Experimentation
	
	public static void play() throws InterruptedException {
		double currentScore = 0;
		 //gets the high score of that difficulty and stores it in a local variable
		double highScore = HiScore.get(difficulty);
		//Shows the score to beat if they haven't played yet
		if (HiScore.get(difficulty)!=999999999.0) {
			System.out.println("High Score is " + HiScore.get(difficulty) + " ms");
			Thread.sleep(1000);
		}
		System.out.println("Enter the following number as fast as possible:");
		Thread.sleep(2000);
		System.out.print("Ready..?  ");
		Thread.sleep(1000);
		System.out.println("GO!");
		//generates the number
		long theNumber = (long)(Math.random() * Math.pow(10, difficulty));
		System.out.println(theNumber);
		long response;
		double timeStart = System.currentTimeMillis();
		//Keep trying until response = theNumber
		do {
			response = getIntGame();
		} while (response!=theNumber);
		double timeEnd = System.currentTimeMillis();
		//Calculates score in ms
		currentScore = (int)(timeEnd - timeStart);
		//if user gets a high score, only show the high score
		if (currentScore < highScore) {
			HiScore.put(difficulty, currentScore);
			System.out.println("New high score! " + currentScore + " ms " + " for difficulty " + difficulty);
		} else {
			System.out.println("Your score: " + currentScore + " ms");
			System.out.println("High score: " + HiScore.get(difficulty) + " ms");
		}
		System.out.println("Retry? (y/n)");
		char yn = keyboard.next().charAt(0);
		if (yn == 'y' || yn == 'Y') {
			play();
		} else Menu();
	}

	public static void main(String[] args) throws InterruptedException {
		//fills the high score list
		for (int i = 1; i <=19;i++) {
			HiScore.put(i, 999999999.0);
		}
		//launch(args); //Comment out to run w/o GUI (broken)??
		System.out.println("Welcome to Speed Dialer!");
		Menu();	
	}
}