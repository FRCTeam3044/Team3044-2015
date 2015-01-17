package org.usfirst.frc.team3044.DriverStation;

import edu.wpi.first.wpilibj.Joystick;

//*Written Assuming xbox controllers

public class DriverController {
	private static DriverController instance = null;
	
	private Joystick driverJoy;
	
	//These are all temporary until i get the actuall button mappings
	//Then we could use joystick.getX() in functions?
	double leftX, leftY, rightX, rightY, trigger;
	boolean xButton, yButton, bButton, aButton;
	boolean dPadUp, dPadDown, DPadLeft, dPadRight; // Check to make sure d pad isnt analog
	
	//Do we want to do this for filtering inputs?
	private DriverController(){
		driverJoy = new Joystick(0);
	}
	
	public static DriverController getInstance(){
		if(instance == null){
			instance = new DriverController();
		}
		
		return instance;
	}

	public double getLeftX() {
		return leftX;
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

	public boolean isxButton() {
		return xButton;
	}

	public boolean isyButton() {
		return yButton;
	}

	public boolean isbButton() {
		return bButton;
	}

	public boolean isaButton() {
		return aButton;
	}

	public boolean isdPadUp() {
		return dPadUp;
	}

	public boolean isdPadDown() {
		return dPadDown;
	}

	public boolean isDPadLeft() {
		return DPadLeft;
	}

	public boolean isdPadRight() {
		return dPadRight;
	}
	

	
	
	

}
