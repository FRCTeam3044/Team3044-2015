	package org.usfirst.frc.team3044.robot;
	import org.usfirst.frc.team3044.DriverStation.SecondaryController;
	import org.usfirst.frc.team3044.utils.Components;
	//USING JAGUAR LEFT1 AND RIGHT1 FOR UP AND DOWN
	//CHECK HOW TO IMPLIMENT THE SECONDARY CONTROLLER JOYSTICKS
	//ALREADY REPLACED MOTORS AND LIMIT SWITCHES (DIGITAL INPUTS)
	//Jaguar ForkJag = new Jaguar(0); //NEED JAGUAR FORKLIFT COMPONENT
	//DigitalInput upperlimit = new DigitalInput(1); //CHECK ACTUAL POSITION
	//DigitalInput lowerlimit = new DigitalInput(2); //CHECK ACTUAL POSITION
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
    	switch(ForkliftState) {
		//SPACE
		case TOP:
		if(Joy.getLeftY() < 0){
			if(!Components.forkliftDown.get()){
				ForkliftState = MOVINGDOWN;
				Components.forkliftLeft1.set(-1);
				Components.forkliftRight1.set(-1);
			}	
		}
		break;
		//SPACE
		case MOVINGDOWN:
		if(Components.forkliftDown.get()){
			ForkliftState = BOTTOM;
			Components.forkliftLeft1.set(0);
			Components.forkliftRight1.set(0);
		}
		if(Joy.getLeftY() == 0){
			ForkliftState = STOPPEDMID;
			Components.forkliftLeft1.set(0);
			Components.forkliftRight1.set(0);
		}
		if(Joy.getLeftY() > 0){
			if(!Components.forkliftUp.get()){
				ForkliftState = MOVINGUP;
				Components.forkliftLeft1.set(1);
				Components.forkliftRight1.set(1);
			}
		}
		break;
		//SPACE
		case BOTTOM:
    	if(Joy.getLeftY() > 0){
    		if(!Components.forkliftUp.get()){
        	ForkliftState = MOVINGUP;
        	Components.forkliftLeft1.set(1);
        	Components.forkliftRight1.set(1);
    		}
    	}
    	break;
    	//SPACE
		case MOVINGUP:
		if(Components.forkliftUp.get()){
			ForkliftState = TOP;
			Components.forkliftLeft1.set(0);
			Components.forkliftRight1.set(0);
		}
		if(Joy.getLeftY() < 0){
			if(!Components.forkliftDown.get()){
			ForkliftState = MOVINGDOWN;
			Components.forkliftLeft1.set(-1);
			Components.forkliftRight1.set(-1);
			}
		}
		if(Joy.getLeftY() == 0){
			ForkliftState = STOPPEDMID;
			Components.forkliftLeft1.set(0);
			Components.forkliftRight1.set(0);
		}
		break;
		//SPACE
		case STOPPEDMID:
			if(Joy.getLeftY() > 0){
				if(!Components.forkliftUp.get()){
				ForkliftState = MOVINGUP;
				Components.forkliftLeft1.set(1);
				Components.forkliftRight1.set(1);
				}
			}
			if(Joy.getLeftY() < 0){
				if(!Components.forkliftDown.get()){
				ForkliftState = MOVINGDOWN;
				Components.forkliftLeft1.set(-1);
				Components.forkliftRight1.set(-1);
				}
			}
			break;
		case OUT:
			if(Joy.getRawButton(Components.ARM_OUT)){
				Components.forkliftClamp.set(true);
			}
			break;
		case IN:
			if(Joy.getRawButton(Components.ARM_IN)){
				Components.forkliftClamp.set(false);
			break;
			}
	}
}
	
	public void autoInit(){
		
	}
	
	public void disabled(){
		
	}
	
	public void forkliftPeriodic(){
		
		
	}

}
