package ProjectWork1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//set to abstract as this class itself would be used, just extended from
public abstract class RoundHandler {
	//all protected to allow for inheritance
	protected final int NUM_OF_ROUNDS = 3;
	protected int roundNumber;
	protected double currentEarnings; //score
	protected LifeLineHandler lifelines;
	protected Scanner enter;
	protected String playerName;
	protected ArrayList<String> mainQuestionPool = new ArrayList<String>();
	
	public RoundHandler(String name) throws IOException, FileNotFoundException{
		this.lifelines = new LifeLineHandler();
		this.playerName = name;
		this.roundNumber = 1;
		this.enter = new Scanner(System.in);
		System.out.println("Initialised");
		//---Dilip------
		File file = new File("src/ProjectWork1/questionBank.txt");//!CHANGE THIS FOR FINAL FILE!		
		try(Scanner input = new Scanner(file)){
			//read the data from the file : read line by line
			while(input.hasNextLine()) {
				String line = input.nextLine();
				this.mainQuestionPool.add(line);
			}
		}//--------------
	}
	//in future the method must accept the main selected bank of questions: 9 or 15 in the form of either array or arraylist
	
	protected void gameOver() {//If the game is lost via Incorrect Answer handle here:
		System.out.println("Incorrect Answer - Earnings Lost");
		System.out.println(this.playerName + " Finishes the game with $0");
	}
	
	protected void handleGameFinalScore() {//If the Game is won
        switch(String.valueOf(this.currentEarnings)) {
        	case "0.0": gameOver(); break;
        	case "1000000.0": System.out.println("Congratualtions!! You, " + this.playerName + ", are A Millionaire! - Earned: $" + this.currentEarnings);
	        	//shows winnings if final round done correctly
	        	break;
        	case "-1": break;
        	default: if(this.currentEarnings > 0.0 && this.currentEarnings < 1000000.0) {
        				System.out.println("Congratualtions. You, " + this.playerName + ", Walked Away with: $" + this.currentEarnings);
		        	}else{
		        		System.out.println("Additional Error Occured in Deciding Final Round: -> Win by default -> Earned: $500000");
		        		break;
		        	}
        }
	}
	
	protected boolean roundCompleted() {
		String continueChoice = "";
        if(this.currentEarnings > 0.0 && this.roundNumber < this.NUM_OF_ROUNDS) { //if earnings become 0 at any point that means the player lost the game

        	System.out.println("Round " + this.roundNumber + " Completed");
        	this.roundNumber += 1;//progress to next round
        	
        	do {//display 
	        	System.out.print("Do you wish to Continue(C) or Walk Away(W) with Current earnings("+ this.currentEarnings +")? ");
		        continueChoice = this.enter.next();
		        if(!continueChoice.toUpperCase().equals("C") &&  !continueChoice.toUpperCase().equals("W")) {
		        	System.out.println("Invalid Input Try Again");
		        }
	        //validation to ensure proper choice is made
        	}while(!continueChoice.toUpperCase().equals("C") &&  !continueChoice.toUpperCase().equals("W"));
        	
	        if(continueChoice.toUpperCase().equals("W")) {
	        	System.out.println("Walked Away"); // Breaks out of loop and ends game as a result
	        	return true; //continue;
	        }
        }else if(this.roundNumber >= this.NUM_OF_ROUNDS) {
        	this.roundNumber += 1;
        }
        System.out.println(":::");
        return false;
	}
	public abstract void runRounds();
}
