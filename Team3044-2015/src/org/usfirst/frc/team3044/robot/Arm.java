package org.usfirst.frc.team3044.robot;


import org.usfirst.frc.team3044.utils.Utilities;

import org.usfirst.frc.team3044.DriverStation.DriverController;
import org.usfirst.frc.team3044.DriverStation.SecondaryController;
import org.usfirst.frc.team3044.utils.Components;

import edu.wpi.first.wpilibj.Jaguar;

public class Arm {
SecondaryController ArmJoy = SecondaryController.getInstance();
boolean ArmButtonIn1 = true;
boolean ArmButtonOut1 = true;
final int IN= 1;
final int MovingOut=2;
final int Middle= 3;
final int Out=4;
final int MovingIn=5;
int ArmState = IN;
	public void robotInit(){
		
	}
	
	public void teleopInit(){
		ArmButtonIn1 = ArmJoy.getRawButton(1);
		ArmButtonOut1 = ArmJoy.getRawButton(Components.ARM_OUT);
		int ArmState = Components.ARM_IN;
		
	switch(ArmState){
    	case IN:
    		if (ArmButtonOut1 = true){
    			if(!Components.ArmExtended.get()){
    				Components.armMotor.set(1);
    				ArmState = Components.ArmMovingOut;
       			}
    		}
    	case Components.ARM_OUT:
    		if (ArmButtonIn1 = true){
    			if(!Components.ArmRetracted.get()){
    				Components.armMotor.set(-1);
    				ArmState = Components.ArmMovingIN;
       			}
    		}
    	case Components.ArmMovingOut:
    		if (ArmButtonOut1 = false){
    			if(!Components.ArmExtended.get()){
    				Components.armMotor.set(0);
    				ArmState = Components.ArmMiddle;
    			}
    		}
    	case Components.ArmMovingIN:
    		if (ArmButtonIn1 = false){
    			if(!Outlimit.get()){
    				ArmJag.set(0);
    				ArmState = Middle;
    			}
    		}
    	case Middle:
    		if (button1 = true){
    			if(!Outlimit.get()){
    				ArmJag.set(1);
    				ArmState = MovingOut;
    		}else if (button2 = true){
    			if(!Inlimit.get()){
    				ArmJag.set(-1);
    				ArmState = MovingIn;
    				}
    			}
    		}
		
		
		
		
		
	}
	
	public void autoInit(){
		
	}
	
	public void disabled(){
		
	}
	
	public void armPeriodic(){
	}

}
