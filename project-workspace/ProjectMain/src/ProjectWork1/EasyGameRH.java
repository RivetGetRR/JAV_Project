package ProjectWork1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class EasyGameRH extends RoundHandler {
	private final int NUMBER_OF_QUESTIONS_PER_ROUND = 3;
	private final int QUESTION_POOL_SIZE = 9;//Unused but left in if needed
	private final double[] moneyValues = {100,500,1000,8000,16000,32000,125000,500000,1000000};
	
	
	public EasyGameRH() {
		super();
	}
	
	@Override
	public void runRounds() {//Change all this later:
		System.out.println("<Game Start>");
		System.out.println("");
		System.out.println("");
		
		double currentEarnings = 0.0;
		int roundNumber = 1;//round Number
		
		double[] moneyValuesTempHold = new double[NUMBER_OF_QUESTIONS_PER_ROUND];//money values
		try {
			ArrayList<String> mainQuestionList = loadQuestionPool();//this method can throw an exception, handled in the catch
			//at the bottom of code
	        Collections.shuffle(mainQuestionList);
	        //---------
	        
	        do {
	        	//Programmatically add the money values to array so that they can be display with question
	        	//used additional array to make process easier
	        	moneyValuesTempHold[0] = this.moneyValues[0 + ((roundNumber - 1) * 5)] ;
	        	moneyValuesTempHold[1] = this.moneyValues[1 + ((roundNumber - 1) * 5)] ;
	        	moneyValuesTempHold[2] = this.moneyValues[2 + ((roundNumber - 1) * 5)] ;
	        	
	        	//start round with details for easy difficulty
		        Round currentRound = new Round(moneyValuesTempHold,this.NUMBER_OF_QUESTIONS_PER_ROUND);
		        mainQuestionList = currentRound.initializeQuestionsForRound(mainQuestionList);
		        
		        String continueChoice = "";

		        currentEarnings = currentRound.doRound(lifelineStatus, roundNumber, true);//set to true as lifelines are allowed by default
		        if(currentEarnings != 0.0) { //if earnings become 0 at any point that means the player lost the game

		        	this.score = currentEarnings; //this.moneyValues[questionCount];
		        	System.out.println("Round " + roundNumber + " Completed");
		        	roundNumber += 1;//progress to next round
		        	
		        	do {
			        	System.out.print("Do you wish to Continue(C) or Walk Away(W) with Current earnings("+ currentEarnings +")?");
				        continueChoice = this.enter.next();
				        if(!continueChoice.toUpperCase().equals("C") &&  !continueChoice.toUpperCase().equals("W")) {
				        	System.out.println("Invalid Input Try Again");
				        }
			        //validation to ensure proper choice is made
		        	}while(!continueChoice.toUpperCase().equals("C") &&  !continueChoice.toUpperCase().equals("W"));
		        	
			        if(continueChoice.toUpperCase().equals("W")) {
			        	System.out.println("Walked Away"); // Breaks out of loop and ends game as a result
			        	break; //continue;
			        }
		        }
		        System.out.println(":::");
	        
	        }while(roundNumber<=this.NUM_OF_ROUNDS && currentEarnings != 0.0);//check whether it is within allowed number of rounds
	        //and whether the player has answered all questions correctly
	        
	        if(currentEarnings == 0.0) {
	        	gameOver();//ends game with message if wrong
	        }else {
	        	System.out.println("Earned: " + this.score);//shows winnings if round done correctly
	        }
		}
		catch(IOException io) {//Exception in case the file cannot be read or found
			System.out.println("There was an Error with the Question Bank Text File:" + io);
		}
		catch(Exception e) {//general exception to catch any stray errors
			System.out.println("There was an Error :" + e);
		}
	}
}
