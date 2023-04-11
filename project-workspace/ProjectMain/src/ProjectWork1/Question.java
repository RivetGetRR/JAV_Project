package ProjectWork1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Question {
	private Scanner enter;//Scanner declared here to be reused
	private final String splitSymbol = ":"; //used to split the question data into its key components
	private int questionNum;
	private String questionContent;
	private ArrayList<String> possibleAnswers;
	private String correctAns;//Store Correct Answer
	private int correctAnsIndex; //correct index ( 0-3 of the correct answer in answer choices )
	private ArrayList<String> validChoices;	
	private LifeLineHandler lifelineHandler;
	
	public Question(int number,  String questionData, LifeLineHandler lh) { 
		//Take question data to populate question object values:
		//question data in format = questioncontent:correctanswer:wrongans1:wrongans2:wrongans3*/
		this.possibleAnswers = new ArrayList<String>();//contains all possible answers
		this.validChoices = new ArrayList<String>();//what the valid choices are for the question, i.e. lifelines, answers
		this.enter = new Scanner(System.in);
		this.lifelineHandler = lh;
		try {
			//Here the question data is taken as an element form the passed Array List
			//and then split up accordingly, as stated in the above format
			//then placed into its correct variable to display question dynamically
			this.questionContent = questionData.substring(0, questionData.indexOf(this.splitSymbol));//Set the question itself
			questionData = questionData.substring(questionData.indexOf(this.splitSymbol)+(this.splitSymbol.length()), questionData.length());
			//Substring Process:
			
			this.correctAns = questionData.substring(0, questionData.indexOf(this.splitSymbol));					
			String[] hold = questionData.split(this.splitSymbol);//Splits question into its components using the split symbol

			//add answers to an arraylist
			for(int j=0;j<hold.length;++j) {
				this.possibleAnswers.add(hold[j]);
			}
			//shuffle answers so that placement of answers varies each time
			Collections.shuffle(this.possibleAnswers);	
			//since answers are shuffled, program needed to know which letter contains correct answer:
			this.questionNum = number;
			this.correctAnsIndex = -1;//default value that is unreachable otherwise
			for(int i=0;i<possibleAnswers.size();++i) {
				if(this.possibleAnswers.get(i).equals(this.correctAns)) {//checks which index,position, 
					//the correct answer is in by comparing values
					this.correctAnsIndex = i;
					break;//once found, break out of loop to save time
				}
			}
		}
		catch(Exception e) {//general exception just in case
			throw e;
		}		
	}
	
	//throws exception to keep in line with best practice
	public String handleQuestion(/*LifeLineHandler lifelineHandler,*/ boolean lifelineAllowed, double questionWorth) throws Exception{
		//Initialize string variables to hold input values
		String choice ="";
		String confirmation = "";
		String result = "redo";
		try {
			//Key for how lifeline array is handled
			//where lifelines[0] is for 50/50, lifelines[1] is for Ask the Audience and lifelines[2] is for Phone a Friend
			
			boolean isInValid = false;//
			//Loop to ensure validation of input answer: A/a or B/b or C/c or D/c
			do {
				do {
					//calls method to print question data
					printQuestion(lifelineAllowed, questionWorth);
					System.out.print("Enter Choice: ");
					choice = enter.next();
					
					//confirm whether player's choice
					System.out.print("Is " + choice.toUpperCase() + " your choice? (Y/N): ");
					confirmation = enter.next();
					//If player confirms choice:
					if(confirmation.toUpperCase().equals("Y")) {
						for(int i=0;i<this.validChoices.size();++i) {//check whether that choice is valid
							if(choice.toUpperCase().equals(validChoices.get(i))) {
								isInValid = true; break;//validation -> complete exits loop
							}
						}
						//if player changes mind
					}else if(confirmation.toUpperCase().equals("N")) {
						System.out.println("Okay...Choose Again:");
						System.out.println("");
						continue;
					}else {//if player entered incorrect value: not Y/N -> try again
						System.out.println("Invalid Option for confirmation: Try again");
						System.out.println("");
						//flag = true;
						continue;
					}
	
					if(!isInValid) {
						//return "invalid";
						System.out.println("Invalid Option for Question "+ this.questionNum +", Try Again");
						System.out.println("");
						isInValid = false;//resets validation
					}
				}while(!isInValid);
				
				//check what choice player made
				switch((choice.toUpperCase().charAt(0))) {
					case 'S': System.out.println("50/50 used"); 
						result = this.lifelineHandler.handle50_50(this.questionNum, this.questionContent,this.possibleAnswers,this.correctAnsIndex);
						break;
					case 'K': System.out.println("Ask the Audience used"); 
						this.lifelineHandler.handleAskAudience(this.possibleAnswers, this.correctAnsIndex);
						break;
					case 'P': this.lifelineHandler.handlePhoneFriend(this.questionContent,this.correctAns);
						System.out.println("Phone a friend used"); 
						break;
					
					//if an answer checks whether it matches the correct index, i.e is the correct answer.
					case 'A': if(0 == this.correctAnsIndex) {result = "correct"; }else{result = "wrong";} break;
					case 'B': if(1 == this.correctAnsIndex) {result = "correct"; }else{result = "wrong";} break;
					case 'C': if(2 == this.correctAnsIndex) {result = "correct"; }else{result = "wrong";} break;
					case 'D': if(3 == this.correctAnsIndex) {result = "correct"; }else{result = "wrong";} break;
					default: result = "error";//just in case and 'error' statement to default to, handled by caller
				}
			}while(result == "redo");
			return result;
		}
		catch(Exception e) {//general exception catch any stray errors
			System.out.println("Error Occured in Question Phase: " + e.getMessage());
			return "error";
		}
	}
	
	//print the question onto the console using information received from function call
	private void printQuestion(boolean lifelineAllowed, double questionWorth) {
		if(lifelineAllowed) {
			//show lifeline options if applicable/not used
			if(this.lifelineHandler.getSpecificLifelineStatus(0)) {
				System.out.print(" S)50/50 ");
				//Validation -> this list contains all the valid choices for the question.
				this.validChoices.add("S");
			}
			if(this.lifelineHandler.getSpecificLifelineStatus(1)) {
				System.out.print(" K)As the Audience ");
				this.validChoices.add("K");
			}
			if(this.lifelineHandler.getSpecificLifelineStatus(2)) {
				System.out.print(" P)Phone A Friend ");
				this.validChoices.add("P");
			}
		}
		System.out.println("");
		System.out.println("Q"+ this.questionNum +")|=="+ this.questionContent +"==|");
		System.out.println("       Question is worth " + questionWorth + "        ");
		System.out.println("");//below list is randomized
		System.out.println("A) " + this.possibleAnswers.get(0));
		System.out.println("B) " + this.possibleAnswers.get(1));
		System.out.println("C) " + this.possibleAnswers.get(2));
		System.out.println("D) " + this.possibleAnswers.get(3));
		//Validation -> adding what choices are valid
		this.validChoices.add("A");
		this.validChoices.add("B");
		this.validChoices.add("C");
		this.validChoices.add("D");
		//-------------
		System.out.println("");
	}
}