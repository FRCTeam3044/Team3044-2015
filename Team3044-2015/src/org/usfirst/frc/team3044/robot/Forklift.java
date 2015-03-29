package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.DriverStation.DriverController;
import org.usfirst.frc.team3044.DriverStation.SecondaryController;
import org.usfirst.frc.team3044.utils.Components;
//USING JAGUAR LEFT1 AND RIGHT1 FOR UP AND DOWN
//DigitalInput upperlimit = new DigitalInput(1); //CHECK ACTUAL POSITION
//DigitalInput lowerlimit = new DigitalInput(2); //CHECK ACTUAL POSITION
//WHEN ALL ELSE IS DONE, CHECK ELECTRONIC POSITIONS


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Forklift {

	SecondaryController Joy = SecondaryController.getInstance();
	DriverController dc = DriverController.getInstance();
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

	double movementSpeed = -.6;
	
	double movementSpeedDown = -.45;
	private boolean secController = false;
	
	public void robotInit() {
		this.ForkliftState = STOPPEDMID;
		components.forkliftLeft1.set(0);
		components.forkliftRight1.set(0);
		components.forkliftLeft2.set(0);
		components.forkliftRight2.set(0);
//		components.forkliftClamp.set(false);
//		components.forkliftClamp2.set(true);
//		components.compressor.setClosedLoopControl(true);
	}

	public void teleopInit() {
		this.ForkliftState = STOPPEDMID;
		components.forkliftLeft1.set(0);
		components.forkliftRight1.set(0);
		components.forkliftLeft2.set(0);
		components.forkliftRight2.set(0);
//		components.forkliftClamp.set(false);
//		components.forkliftClamp2.set(true);
//		components.compressor.setClosedLoopControl(true);
//		components.compressor.start();
	}

	public void autoInit() {
		this.ForkliftState = STOPPEDMID;
		components.forkliftLeft1.set(0);
		components.forkliftRight1.set(0);
		components.forkliftLeft2.set(0);
		components.forkliftRight2.set(0);
//		components.forkliftClamp.set(false);
//		components.forkliftClamp2.set(true);
//		components.compressor.setClosedLoopControl(true);
//		components.compressor.start();
	
	}

	public void disabled() {
		this.ForkliftState = STOPPEDMID;
		components.forkliftLeft1.set(0);
		components.forkliftRight1.set(0);
		components.forkliftLeft2.set(0);
		components.forkliftRight2.set(0);
//		components.forkliftClamp.set(false);
//		components.forkliftClamp2.set(true);
	}
	public void Init(){
		ForkliftState = 3;
		forkPosState = OUT;
	}
	public void forkliftPeriodic() {
		
		/*
		switch(compressorState){
		case COMPRESSOR_ON:
			if(Joy.getRawButton(7)){
				components.compressor.stop();
				
			}
			break;
		case COMPRESSOR_OFF:
			if(Joy.getRawButton(8))
			break;
		}*/
		
		/*
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
		*/
		
		//this.movementSpeed = -Joy.getTriggerRight();
		//this.movementSpeed = -SmartDashboard.getNumber("DB/Slider 3");
		if(Joy.getRawButton(Joy.BUTTON_Y) || Joy.getRawButton(Joy.BUTTON_A)){
			secController = true;
		}else{
			secController = false;
		}
		if(Joy.getRawButton(Joy.BUTTON_X)){
			this.movementSpeed = -.55;
		} else if(Joy.getRawButton(Joy.BUTTON_B)){
			this.movementSpeed = -.4;
		}
		switch (ForkliftState) {
		// SPACE
		case TOP:
			if (dc.getRawButton(dc.BUTTON_LT) || Joy.getRawButton(Joy.BUTTON_A)) {
				if (components.forkliftDown.get()) {
					ForkliftState = MOVINGDOWN;
					components.forkliftLeft1.set(movementSpeedDown);
					components.forkliftLeft2.set(movementSpeedDown);
					components.forkliftRight1.set(-movementSpeedDown);
					components.forkliftRight2.set(-movementSpeedDown);
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
			if (!dc.getRawButton(dc.BUTTON_LT)  && !this.secController) {
				ForkliftState = STOPPEDMID;
				components.forkliftLeft1.set(0);
				components.forkliftLeft2.set(0);
				components.forkliftRight1.set(0);
				components.forkliftRight2.set(0);
			} else if (dc.getRawButton(dc.BUTTON_RT) || Joy.getRawButton(Joy.BUTTON_Y)) {
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
			if (dc.getRawButton(dc.BUTTON_RT) || Joy.getRawButton(Joy.BUTTON_Y)) {
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
			if (dc.getRawButton(dc.BUTTON_LT) || Joy.getRawButton(Joy.BUTTON_A)) {
				if (components.forkliftDown.get()) {
					ForkliftState = MOVINGDOWN;
					components.forkliftLeft1.set(movementSpeedDown);
					components.forkliftLeft2.set(movementSpeedDown);
					components.forkliftRight1.set(-movementSpeedDown);
					components.forkliftRight2.set(-movementSpeedDown);
				}
			}
			if (!dc.getRawButton(dc.BUTTON_RT) && !this.secController) {
				ForkliftState = STOPPEDMID;
				components.forkliftLeft1.set(0);
				components.forkliftLeft2.set(0);
				components.forkliftRight1.set(0);
				components.forkliftRight2.set(0);
			}
			if(dc.getRawButton(7) && components.forkliftTote.get()){
				ForkliftState = STOPPEDMID;
				components.forkliftLeft1.set(0);
				components.forkliftLeft2.set(0);
				components.forkliftRight1.set(0);
				components.forkliftRight2.set(0);
			}
			break;
		// SPACE
		case STOPPEDMID:
			if (dc.getRawButton(dc.BUTTON_RT) || Joy.getRawButton(Joy.BUTTON_Y)) {
				if (components.forkliftUp.get()) {
					ForkliftState = MOVINGUP;
					components.forkliftLeft1.set(-movementSpeed);
					components.forkliftLeft2.set(-movementSpeed);
					components.forkliftRight1.set(movementSpeed);
					components.forkliftRight2.set(movementSpeed);
				}
			}
			if (dc.getRawButton(dc.BUTTON_LT) || Joy.getRawButton(Joy.BUTTON_A)) {
				if (components.forkliftDown.get()) {
					ForkliftState = MOVINGDOWN;
					components.forkliftLeft1.set(movementSpeedDown);
					components.forkliftLeft2.set(movementSpeedDown);
					components.forkliftRight1.set(-movementSpeedDown);
					components.forkliftRight2.set(-movementSpeedDown);
				}
			}
			break;

		}
	}

}