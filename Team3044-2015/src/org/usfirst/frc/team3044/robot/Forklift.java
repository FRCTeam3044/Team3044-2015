	package org.usfirst.frc.team3044.robot;
	import org.usfirst.frc.team3044.DriverStation.SecondaryController;
	import org.usfirst.frc.team3044.utils.Components;
	//USING JAGUAR LEFT1 AND RIGHT1 FOR UP AND DOWN
	//DigitalInput upperlimit = new DigitalInput(1); //CHECK ACTUAL POSITION
	//DigitalInput lowerlimit = new DigitalInput(2); //CHECK ACTUAL POSITION
	//WHEN ALL ELSE IS DONE, CHECK ELECTRONIC POSITIONS
	public class Forklift {
	SecondaryController Joy = SecondaryController.getInstance();
	final int TOP = 1;
	final int MOVINGUP = 2; 
	final int BOTTOM = 3;
	final int MOVINGDOWN = 4;
	final int STOPPEDMID = 5;
	final int OUT = 6;
	final int IN = 7;
	int ForkliftState = 3;
	public void robotInit(){	
	
	}
	
	public void teleopInit(){

	}
	
	public void autoInit(){
		
	}
	
	public void disabled(){
		
	}
	
	public void forkliftPeriodic(){
    	switch(ForkliftState) {
		//SPACE
		case TOP:
		if(Joy.getLeftY() < 0){
			if(!Components.forkliftDown.get()){
				ForkliftState = MOVINGDOWN;
				Components.forkliftLeft1.set(-1);
				Components.forkliftLeft2.set(-1);
				Components.forkliftRight1.set(-1);
				Components.forkliftRight2.set(-1);
			}	
		}
		break;
		//SPACE
		case MOVINGDOWN:
		if(Components.forkliftDown.get()){
			ForkliftState = BOTTOM;
			Components.forkliftLeft1.set(0);
			Components.forkliftLeft2.set(0);
			Components.forkliftRight1.set(0);
			Components.forkliftRight2.set(0);
		}
		if(Joy.getLeftY() == 0){
			ForkliftState = STOPPEDMID;
			Components.forkliftLeft1.set(0);
			Components.forkliftLeft2.set(0);
			Components.forkliftRight1.set(0);
			Components.forkliftRight2.set(0);
		}
		if(Joy.getLeftY() > 0){
			if(!Components.forkliftUp.get()){
				ForkliftState = MOVINGUP;
				Components.forkliftLeft1.set(1);
				Components.forkliftLeft2.set(1);
				Components.forkliftRight1.set(1);
				Components.forkliftRight2.set(1);
			}
		}
		break;
		//SPACE
		case BOTTOM:
    	if(Joy.getLeftY() > 0){
    		if(!Components.forkliftUp.get()){
        	ForkliftState = MOVINGUP;
			Components.forkliftLeft1.set(1);
			Components.forkliftLeft2.set(1);
			Components.forkliftRight1.set(1);
			Components.forkliftRight2.set(1);
    		}
    	}
    	break;
    	//SPACE
		case MOVINGUP:
		if(Components.forkliftUp.get()){
			ForkliftState = TOP;
			Components.forkliftLeft1.set(0);
			Components.forkliftLeft2.set(0);
			Components.forkliftRight1.set(0);
			Components.forkliftRight2.set(0);
		}
		if(Joy.getLeftY() < 0){
			if(!Components.forkliftDown.get()){
			ForkliftState = MOVINGDOWN;
			Components.forkliftLeft1.set(-1);
			Components.forkliftLeft2.set(-1);
			Components.forkliftRight1.set(-1);
			Components.forkliftRight2.set(-1);
			}
		}
		if(Joy.getLeftY() == 0){
			ForkliftState = STOPPEDMID;
			Components.forkliftLeft1.set(0);
			Components.forkliftLeft2.set(0);
			Components.forkliftRight1.set(0);
			Components.forkliftRight2.set(0);
		}
		break;
		//SPACE
		case STOPPEDMID:
			if(Joy.getLeftY() > 0){
				if(!Components.forkliftUp.get()){
				ForkliftState = MOVINGUP;
				Components.forkliftLeft1.set(1);
				Components.forkliftLeft2.set(1);
				Components.forkliftRight1.set(1);
				Components.forkliftRight2.set(1);
				}
			}
			if(Joy.getLeftY() < 0){
				if(!Components.forkliftDown.get()){
				ForkliftState = MOVINGDOWN;
				Components.forkliftLeft1.set(-1);
				Components.forkliftLeft2.set(-1);
				Components.forkliftRight1.set(-1);
				Components.forkliftRight2.set(-1);
				}
			}
			break;
		case OUT:
			if(Joy.getRawButton(Components.FORK_OUT_BUTTON)){//CHANGE
				Components.forkliftClamp.set(true);
				//Components.forkliftClamp2.set(true);		//CHANGE!!!!!!!!!!
			}
			break;
		case IN:
			if(Joy.getRawButton(Components.FORK_IN_BUTTON)){//CHANGE
				Components.forkliftClamp.set(false);
				//Components.forkliftClamp2.set(false);		//CHANGE!!!!!!!!!!
			break;
			}
    	}
	}

}