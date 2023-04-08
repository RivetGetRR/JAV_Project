package ProjectWork1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//set to abstract as this class itself would be used, just extended from
public abstract class RoundHandler {
	//all protected to allow for inheritance
	protected final int NUM_OF_ROUNDS = 3;
	protected int currentRound;
	protected double score;
	protected boolean[] lifelineStatus = new boolean[3];
	protected Scanner enter;
	protected String choice;
	
	public RoundHandler() {
		//Work on this:-----
		this.lifelineStatus[0] = true;
		this.lifelineStatus[1] = true;
		this.lifelineStatus[2] = true;
		//:-------------
		this.enter = new Scanner(System.in);
		System.out.println("Initialised");
	}
	//in future the method must accept the main selected bank of questions: 9 or 15 in the form of either array or arraylist
	
	protected void gameOver() {
		System.out.println("Incorrect Answer - Earnings Lost");
		System.out.println("You Finish the game with $0");
		System.out.println("");
	}
	
	//backup Question Pool?
	protected ArrayList<String> initializeQuestionPool(int numberOfQuestions) {
		ArrayList<String> mainQuestionListm = new ArrayList<String>();
		
		
		mainQuestionListm.add("Which of the following is not a Mammal?:@_Finch:@_Whale:@_Bear:@_Bat");
        mainQuestionListm.add("What is 15 x 21?:@_315:@_210:@_330:@_310");
        mainQuestionListm.add("What is the Capital of France:@_Paris:@_Notre Dame:@_Rio:@_Venice");
        mainQuestionListm.add("What is refered to as 'the powerhouse of the cell'?:@_Mitochondria:@_Ectoplasmic Reticulum:@_DNA:@_Cell Heart");
        mainQuestionListm.add("Word for 'Remembering the Past Fondly'?:@_Nostalgia:@_Meloncholy:@_Anxiety:@_Mortis");
        mainQuestionListm.add("questioncontent6:@_correctanswer6:@_wrongans61:@_wrongans62:@_wrongans63");
        mainQuestionListm.add("questioncontent7:@_correctanswer7:@_wrongans71:@_wrongans72:@_wrongans73");
        mainQuestionListm.add("questioncontent8:@_correctanswer8:@_wrongans81:@_wrongans82:@_wrongans83");
        mainQuestionListm.add("questioncontent9:@_correctanswer9:@_wrongans91:@_wrongans92:@_wrongans93");
        mainQuestionListm.add("questioncontent10:@_correctanswer10:@_wrongans101:@_wrongans102:@_wrongans103");
        mainQuestionListm.add("questioncontent11:@_correctanswer11:@_wrongans111:@_wrongans112:@_wrongans113");
        mainQuestionListm.add("questioncontent12:@_correctanswer12:@_wrongans121:@_wrongans122:@_wrongans123");
        mainQuestionListm.add("questioncontent13:@_correctanswer13:@_wrongans131:@_wrongans132:@_wrongans133");
        mainQuestionListm.add("questioncontent14:@_correctanswer14:@_wrongans141:@_wrongans142:@_wrongans143");
        mainQuestionListm.add("questioncontent15:@_correctanswer15:@_wrongans151:@_wrongans152:@_wrongans153");
		
        return mainQuestionListm;
	}
	
	//Code from Dilip----------------
	public static ArrayList<String> loadQuestionPool(int numOfQuestions) throws IOException, FileNotFoundException {
		ArrayList<String> questions = new ArrayList<>();
		File file = new File("src/ProjectWork1/questionBank.txt");//!CHANGE THIS FOR FINAL FILE!
		
		int counter = 0;
		try(Scanner input = new Scanner(file)){
			//read the data from the file in an alternative way: read line by line then token by token
			while(input.hasNextLine() && counter < numOfQuestions) {
				String line = input.nextLine();
				questions.add(line);
				counter += 1;
			}
			return questions;
		}
	}
	
	public static ArrayList<String> loadQuestionPool() throws IOException, FileNotFoundException {
		ArrayList<String> questions = new ArrayList<>();
		File file = new File("src/ProjectWork1/questionBank.txt");//!CHANGE THIS FOR FINAL FILE!
		
		try(Scanner input = new Scanner(file)){
			//read the data from the file in an alternative way: read line by line then token by token
			while(input.hasNextLine()) {
				String line = input.nextLine();
				questions.add(line);
			}
			return questions;
		}
	}
	//---------------------------
	
	public abstract void runRounds();
	
	
	
}
