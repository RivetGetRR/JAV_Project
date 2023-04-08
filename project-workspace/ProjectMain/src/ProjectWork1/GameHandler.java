package ProjectWork1;

import java.util.Scanner;

public class GameHandler {
	private Scanner input;	//Scanner declared here so only one needs to be declared and can be reused
	private String name;
	
	//Constructor
	public GameHandler (){
		System.out.println("Program Set");
		this.name = "";
		this.input = new Scanner(System.in);
	}
	
	//method to display main menu for the game
	public void displayStartMenu() {
		boolean moveToNextPart = false;//flag set to check validation
		String choice = "";
		///*
		do {//Used a do while loop as the player enters the below code and validation at least once.
			try {//display menu
				System.out.println("|==WELCOME==|");
				System.out.println("|==To Who Wants To Be A Millionaire==|");
				System.out.println("");//Empty Print Statements used to give structure to the output across the program
				System.out.println("S) Start the Game");
				System.out.println("R) View the Rules");
				System.out.println("E) Exit the Game");
				System.out.println("");
				System.out.print("Enter Choice: ");
				choice = input.next();
				switch(choice.toUpperCase()){//this system can be problematic -> lead to infinite loops and such
					//used toUpper so that player can enter lower case versions of the inputs and let it still work.
					case "S": moveToNextPart = true; gameMenu();break;//begin the game proper
					case "R": viewRules();break;//show the rules -> flag not set to true to allow player to see menu and select again
					case "E": moveToNextPart = true; ;break;
					default: System.out.println("Invalid Response - Try Again:");
				}
			}
			catch(Exception e) {//general exception to catch any possible stray errors with generic error message
				System.out.println("Invalid Response - with Error "+ e.getMessage() +" - Try Again:");
			}
		}while(!moveToNextPart);
		exitGame();//*/since the game is handled above -> that means that if it returns here the game must have ended. 
	}
	
	private void gameMenu() {
		System.out.println("");
		boolean flag2 = false;
		System.out.print("Enter Your Name: ");
		this.name = input.next();	
		System.out.println("Okay " + this.name + ", please select your Difficulty:");
		System.out.println("");
		System.out.println("E) Easy Difficulty");
		System.out.println("H) Hard Difficulty");
		System.out.println("");
		System.out.print("Enter Choice: ");
		String choice = ""; //Used string instead of char as it can accept numbers and multiple characters
		//without error and then can be validated against and discarded if invalid instead of getting an error.
		//This will be done throughout program.

		do {
			try {
			choice = input.next();
				switch(choice.toUpperCase()){//
					case "E": flag2 = true; 
						//-> make Easy Game
						EasyGameRH easyGame = new EasyGameRH();
						easyGame.runRounds();
						break;
					case "H": flag2 = true; 
						//-> make Hard Game
						HardGameRH hardGame = new HardGameRH();
						hardGame.runRounds();
						break;
					default: System.out.println("Invalid Response - Try Again:");
				}
			}
			catch(Exception e) {//General Exception to catch any stray errors.
				System.out.println("Invalid Response - with Error "+ e.getMessage() +" - Try Again:");
			}
		}while(!flag2);
		//*/
	}
	
	private void viewRules() {//Display rules
		System.out.println("DA RULES");
		System.out.println("DA RULES");
		System.out.println("DA RULES");
		System.out.println("DA RULES");
		System.out.println("DA RULES");
		System.out.println("");
		System.out.println("DA RULES");
		System.out.println("DA RULES");
		System.out.println("DA RULES");
		System.out.println("");
		System.out.println("");
		System.out.println("Enter anything and hit enter to return to Main Menu:");
		String returns = input.next();
		//this.displayStartMenu();
	}
	
	public void exitGame() {
		System.out.println("Game Exited");
	}
	
}
