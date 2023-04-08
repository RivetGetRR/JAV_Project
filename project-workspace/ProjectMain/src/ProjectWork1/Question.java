package ProjectWork1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Question {
	private Scanner enter;
	private final String splitSymbol = ":"; //:@_
	private int questionNum;
	private String questionContent;
	private ArrayList<String> possibleAnswers;
	private String correctAns;
	private int correctAnsIndex; //correct index (1-4 or 0-3 of the correct answer in answer choices 
	//private String questionData; Okay, for now we tackle individually? 
	//For validation?
	private ArrayList<String> validChoices;
	public Question() {
		System.out.println("Something is not right: Question");
	}
	
	public Question(/* String question, int llr,*/ int number,  String questionData) { //ArrayList<String> possibleAnswers) {
		//Take question data to populate question object values:
		//
		//process here : TO ADD
		//adding to arraylist should aslo take place here
		
		/*IDEA question data in format questioncontent:@_correctanswer:@_wrongans1:@_wrongans2:@_wrongans3*/
		//IMPORTANT transfer this logic to another method in class with return variable declaring that everything is set or error recorded?
		this.possibleAnswers = new ArrayList<String>();
		this.validChoices = new ArrayList<String>();
		this.enter = new Scanner(System.in);
		try {
			this.questionContent = questionData.substring(0, questionData.indexOf(this.splitSymbol));
			questionData = questionData.substring(questionData.indexOf(this.splitSymbol)+(this.splitSymbol.length()), questionData.length());
			this.correctAns = questionData.substring(0, questionData.indexOf(this.splitSymbol));		
			String[] hold = questionData.split(this.splitSymbol);
			//System.out.println(hold[0]+","+hold[1]+","+hold[2]+","+hold[3]);
			//Collections.shuffle() -> not working properly? erratic
			for(int j=0;j<hold.length;++j) {
				this.possibleAnswers.add(hold[j]);
				//System.out.println(hold[j]);
			}
			Collections.shuffle(this.possibleAnswers);
	
			
			//for now use test values:
			this.questionNum = number;
			this.correctAnsIndex = -1;
			for(int i=0;i<possibleAnswers.size();++i) {
				if(this.possibleAnswers.get(i).equals(this.correctAns)) {
					this.correctAnsIndex = i+1;
					break;
				}
				//System.out.println(":");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		//IDEA END
		
	}
	
	public String handleQuestion(boolean[] lifelines, boolean lifelineAllowed, double questionWorth) throws Exception{//String wrongAns1, String wrongAns2, String wrongAns3, String correctAns) {
		//boolean flag = false;
		String choice ="";
		String confirmation = "";
		try {
			//where lifelines[0] is for 50/50, lifelines[1] is for Ask the Audience and lifelines[2] is for Phone a Friend
			
			boolean flag = false;
			//System.out.print("Correct: " + this.correctAnsIndex);
			do {
				
				printQuestion(lifelines, lifelineAllowed, questionWorth);
				System.out.print("Enter Choice: ");
				choice = enter.next();
				//do validation here?
				
				System.out.print("Is " + choice.toUpperCase() + " your choice? (Y/N): ");
				confirmation = enter.next();
				if(confirmation.toUpperCase().equals("Y")) {
					for(int i=0;i<this.validChoices.size();++i) {
						if(choice.toUpperCase().equals(validChoices.get(i))) {
							flag = true; break;
						}
					}
				}else if(confirmation.toUpperCase().equals("N")) {
					System.out.println("Okay...Choose Again:");
					System.out.println("");
					continue;
				}else {
					System.out.println("Invalid Option for confirmation: Try again");
					System.out.println("");
					//flag = true;
					continue;
				}
				
				/*
				if(flag) {
					return this.correctAnsIndex + choice;
				}else {*/
				if(!flag) {
					//return "invalid";
					System.out.println("Invalid Option for Question "+ this.questionNum +", Try Again");
					System.out.println("");
					flag = false;
				}
			}while(!flag);
			
			switch((choice.toUpperCase().charAt(0))) {
				case 'S': System.out.println("50/50 used"); return "50/50";
				case 'K': System.out.println("Ask the Audience used"); return "Ask Audeince";
				case 'P': System.out.println("Phone a friend used"); return "Phone Friend";
				//case 'W': ;
				case 'A': if(1 == this.correctAnsIndex) {return "correct"; }else{return "wrong";}
				case 'B': if(2 == this.correctAnsIndex) {return "correct"; }else{return "wrong";}
				case 'C': if(3 == this.correctAnsIndex) {return "correct"; }else{return "wrong";}
				case 'D': if(4 == this.correctAnsIndex) {return "correct"; }else{return "wrong";}
				default: return "error";
			}
			//return this.correctAnsIndex + choice.toUpperCase();
		}
		catch(Exception e) {
			System.out.println("Error Occured: " + e.getMessage());
			return "error";
		}
	}
	
	private void printQuestion(boolean[] lifelines, boolean lifelineAllowed, double questionWorth) {
		if(lifelineAllowed) {
			if(lifelines[0]) {
				System.out.print(" S)50/50 ");
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
		//System.out.println("W) WalkAway");
		System.out.println("Q"+ this.questionNum +")|=="+ this.questionContent +"==|");
		System.out.println("       Question is worth " + questionWorth + "        ");
		System.out.println("");//add randomization to how questions are displayed -> ADDED
		System.out.println("A) " + this.possibleAnswers.get(0));
		System.out.println("B) " + this.possibleAnswers.get(1));
		System.out.println("C) " + this.possibleAnswers.get(2));
		System.out.println("D) " + this.possibleAnswers.get(3));
		//Validation
		this.validChoices.add("A");
		this.validChoices.add("B");
		this.validChoices.add("C");
		this.validChoices.add("D");
		//this.validChoices.add("W");
		//-------------
		System.out.println("");
	}
}