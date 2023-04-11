package ProjectWork1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class LifeLineHandler {
	private boolean[] lifelineStatus; 
	//OR create three separate Boolean values here and create the array with 
	//their values in Round Handler or equivalent class -> for clarity's sake

	public LifeLineHandler() {
		this.lifelineStatus = new boolean[3];//set all lifelines as 'unused' by default
		this.lifelineStatus[0] = true;//50/50
		this.lifelineStatus[1] = true;//AskAudience
		this.lifelineStatus[2] = true;//PhoneFriend
	}

	//Getter and Setter Methods
	public boolean[] getLifelineStatus() {
		return lifelineStatus;
	}

	public void setLifelineStatus(boolean[] lifelineStatus) {
		this.lifelineStatus = lifelineStatus;
	}
	
	public void setSpecificLifelineStatus(int index, boolean value) {
		this.lifelineStatus[index] = value;
	}
	
	public boolean getSpecificLifelineStatus(int index) {
		return this.lifelineStatus[index];
	}
	
	public String handle50_50(int questionNumber, String question, ArrayList<String> answers, int correctAnsIndex) {
		boolean isInValid = false;
		Scanner enter = new Scanner(System.in);
		String choice = "";
		//Assembles the Question with the correct answer and two of its wrong Answers removed
		//below list is randomized -> Question object cannot be reused without potential heavy re-tooling for just one use 
		//therefore redone here
		String correct = answers.get(correctAnsIndex); answers.remove(correctAnsIndex);
		Collections.shuffle(answers);
		answers.remove(answers.size() - 1); answers.remove(answers.size() - 2);
		answers.add(correct);
		Collections.shuffle(answers);
		correctAnsIndex = answers.indexOf(correct);
		//confirm the player's choice
		do {
			System.out.println("Q"+ questionNumber +")|=="+ question +"==|");
			System.out.println("");
			System.out.println("A) " + answers.get(0));//randomly placed again
			System.out.println("B) " + answers.get(1));
			System.out.println("");
			System.out.print("Enter Choice: ");
			choice = enter.next();
			System.out.print("Is " + choice.toUpperCase() + " your choice? (Y/N): ");
			String confirmation = enter.next();
			//If player confirms choice:
			if(confirmation.toUpperCase().equals("Y")) {
				//check whether that choice is valid
					if(choice.toUpperCase().equals("A") || choice.toUpperCase().equals("B")) {
						isInValid = true; break;//validation -> complete exits loop
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
				System.out.println("Invalid Option for Question "+ questionNumber +", Try Again");
				System.out.println("");
				isInValid = false;//resets validation
			}
		}while(!isInValid);
		//enter.close();
		//Set 50/50 used:
		this.setSpecificLifelineStatus(0,false);
		switch((choice.toUpperCase().charAt(0))) {//charAt for additional safety from potential errors
		
		//if an answer checks whether it matches the correct index, i.e is the correct answer.
		case 'A': if(0 == correctAnsIndex) {return "correct"; }else{return "wrong";}
		case 'B': if(1 == correctAnsIndex) {return "correct"; }else{return "wrong";}
		default: return "error";//just in case and 'error' statement to default to, handled by caller
		}	
	}
	
	public void handleAskAudience(ArrayList<String> answers, int correctIndex) {
		// A 10% B 50%C 25% D 15%
		//correct answer -> highest or second highest
		//Set AskAudience used:
		Random randomInt = new Random();
		int[] percentageArray = new int[4];
		System.out.println("");
		System.out.println("Then .. Lets Ask the Audience!");
		System.out.println("");
		//Below assembles the percentages, with fluctuation to simulate audience polling, given where correct answer was placed in question
		if(correctIndex == 0 || correctIndex == 2) {
			if(correctIndex == 0) {
				percentageArray[0] = randomInt.nextInt(9) + 32;//Set percentage with some variance/random value added for dynamic results
				percentageArray[1] = randomInt.nextInt(7) + 25;
				percentageArray[2] = randomInt.nextInt(5) + 12;
				percentageArray[3] = 100 - (percentageArray[0] + percentageArray[1] +percentageArray[2]);//last percentage is  100 - others.					
			}else {
				percentageArray[2] = randomInt.nextInt(9) + 32;
				percentageArray[3] = randomInt.nextInt(7) + 25;
				percentageArray[1] = randomInt.nextInt(5) + 12;
				percentageArray[0] = 100 - (percentageArray[1] + percentageArray[2] +percentageArray[3]);
			}
		}
		if(correctIndex == 1 || correctIndex == 3) {
			if(correctIndex == 3) {//In this set the audience is more likely to be wrong, to add some variance
				percentageArray[1] = randomInt.nextInt(10) + 29;
				percentageArray[3] = randomInt.nextInt(15) + 24;
				percentageArray[2] = randomInt.nextInt(5) + 17;
				percentageArray[0] = 100 - (percentageArray[2] + percentageArray[3] +percentageArray[1]);
			}else {
				percentageArray[0] = randomInt.nextInt(20) + 19;
				percentageArray[1] = randomInt.nextInt(5) + 23;
				percentageArray[3] = randomInt.nextInt(16) + 17;
				percentageArray[2] = 100 - (percentageArray[0] + percentageArray[3] +percentageArray[1]);
			}
		}//Presentation of calculated percentages -> simulate Polling like in actual show		
		System.out.println(" Audience Says....");
		System.out.println("A) " + percentageArray[0] + "% | B) " + percentageArray[1] + "%");
		System.out.println("C) " + percentageArray[2] + "% | D) " + percentageArray[3] + "%");
		System.out.println("");//Polling shown, player can decide based on the results, question is shown again. -> same for phone a friend
		//Set Ask the Audience used
		this.setSpecificLifelineStatus(1,false);
	}
	
	public void handlePhoneFriend(String question, String correctAns) {
		//fake conversation -this option always give the correct answer: Your Friend is very smart
		System.out.println("Hello? \nOh its You!");
		System.out.println("What is it?");
		System.out.println("Hmm? " + question);
		System.out.println("Okay.. I think it's " + correctAns);//Correct answer and question given to make dialogue more dynamic.
		System.out.println("Yeah, that's my guess! \nGood Luck, Bye!");
		System.out.println("");
		System.out.println("..And thats that! Will you take your Friend's Answer? Or Not..?");
		System.out.println("");
		//Set PhoneFriend used:
		this.setSpecificLifelineStatus(2,false);
	}
}
