package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.DriverStation.SecondaryController;
import org.usfirst.frc.team3044.utils.Components;
//USING JAGUAR LEFT1 AND RIGHT1 FOR UP AND DOWN
//DigitalInput upperlimit = new DigitalInput(1); //CHECK ACTUAL POSITION
//DigitalInput lowerlimit = new DigitalInput(2); //CHECK ACTUAL POSITION
//WHEN ALL ELSE IS DONE, CHECK ELECTRONIC POSITIONS

public class Forklift {

	SecondaryController Joy = SecondaryController.getInstance();
	Components components = Components.getInstance();

	final int TOP = 1;
	final int MOVINGUP = 2;
	final int BOTTOM = 3;
	final int MOVINGDOWN = 4;
	final int STOPPEDMID = 5;
	final int OUT = 6;
	final int IN = 7;

	int ForkliftState = 3;
	
	int forkPosState = OUT;
	
	final int COMPRESSOR_ON = 0;
	final int COMPRESSOR_OFF = 1;
	int compressorState = 0;

	double movementSpeed = -.3;

	public void robotInit() {
		this.ForkliftState = STOPPEDMID;
		components.forkliftLeft1.set(0);
		components.forkliftRight1.set(0);
		components.forkliftLeft2.set(0);
		components.forkliftRight2.set(0);
		components.forkliftClamp.set(false);
		components.forkliftClamp2.set(true);
		components.compressor.setClosedLoopControl(true);
	}

	public void teleopInit() {
		this.ForkliftState = STOPPEDMID;
		components.forkliftLeft1.set(0);
		components.forkliftRight1.set(0);
		components.forkliftLeft2.set(0);
		components.forkliftRight2.set(0);
		components.forkliftClamp.set(false);
		components.forkliftClamp2.set(true);
		components.compressor.setClosedLoopControl(true);
		components.compressor.start();
	}

	public void autoInit() {
		this.ForkliftState = STOPPEDMID;
		components.forkliftLeft1.set(0);
		components.forkliftRight1.set(0);
		components.forkliftLeft2.set(0);
		components.forkliftRight2.set(0);
		components.forkliftClamp.set(false);
		components.forkliftClamp2.set(true);
		components.compressor.setClosedLoopControl(true);
		components.compressor.start();
	
	}

	public void disabled() {
		this.ForkliftState = STOPPEDMID;
		components.forkliftLeft1.set(0);
		components.forkliftRight1.set(0);
		components.forkliftLeft2.set(0);
		components.forkliftRight2.set(0);
		components.forkliftClamp.set(false);
		components.forkliftClamp2.set(true);
	}

	public void forkliftPeriodic() {
		System.out.println(ForkliftState);
		
		switch(compressorState){
		case COMPRESSOR_ON:
			if(Joy.getRawButton(7)){
				components.compressor.stop();
				
			}
			break;
		case COMPRESSOR_OFF:
			if(Joy.getRawButton(8))
			break;
		}
		
		switch(forkPosState){
		case OUT:
			if (Joy.getRawButton(Joy.BUTTON_X)) {// CHANGE
				components.forkliftClamp.set(true);
				components.forkliftClamp2.set(false);
				this.forkPosState = IN;
			}
			break;
		case IN:
			if (Joy.getRawButton(Joy.BUTTON_B)) {// CHANGE
				this.forkPosState = OUT;
				components.forkliftClamp.set(false);
				components.forkliftClamp2.set(true); //CHANGE!!!!!!!!!!
			}
			break;
		}
		
		switch (ForkliftState) {
		// SPACE
		case TOP:
			if (Joy.getRawButton(Joy.BUTTON_A)) {
				if (components.forkliftDown.get()) {
					ForkliftState = MOVINGDOWN;
					components.forkliftLeft1.set(movementSpeed);
					components.forkliftLeft2.set(movementSpeed);
					components.forkliftRight1.set(-movementSpeed);
					components.forkliftRight2.set(-movementSpeed);
				}
			}
			if(components.forkliftUp.get()){
				ForkliftState =STOPPEDMID;
			}
			break;
		// SPACE
		case MOVINGDOWN:
			if (!components.forkliftDown.get()) {
				ForkliftState = BOTTOM;
				components.forkliftLeft1.set(0);
				components.forkliftLeft2.set(0);
				components.forkliftRight1.set(0);
				components.forkliftRight2.set(0);
			}
			if (!Joy.getRawButton(Joy.BUTTON_A)) {
				ForkliftState = STOPPEDMID;
				components.forkliftLeft1.set(0);
				components.forkliftLeft2.set(0);
				components.forkliftRight1.set(0);
				components.forkliftRight2.set(0);
			} else if (Joy.getRawButton(Joy.BUTTON_Y)) {
				if (components.forkliftUp.get()) {
					ForkliftState = MOVINGUP;
					components.forkliftLeft1.set(-movementSpeed);
					components.forkliftLeft2.set(-movementSpeed);
					components.forkliftRight1.set(movementSpeed);
					components.forkliftRight2.set(movementSpeed);
				}
			}
			break;
		// SPACE
		case BOTTOM:
			if (Joy.getRawButton(Joy.BUTTON_Y)) {
				if (components.forkliftUp.get()) {
					ForkliftState = MOVINGUP;
					components.forkliftLeft1.set(-movementSpeed);
					components.forkliftLeft2.set(-movementSpeed);
					components.forkliftRight1.set(movementSpeed);
					components.forkliftRight2.set(movementSpeed);
				}
			}
			break;
		// SPACE
		case MOVINGUP:
			if (!components.forkliftUp.get()) {
				ForkliftState = TOP;
				components.forkliftLeft1.set(0);
				components.forkliftLeft2.set(0);
				components.forkliftRight1.set(0);
				components.forkliftRight2.set(0);
			}
			if (Joy.getRawButton(Joy.BUTTON_A)) {
				if (components.forkliftDown.get()) {
					ForkliftState = MOVINGDOWN;
					components.forkliftLeft1.set(movementSpeed);
					components.forkliftLeft2.set(movementSpeed);
					components.forkliftRight1.set(-movementSpeed);
					components.forkliftRight2.set(-movementSpeed);
				}
			}
			if (!Joy.getRawButton(Joy.BUTTON_Y)) {
				ForkliftState = STOPPEDMID;
				components.forkliftLeft1.set(0);
				components.forkliftLeft2.set(0);
				components.forkliftRight1.set(0);
				components.forkliftRight2.set(0);
			}
			break;
		// SPACE
		case STOPPEDMID:
			if (Joy.getRawButton(Joy.BUTTON_Y)) {
				if (components.forkliftUp.get()) {
					ForkliftState = MOVINGUP;
					components.forkliftLeft1.set(-movementSpeed);
					components.forkliftLeft2.set(-movementSpeed);
					components.forkliftRight1.set(movementSpeed);
					components.forkliftRight2.set(movementSpeed);
				}
			}
			if (Joy.getRawButton(Joy.BUTTON_A)) {
				if (components.forkliftDown.get()) {
					ForkliftState = MOVINGDOWN;
					components.forkliftLeft1.set(movementSpeed);
					components.forkliftLeft2.set(movementSpeed);
					components.forkliftRight1.set(-movementSpeed);
					components.forkliftRight2.set(-movementSpeed);
				}
			}
			break;

		}
	}

}