package org.usfirst.frc.team3044.DriverStation;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public class SecondaryController {
	private static SecondaryController instance = null;
	
	private Joystick secondaryJoy;

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
		if(num < 1){
			System.out.println("Secondary Controller! Buttons start at 1");
			num = 20;
		}
		if(!DriverStation.getInstance().isAutonomous()){
			
			return secondaryJoy.getRawButton(num);
			
		}else{
			switch(num){
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
}
