package ProjectWork1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GameHandler {
	private Scanner input;	//Scanner declared here so only one needs to be declared and can be reused
	private String name;
	
	//Constructor
	public GameHandler (){
		this.name = "";
		this.input = new Scanner(System.in);
	}
	
	//method to display main menu for the game
	public void displayStartMenu() {
		boolean moveToNextPart = false;//flag set to check validation
		String choice = "";
		try {
			System.out.println("|==WELCOME==|");
			System.out.println("|==To Who Wants To Be A Millionaire==|");
			do {//Used a do while loop as the player enters the below code and validation at least once.
				//display menu				
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
						case "E": moveToNextPart = true;break;//Just exits game without doing anything by setting flag to true and not calling method to perform rest of game
						default: System.out.println("Invalid Response - Try Again:");//all other responses invalid therefore this is default
					}
			}while(!moveToNextPart);
			exitGame();//*/since the game is handled above -> that means that if it returns here the game must have ended. 
			
		}catch(Exception e) {//general exception to catch any possible stray errors with generic error message
			System.out.println("Error occured at Game Handler " + e + " Exiting Game");
		}
	}
	
	private void gameMenu() {
		System.out.println("");
		boolean isInValid = false;
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

		try {
			do {//Similar validation system to above
				choice = input.next();
				switch(choice.toUpperCase()){//
					case "E": isInValid = true; 
								//-> make Easy Game
								EasyGameRH easyGame = new EasyGameRH(this.name);
								easyGame.runRounds();
								break;
					case "H": isInValid = true; 
								//-> make Hard Game
								HardGameRH hardGame = new HardGameRH(this.name);
								hardGame.runRounds();
								break;
					default: System.out.println("Invalid Response - Try Again:");
				}
			}while(!isInValid);		
		}catch(IOException io) {//Exception in case the file cannot be read or found -> FileNotFound Also handled by this Exception
			System.out.println("There was an Error with getting the questions from the Question Bank Text File:" + io);
		}
		catch(Exception e) {//general exception to catch any stray errors
			System.out.println("There was a General Error in Hard Game Handling Phase:" + e + ": Quitting Game");
		}
	}
	
	private void viewRules() {//Display rules
		File gameRules = new File("src/ProjectWork1/GameRules.txt");
		if(!gameRules.exists()) {
			System.out.println("The game rules text file is missing.");
			System.exit(0);
		}
		try(Scanner fileScan = new Scanner(gameRules);) {
			System.out.println("\n\nList of the game rules are as follows");
			while(fileScan.hasNextLine()) {
				String rulesfileData = fileScan.nextLine();
				System.out.println(rulesfileData);
			}
		}
		catch(FileNotFoundException err) {
			System.err.println(err.getMessage());
		}
		
		System.out.println("Enter anything and hit enter to return to Main Menu:");
		String returns = input.next();
		//just enter anything to return -> no need for validation
	}
	
	public void exitGame() {//Print when Game is over
		System.out.println("Game Exited");
	}
	
}
