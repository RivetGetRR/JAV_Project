package test;


import java.util.ArrayList;
import java.util.Collections;

import ProjectWork1.GameHandler;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<String> m = new ArrayList<String>();
		/* Test Data 1
	        m.add("questioncontent1:@_correctanswer1:@_wrongans11:@_wrongans12:@_wrongans13");
	        m.add("questioncontent2:@_correctanswer2:@_wrongans21:@_wrongans22:@_wrongans23");
	        m.add("questioncontent3:@_correctanswer3:@_wrongans31:@_wrongans32:@_wrongans33");
	        m.add("questioncontent4:@_correctanswer4:@_wrongans41:@_wrongans42:@_wrongans34");
	        m.add("questioncontent5:@_correctanswer5:@_wrongans51:@_wrongans52:@_wrongans53");
	        m.add("questioncontent6:@_correctanswer6:@_wrongans61:@_wrongans62:@_wrongans63");
	        m.add("questioncontent7:@_correctanswer7:@_wrongans71:@_wrongans72:@_wrongans73");
	        m.add("questioncontent8:@_correctanswer8:@_wrongans81:@_wrongans82:@_wrongans83");
	        m.add("questioncontent9:@_correctanswer9:@_wrongans91:@_wrongans92:@_wrongans93");
	     */
	    //System.out.println(m);
			m.add("Which of the following is not a Mammal?:@_Finch:@_Whale:@_Bear:@_Bat");
	        m.add("What is 15 x 21?:@_315:@_210:@_330:@_310");
	        m.add("What is the Capital of France:@_Paris:@_Notre Dame:@_Rio:@_Venice");
	        m.add("What is refered to as 'the powerhouse of the cell'?:@_Mitochondria:@_Ectoplasmic Reticulum:@_DNA:@_Cell Heart");
	        m.add("Word for 'Remembering the Past Fondly'?:@_Nostalgia:@_Meloncholy:@_Anxiety:@_Mortis");
	        m.add("questioncontent6:@_correctanswer6:@_wrongans61:@_wrongans62:@_wrongans63");
	        m.add("questioncontent7:@_correctanswer7:@_wrongans71:@_wrongans72:@_wrongans73");
	        m.add("questioncontent8:@_correctanswer8:@_wrongans81:@_wrongans82:@_wrongans83");
	        m.add("questioncontent9:@_correctanswer9:@_wrongans91:@_wrongans92:@_wrongans93");
		
		    //Collections.shuffle(m);
		    
		    
		    
		    //System.out.println(m);
		    
		    /*IDEA question data in format questioncontent:@_correctanswer:@_wrongans1:@_wrongans2:@_wrongans3
		    String data = "questioncontent:@_correctanswer:@_wrongans1:@_wrongans2:@_wrongans3";
		    System.out.println(data.indexOf(":@_"));
		    System.out.println(data.substring(0, data.indexOf(":@_")));
		    String[] s = data.split(":@_");
		    System.out.println(s[0] +","+ s[1]+","+ s[2]+","+ s[3]);*/
		    
		   /* Question q = new Question(1,"questioncontent:@_correctanswer:@_wrongans1:@_wrongans2:@_wrongans3");
		    boolean[] l = {true,true,false};
		    String c = q.printQuestion(l,true);
		    System.out.println(c);
		        double[] l = {400.0,500.0,600.0};
		    Round r = new Round(l,3);
		    r.initializeQuestions(m);*/
		    //RoundHandler r = new RoundHandler(1,9);    
		    //r.runRounds();;
		    /*    
		    int i = 1;
		    do {
		    	System.out.println(0 + (i-1)*5);
		    	System.out.println(1 + (i-1)*5);
		    	System.out.println(2 + (i-1)*5);
		    	System.out.println(3 + (i-1)*5);
		    	System.out.println(4 + (i-1)*5);
		    	i += 1;
		    }while(i < 4);
		    
		        */
		        
		    //System.out.println(c);
		        

		    GameHandler g = new GameHandler();
		    g.displayStartMenu();
	        
	        
	}

}
