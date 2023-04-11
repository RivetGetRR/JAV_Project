package ProjectWork1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class HardGameRH extends RoundHandler {//set these constants here
	private final int NUMBER_OF_QUESTIONS_PER_ROUND = 5;
	private final int QUESTION_POOL_SIZE = 15;//Unused but kept if needed
	private final double[] MONEY_VALUES = {100,200,300,500,1000,2000,4000,8000,16000,32000,64000,125000,250000,500000,1000000};
	private final int ROUND_TO_ALLOW_LIFELINES = 2;//When to allow lifelines
	
	public HardGameRH(String name) throws FileNotFoundException, IOException {
		super(name);
	}
	
	@Override
	public void runRounds() {//override the abstract method in parent RoundHandler
		System.out.println("<Game Start>");
		System.out.println("");		
		int roundNumber = 1;//round Number
		String fileName = null;
		double[] moneyValuesTempHold = new double[this.NUMBER_OF_QUESTIONS_PER_ROUND];//money values
		try {
			boolean allowLifelines = true;
	        do {
	        	//Programmatically add the money values to array so that they can be display with question
	        	//used additional array to make process easier
	        	moneyValuesTempHold[0] = this.MONEY_VALUES[0 + ((roundNumber - 1) * 5)];
	        	moneyValuesTempHold[1] = this.MONEY_VALUES[1 + ((roundNumber - 1) * 5)];
	        	moneyValuesTempHold[2] = this.MONEY_VALUES[2 + ((roundNumber - 1) * 5)];
	        	moneyValuesTempHold[3] = this.MONEY_VALUES[3 + ((roundNumber - 1) * 5)];
	        	moneyValuesTempHold[4] = this.MONEY_VALUES[4 + ((roundNumber - 1) * 5)];
	        	//start round with details for hard difficulty
	        	//start round with details for easy difficulty
		        Round currentRound = new Round(moneyValuesTempHold,this.NUMBER_OF_QUESTIONS_PER_ROUND);
		        switch(this.roundNumber) {
		        //for Round 1
				case 1: 
					this.mainQuestionPool = currentRound.addQuestionsToRound(this.mainQuestionPool, 4, "1");
					this.mainQuestionPool = currentRound.addQuestionsToRound(this.mainQuestionPool, 1, "2"); 
					break;
				//for Round 2
				case 2: ; 
					this.mainQuestionPool = currentRound.addQuestionsToRound(this.mainQuestionPool, 1, "1");
					this.mainQuestionPool = currentRound.addQuestionsToRound(this.mainQuestionPool, 3, "2");
					this.mainQuestionPool = currentRound.addQuestionsToRound(this.mainQuestionPool, 1, "3");

					break;
				//For Round 3
				case 3: ; 
					this.mainQuestionPool = currentRound.addQuestionsToRound(this.mainQuestionPool, 1, "2");
					this.mainQuestionPool = currentRound.addQuestionsToRound(this.mainQuestionPool, 4, "3");
					break;
				}				                        //lifelineStatus
		        this.currentEarnings = currentRound.doRound(lifelines, roundNumber, allowLifelines);
		        
		        if(roundCompleted()) {//to validation and what the player will do now that round was completed
		        	break;//if decided to walk away -> break from loop to handle final result
		        }
	        
	        }while(roundNumber<=this.NUM_OF_ROUNDS && this.currentEarnings > 0.0);//check whether it is within allowed number of rounds
	        handleGameFinalScore();
		}
		catch(Exception e) {//general exception to catch any stray errors
			System.out.println("There was an Error in Hard Game Handling Phase:" + e + ": Quitting Game");
		}
	}

}
