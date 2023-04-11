package ProjectWork1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Round {
	private double[] moneyValues;
	private int numOfQuestions;
	private ArrayList<String> roundQuestionList;
	private ArrayList<String> fullQuestionSet;
	private double earnings;
	
	public Round(double[] moneyValues, int numOfQuestions) {
		//set values -> except questions
		//then start round?
		this.roundQuestionList = new ArrayList<String>();
		this.fullQuestionSet = new ArrayList<String>();
		this.numOfQuestions = numOfQuestions;
		this.moneyValues = new double[3];
		this.moneyValues = moneyValues;
		this.earnings = 0.0;
	}

	protected ArrayList<String> addQuestionsToRound(ArrayList<String> fullQuestionSet, int amount, String difficulty) {
		int temp = 0;
		int count = 0;
		Random randomInt = new Random();		
		do{//each block follows same random logic as original method above
			temp = randomInt.nextInt(fullQuestionSet.size());
			if(fullQuestionSet.get(temp).startsWith(difficulty)) {//substring(2) to remove difficulty marker at front
				this.roundQuestionList.add(fullQuestionSet.get(temp).substring(2));
				fullQuestionSet.remove(temp);//question removed from bank so that it is not used again
				count += 1;
			}
		}while(count < amount);		
		return fullQuestionSet;
	}
	
	public double doRound(LifeLineHandler lifelinesHandler, int questionNumber, boolean allowed) {
		Scanner input = new Scanner(System.in);
		System.out.println("   --Round " + questionNumber + " Start--");
		System.out.println("");
		//actually do round
		try {
			for(int j = 0; j< this.numOfQuestions; ++j) {
				Question question = new Question(j+1,this.roundQuestionList.get(j), lifelinesHandler);//j+1 to allow for 1 to n and not 0 to n-1
				//String returnedValue = "correct";
				String returnedValue = question.handleQuestion(allowed, moneyValues[j]);//true id for allow lifelines
			    //System.out.println(c);
				switch (returnedValue) {
				case "correct": this.earnings = this.moneyValues[j]; System.out.println("Correct! Your current score is $" + this.earnings); break;
				case "wrong": this.earnings = 0.0; j = this.numOfQuestions; break;//j = 7 for the moment
				case "error": /*Throw exception?*/ break;
				default:;
				}
			    System.out.println("");
			}
			return this.earnings;
		}
		catch(Exception e){
			System.out.println("Error Occured in Round Phase: " + e.getCause() + ": Quitting Game");
			return -1.0;
		}		
	}
}
