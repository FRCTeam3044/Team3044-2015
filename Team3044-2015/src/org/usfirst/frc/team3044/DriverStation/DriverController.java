package org.usfirst.frc.team3044.DriverStation;

import edu.wpi.first.wpilibj.Joystick;

//*Written Assuming xbox controllers

public class DriverController {
	private static DriverController instance = null;
	
	private Joystick driverJoy;
	
	public int BUTTON_X = 3;
	public int BUTTON_Y = 4;
	public int BUTTON_B = 2;
	public int BUTTON_A = 1;
	
	public int BUTTON_RT = 6;
	public int BUTTON_LT = 5;
	
	
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
		return driverJoy.getRawAxis(0);
	}

	public double getLeftY() {
		return driverJoy.getRawAxis(1);
	}

	public double getRightX() {
		return driverJoy.getRawAxis(4);
	}

	public double getRightY() {
		return driverJoy.getRawAxis(5);
	}

	public double getTriggerRight() {
		return driverJoy.getRawAxis(3);
	}
	
	public double getTriggerLeft(){
		return driverJoy.getRawAxis(2);
	}

	public boolean getRawButton(int num){
		return driverJoy.getRawButton(num);
	}
}
//