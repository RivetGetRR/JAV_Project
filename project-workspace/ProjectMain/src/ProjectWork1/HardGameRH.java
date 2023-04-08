package ProjectWork1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class HardGameRH extends RoundHandler {//set these constants here
	private final int NUMBER_OF_QUESTIONS_PER_ROUND = 5;
	private final int QUESTION_POOL_SIZE = 15;
	private final double[] MONEY_VALUES = {100,200,300,500,1000,2000,4000,8000,16000,32000,64000,125000,250000,500000,1000000};
	private final int ROUND_TO_ALLOW_LIFELINES = 2;//When to allow lifelines
	
	public HardGameRH() {
		super();
	}
	
	@Override
	public void runRounds() {//override the abstract method in parent RoundHandler
		System.out.println("<Game Start>");
		System.out.println("");
		System.out.println("");
		
		//Test values for build testing
		int roundNumber = 1;//round Number
		double[] moneyValuesTempHold = new double[NUMBER_OF_QUESTIONS_PER_ROUND];//money values
		try {
			ArrayList<String> mainQuestionListm = loadQuestionPool();//this method can throw an exception, handled in the catch
			//at the bottom of code
			double currentEarnings = 0.0;
			boolean flag = true;
			
			// use collections.shuffle to randomize the question pool
	        Collections.shuffle(mainQuestionListm);
	        //---------
	        
	        do {
	        	//Programmatically add the money values to array so that they can be display with question
	        	moneyValuesTempHold[0] = this.MONEY_VALUES[0 + ((roundNumber - 1) * 5)];
	        	moneyValuesTempHold[1] = this.MONEY_VALUES[1 + ((roundNumber - 1) * 5)];
	        	moneyValuesTempHold[2] = this.MONEY_VALUES[2 + ((roundNumber - 1) * 5)];
	        	moneyValuesTempHold[3] = this.MONEY_VALUES[3 + ((roundNumber - 1) * 5)];
	        	moneyValuesTempHold[4] = this.MONEY_VALUES[4 + ((roundNumber - 1) * 5)];
	        	
		        Round currentRound = new Round(moneyValuesTempHold,this.NUMBER_OF_QUESTIONS_PER_ROUND);
		        mainQuestionListm = currentRound.initializeQuestionsForRound(mainQuestionListm);
		        
		        if(roundNumber >= this.ROUND_TO_ALLOW_LIFELINES) {
		        	flag = true;
		        }else {
		        	flag = false;
		        }
		        
		        //System.out.println(mainQuestionListm.size());
		        currentEarnings = currentRound.doRound(lifelineStatus, roundNumber, flag);
		        if(currentEarnings != 0.0) { //if earnings become 0 at any point that means the player lost the game

		        	this.score = currentEarnings; //this.moneyValues[questionCount];
		        	System.out.println("Round " + roundNumber + " Completed");
		        	roundNumber += 1;
		        	
		        	System.out.print("Do you wish to Continue(C) or Walk Away(W) with Current earnings("+ currentEarnings +")?");
			        String  choice = this.enter.next();
			        if(choice.toUpperCase().equals("W")) {
			        	System.out.println("Walked Away");
			        	break; //continue;
			        }
		        }
	        
		        //Add Error handling/Validation
		        System.out.println(":::");
	        
	        }while(roundNumber<=this.NUM_OF_ROUNDS && currentEarnings != 0.0);
	        
	        if(currentEarnings == 0.0) {
	        	gameOver();
	        }else {
	        	System.out.println("Earned: " + this.score);//currentEarnings);
	        }
		}
		catch(IOException io) {
			System.out.println("file error:" + io);
		}
	}

}
