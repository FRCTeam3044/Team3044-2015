package org.usfirst.frc.team3044.DriverStation;

import edu.wpi.first.wpilibj.Joystick;

public class SecondaryController {
	private static SecondaryController instance = null;
	
	private Joystick secondaryJoy;
	
	double leftX, leftY, rightX, rightY, trigger;
	boolean xButton, yButton, bButton, aButton;
	boolean dPadUp, dPadDown, DPadLeft, dPadRight; // Check to make sure d pad isnt analog
	
	
	//Do we want to do this for filtering inputs?
	private SecondaryController(){
		secondaryJoy = new Joystick(0);
	}
	
	public static SecondaryController getInstance(){
		if(instance == null){
			instance = new SecondaryController();
		}
		
		return instance;
	}
	

	public double getLeftX() {
		return secondaryJoy.getRawAxis(1);
	}

	public double getLeftY() {
		return leftY;
	}

	public double getRightX() {
		return rightX;
	}

	public double getRightY() {
		return rightY;
	}

	public double getTrigger() {
		return trigger;
	}

	public boolean getRawButton(int num){
		return false;
	}
}
