import java.util.Scanner;

public class Main {
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
	
	public static void main(String[] args) {
		boolean run = true;
		System.out.println("Welcome to SpeedDialer!");
		int highScore = 0;
		int currentScore = 0;
		do {
			introduction();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Enter the following number as fast as possible:");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("Ready..?   ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("GO!");
			int theNumber = phoneNumberGen(difficulty);
			System.out.println(theNumber);
			int response;
			double timeStart = System.currentTimeMillis();
			do {
				response = getIntGame();
			} while (response!=theNumber);
			double timeEnd = System.currentTimeMillis();
			currentScore = (int)(10000 - (timeStart - timeEnd) * -1);
			if (currentScore > highScore) {
				highScore = currentScore;
				System.out.println("New high score! " + highScore);
			} else {
				System.out.println("Your score: " + currentScore);
				System.out.println("High score: " + highScore);
			}
		} while (run = true);
	}

}
