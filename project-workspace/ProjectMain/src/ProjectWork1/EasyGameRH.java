package ProjectWork1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class EasyGameRH extends RoundHandler {
	private final int NUMBER_OF_QUESTIONS_PER_ROUND = 3;
	private final int QUESTION_POOL_SIZE = 9;//Unused but left in if needed
	private final double[] MONEY_VALUES = {100,500,1000,8000,16000,32000,125000,500000,1000000};
		
	public EasyGameRH(String name) throws FileNotFoundException, IOException {
		super(name);
	}
	
	@Override
	public void runRounds() {//Change all this later:
		System.out.println("<Game Start>");
		System.out.println("");		
		double[] moneyValuesTempHold = new double[this.NUMBER_OF_QUESTIONS_PER_ROUND];//money values	
		try {
			do {	        	
	        	//Programmatically add the money values to array so that they can be display with question
	        	//used additional array to make process easier
	        	moneyValuesTempHold[0] = this.MONEY_VALUES [0 + ((this.roundNumber - 1) * 3)] ;
	        	moneyValuesTempHold[1] = this.MONEY_VALUES [1 + ((this.roundNumber - 1) * 3)] ;
	        	moneyValuesTempHold[2] = this.MONEY_VALUES [2 + ((this.roundNumber - 1) * 3)] ;
	        	
	        	//start round with details for easy difficulty
		        Round currentRound = new Round(moneyValuesTempHold,this.NUMBER_OF_QUESTIONS_PER_ROUND);
		        switch(this.roundNumber) {
		        //for Round 1
				case 1: 
					this.mainQuestionPool = currentRound.addQuestionsToRound(this.mainQuestionPool, 2, "1");
					this.mainQuestionPool = currentRound.addQuestionsToRound(this.mainQuestionPool, 1, "2"); 
					break;
				//for Round 2
				case 2: ; 
					this.mainQuestionPool = currentRound.addQuestionsToRound(this.mainQuestionPool, 1, "1");
					this.mainQuestionPool = currentRound.addQuestionsToRound(this.mainQuestionPool, 1, "2");
					this.mainQuestionPool = currentRound.addQuestionsToRound(this.mainQuestionPool, 1, "3");

					break;
				//For Round 3
				case 3: ; 
					this.mainQuestionPool = currentRound.addQuestionsToRound(this.mainQuestionPool, 1, "2");
					this.mainQuestionPool = currentRound.addQuestionsToRound(this.mainQuestionPool, 2, "3");
					break;
				}
		        
		        this.currentEarnings = currentRound.doRound(lifelines, this.roundNumber, true);//set to true as lifelines are allowed by default
		        
		        if(roundCompleted()) {//to validation and what the player will do now that round was completed
		        	break;//if decided to walk away -> break from loop to handle final result
		        }
		        
	        }while(roundNumber<=this.NUM_OF_ROUNDS && currentEarnings != 0.0);//check whether it is within allowed number of roundsand whether the player has answered all questions correctly
			handleGameFinalScore();
		}
		catch(Exception e) {//general exception to catch any stray errors
			System.out.println("There was an Error in Easy Game Handling Phase:" + e + ": Quitting Game");
		}
	}
	
}
