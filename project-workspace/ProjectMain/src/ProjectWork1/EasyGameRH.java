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
		//Test values for build testing
		int roundNumber = 1;//round Number
		double[] moneyValuesTempHold = new double[NUMBER_OF_QUESTIONS_PER_ROUND];//money values
		try {
			ArrayList<String> mainQuestionListm = loadQuestionPool();//initializeQuestionPool(this.QUESTION_POOL_SIZE);

	        Collections.shuffle(mainQuestionListm);
	        //---------
	        
	        do {
	        	//Programmatically add the money values to array so that they can be display with question
	        	moneyValuesTempHold[0] = this.moneyValues[0 + ((roundNumber - 1) * 5)] ;
	        	moneyValuesTempHold[1] = this.moneyValues[1 + ((roundNumber - 1) * 5)] ;
	        	moneyValuesTempHold[2] = this.moneyValues[2 + ((roundNumber - 1) * 5)] ;
	        	
	        	//Create a Round
		        Round currentRound = new Round(moneyValuesTempHold,this.NUMBER_OF_QUESTIONS_PER_ROUND);
		        mainQuestionListm = currentRound.initializeQuestionsForRound(mainQuestionListm);
		        

		        currentEarnings = currentRound.doRound(lifelineStatus, roundNumber, true);
		        if(currentEarnings != 0.0) {
		        	//currentEarnings += hold;
		        	this.score = currentEarnings; //this.moneyValues[questionCount];
		        	System.out.println("Round " + roundNumber + " Completed");
		        	roundNumber += 1;
		        	
		        	System.out.print("Do you wish to Continue(C) or Walk Away(W) with Current earnings?");
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
