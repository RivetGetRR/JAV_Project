package ProjectWork1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Question {
	private Scanner enter;//Scanner declared here to be reused
	private final String splitSymbol = ":"; //:@_ used to be default symbol -> kept here incase
	private int questionNum;
	private String questionContent;
	private ArrayList<String> possibleAnswers;
	private String correctAns;
	private int correctAnsIndex; //correct index ( 0-3 of the correct answer in answer choices )
	private ArrayList<String> validChoices;
	
	public Question() {
		System.out.println("Something is not right: Question");
	}
	
	public Question(int number,  String questionData) { 
		//Take question data to populate question object values:
		/*IDEA question data in format = questioncontent:correctanswer:wrongans1:wrongans2:wrongans3*/
		//IMPORTANT transfer this logic to another method in class with return variable declaring that everything is set or error recorded?
		this.possibleAnswers = new ArrayList<String>();//contains all possible answers
		this.validChoices = new ArrayList<String>();//what the valid choices are for the question, i.e. lifelines, answers
		this.enter = new Scanner(System.in);
		try {
			//Here the question data is taken as an element form the passed arraylist
			//and then split up accordingly, as stated in the above format
			//then placed into its correct variable to display question dynamically
			this.questionContent = questionData.substring(0, questionData.indexOf(this.splitSymbol));
			questionData = questionData.substring(questionData.indexOf(this.splitSymbol)+(this.splitSymbol.length()), questionData.length());
			this.correctAns = questionData.substring(0, questionData.indexOf(this.splitSymbol));		
			
			String[] hold = questionData.split(this.splitSymbol);

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
			System.out.println(e);
		}
		//IDEA END
		
	}
	//throws exception to keep in line with best practice
	public String handleQuestion(boolean[] lifelines, boolean lifelineAllowed, double questionWorth) throws Exception{
		//Initialize string variables to hold input values
		String choice ="";
		String confirmation = "";
		try {
			//Key for how lifeline array is handled
			//where lifelines[0] is for 50/50, lifelines[1] is for Ask the Audience and lifelines[2] is for Phone a Friend
			
			boolean isInValid = false;//
			//Loop to ensure validation of input answer: A/a or B/b or C/c or D/c
			do {
				//calls method to print question data
				printQuestion(lifelines, lifelineAllowed, questionWorth);
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
				case 'S': System.out.println("50/50 used"); return "50/50";
				case 'K': System.out.println("Ask the Audience used"); return "Ask Audeince";
				case 'P': System.out.println("Phone a friend used"); return "Phone Friend";
				//if an answer checks whether it matches the correct index, i.e is the correct answer.
				case 'A': if(0 == this.correctAnsIndex) {return "correct"; }else{return "wrong";}
				case 'B': if(1 == this.correctAnsIndex) {return "correct"; }else{return "wrong";}
				case 'C': if(2 == this.correctAnsIndex) {return "correct"; }else{return "wrong";}
				case 'D': if(3 == this.correctAnsIndex) {return "correct"; }else{return "wrong";}
				default: return "error";//just in case and 'error' statement to default to, handled by caller
			}
		}
		catch(Exception e) {//general exception catch any stray errors
			System.out.println("Error Occured: " + e.getMessage());
			return "error";
		}
	}
	
	private void printQuestion(boolean[] lifelines, boolean lifelineAllowed, double questionWorth) {
		if(lifelineAllowed) {
			//show lifeline options if applicable/not used
			if(lifelines[0]) {
				System.out.print(" S)50/50 ");
				//Validation -> this list contains all the valid choices for the question.
				this.validChoices.add("S");
			}
			if(lifelines[1]) {
				System.out.print(" K)As the Audience ");
				this.validChoices.add("K");
			}
			if(lifelines[2]) {
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
		//Validation
		this.validChoices.add("A");
		this.validChoices.add("B");
		this.validChoices.add("C");
		this.validChoices.add("D");
		//-------------
		System.out.println("");
	}
}