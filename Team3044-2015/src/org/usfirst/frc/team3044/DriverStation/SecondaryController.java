package org.usfirst.frc.team3044.DriverStation;

import edu.wpi.first.wpilibj.Joystick;

public class SecondaryController {
	private static SecondaryController instance = null;
	
	private Joystick secondaryJoy;
	
	public int BUTTON_X = 2;
	public int BUTTON_Y = 3;
	public int BUTTON_B = 1;
	public int BUTTON_A = 0;
	
	public int BUTTON_RT = 5;
	public int BUTTON_LT = 4;
	
	
	//Do we want to do this for filtering inputs?
	private SecondaryController(){
		secondaryJoy = new Joystick(1);
	}
	
	public static SecondaryController getInstance(){
		if(instance == null){
			instance = new SecondaryController();
		}
		
		return instance;
	}
	

	public double getLeftX() {
		return secondaryJoy.getRawAxis(0);
	}

	public double getLeftY() {
		return secondaryJoy.getRawAxis(1);
	}

	public double getRightX() {
		return secondaryJoy.getRawAxis(4);
	}

	public double getRightY() {
		return secondaryJoy.getRawAxis(5);
	}

	public double getTriggerRight() {
		return secondaryJoy.getRawAxis(3);
	}
	
	public double getTriggerLeft(){
		return secondaryJoy.getRawAxis(2);
	}


	public boolean getRawButton(int num){
		return false;
	}
}
