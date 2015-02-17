package org.usfirst.frc.team3044.DriverStation;

import edu.wpi.first.wpilibj.DriverStation;
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

	public boolean buttonOne = false;
	public boolean buttonTwo = false;
	public boolean buttonThree = false;
	public boolean buttonFour = false;
	public boolean buttonFive = false;
	public boolean buttonSix = false;
	public boolean buttonSeven = false;
	public boolean buttonEight = false;
	public boolean buttonNine = false;
	public boolean buttonTen = false;
	public boolean buttonEleven = false;

	private DriverController() {
		driverJoy = new Joystick(0);
	}

	public static DriverController getInstance() {
		if (instance == null) {
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

	public double getTriggerLeft() {
		return driverJoy.getRawAxis(2);
	}

	public boolean getRawButton(int num) {
		if (!DriverStation.getInstance().isAutonomous()) {
			return driverJoy.getRawButton(num);
			
		}
		switch (num) {
		default:
			return false;
		case 1:
			return buttonOne;
		case 2:
			return buttonTwo;
		case 3:
			return buttonThree;
		case 4:
			return buttonFour;
		case 5:
			return buttonFive;
		case 6:
			return buttonSix;
		case 7:
			return buttonSeven;
		case 8:
			return buttonEight;
		case 9:
			return buttonNine;
		case 10:
			return buttonTen;
		case 11:
			return buttonEleven;
		}
	}
}
//