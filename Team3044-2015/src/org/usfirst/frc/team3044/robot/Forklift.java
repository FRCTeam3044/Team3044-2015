package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.utils.Components;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
//USING JAGUAR LEFT1 AND RIGHT1 FOR UP AND DOWN
//CHECK HOW TO IMPLIMENT THE SECONDARY CONTROLLER JOYSTICKS
//ALREADY REPLACED MOTORS AND LIMIT SWITCHES (DIGITAL INPUTS)
public class Forklift {
	//Jaguar ForkJag = new Jaguar(0); //NEED JAGUAR FORKLIFT COMPONENT
	Joystick Joy = new Joystick(1); //CHECK ACTUAL POSITION
	//DigitalInput upperlimit = new DigitalInput(1); //CHECK ACTUAL POSITION
	//DigitalInput lowerlimit = new DigitalInput(2); //CHECK ACTUAL POSITION
	final int TOP = 1;
	final int MOVINGUP = 2; 
	final int BOTTOM = 3;
	final int MOVINGDOWN = 4;
	final int STOPPEDMID = 5;
	int ForkliftState = 3; //CHECK WITH FORKLIFT GUYS
	public void robotInit(){	
	
	}
	
	public void teleopInit(){
    	switch(ForkliftState) {
		//SPACE
		case TOP:
		if(Joy.getY() < 0){
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
		if(Joy.getY() == 0){
			ForkliftState = STOPPEDMID;
			Components.forkliftLeft1.set(0);
			Components.forkliftRight1.set(0);
		}
		if(Joy.getY() > 0){
			if(!Components.forkliftUp.get()){
				ForkliftState = MOVINGUP;
				Components.forkliftLeft1.set(1);
				Components.forkliftRight1.set(1);
			}
		}
		break;
		//SPACE
		case BOTTOM:
    	if(Joy.getY() > 0){
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
		if(Joy.getY() < 0){
			if(!Components.forkliftDown.get()){
			ForkliftState = MOVINGDOWN;
			Components.forkliftLeft1.set(-1);
			Components.forkliftRight1.set(-1);
			}
		}
		if(Joy.getY() == 0){
			ForkliftState = STOPPEDMID;
			Components.forkliftLeft1.set(0);
			Components.forkliftRight1.set(0);
		}
		break;
		//SPACE
		case STOPPEDMID:
			if(Joy.getY() > 0){
				if(!Components.forkliftUp.get()){
				ForkliftState = MOVINGUP;
				Components.forkliftLeft1.set(1);
				Components.forkliftRight1.set(1);
				}
			}
			if(Joy.getY() < 0){
				if(!Components.forkliftDown.get()){
				ForkliftState = MOVINGDOWN;
				Components.forkliftLeft1.set(-1);
				Components.forkliftRight1.set(-1);
				}
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
