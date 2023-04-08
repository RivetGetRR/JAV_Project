package ProjectWork1;

public class LifeLineHandler {
	private boolean[] lifelineStatus; 
	//OR create three separate Boolean values here and create the array with 
	//their values in Round Handler or equivalent class -> for clarity's sake
	
	public LifeLineHandler() {
		System.out.println("Something is not right: LifeLineHandler");
	}
	
	public LifeLineHandler(int t) {
		this.lifelineStatus[0] = true;//50/50
		this.lifelineStatus[1] = true;//AskAudience
		this.lifelineStatus[2] = true;//PhoneFriend
	}

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
	
	public void handle50_50() {
		
		//Set 50/50 used:
		this.setSpecificLifelineStatus(0,false);
	}
	
	public void handleAskAudience() {
		// A 10% B 50%C 25% D 15%
		//correct answer -> highest or second highest
		//Set AskAudience used:
		this.setSpecificLifelineStatus(1,false);
	}
	
	public void handlePhoneFriend() {
		//fake conversation -random answer from 3 options
		//Set PhoneFriend used:
		this.setSpecificLifelineStatus(2,false);
	}
}
