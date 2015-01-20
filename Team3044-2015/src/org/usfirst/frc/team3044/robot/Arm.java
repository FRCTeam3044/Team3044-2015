package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.DriverStation.DriverController;
import org.usfirst.frc.team3044.utils.Components;

import edu.wpi.first.wpilibj.Jaguar;

public class Arm {
	//DriverController controller = DriverController.getInstance();
	
	public void robotInit(){
		
	}
	
	public void teleopInit(){
		
	}
	
	public void autoInit(){
		
	}
	
	public void disabled(){
		
	}
	
	public void armPeriodic(){
		Components.armMotor.set(3);
		controller.getRawButton(Components.ARM_OUT);
		///This is another test change
		
	}

}
