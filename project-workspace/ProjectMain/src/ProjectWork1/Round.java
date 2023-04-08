package ProjectWork1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Round {
	private double[] moneyValues;
	private int numOfQuestions;
	private ArrayList<String> questionSet;
	private double earnings;
	
	public Round() {
		System.out.println("Something is not right: Round");
	}
	
	public Round(double[] moneyValues, int numOfQuestions) {
		//set values -> except questions
		//then start round?
		this.questionSet = new ArrayList<String>();
		this.numOfQuestions = numOfQuestions;
		this.moneyValues = new double[3];
		this.moneyValues = moneyValues;
		this.earnings = 0.0;
	}
	
	public ArrayList<String> initializeQuestionsForRound(ArrayList<String> fullQuestionSet) {//setupRound(){
		//set questions
		Random randomInt = new Random();
		int temp = 0;

		//account for question difficulty?
		
		//Question Randomizer
		for(int i = 0;i< this.numOfQuestions; ++i) {
			temp = randomInt.nextInt(fullQuestionSet.size());
			this.questionSet.add(fullQuestionSet.get(temp));// adds random question to round question list
			fullQuestionSet.remove(temp);//then removes this question from the main list to ensure it would be asked again			
		}
		//then return the list again without selected questions
		return fullQuestionSet;
	}
	
	public double doRound(boolean[] lifelineStauses, int questionNumber, boolean allowed) {
		//Temp Test VAlues:
		Scanner input = new Scanner(System.in);

		
		System.out.println("   --Round " + questionNumber + " Start--");
		System.out.println("");
		//actually do round
		try {
			for(int j = 0; j< this.numOfQuestions; ++j) {
				Question question = new Question(j+1,this.questionSet.get(j));
				String returnedValue = question.handleQuestion(lifelineStauses, allowed, moneyValues[j]);//true id for allow lifelines
			    //System.out.println(c);
				switch (returnedValue) {
				case "correct": this.earnings = this.moneyValues[j]; System.out.println("Correct! Your current score is " + this.earnings); break;
				case "wrong": this.earnings = 0.0; j = this.numOfQuestions; break;//j = 7 for the moment
				case "error": /*Throw exception?*/ break;
				default:;
				}
			    System.out.println("");
			}
			return this.earnings;
		}
		catch(Exception e){
			System.out.println("Error Occured: " + e.getMessage());
			return -1.0;
		}		
	}
}
