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
boolean PneumaticsButton1 = true;
final int IN= 1; 
final int MovingOut =2;
final int Middle = 3;
final int Out =4;
final int MovingIn=5;
int ArmState = IN;

	public void robotInit(){
		
	}
	
	public void teleopInit(){
	}	
	public void autoInit(){
	}
	public void disabled(){
		
	
	}
	public void armPeriodic(){
		
		ArmButtonIn1 = ArmJoy.getRawButton(1);
		ArmButtonOut1 = ArmJoy.getRawButton(Components.ARM_OUT);
		PneumaticsButton1 = ArmJoy.getRawButton(2);
		int ArmState = Components.ARM_IN;
		
	switch(ArmState){
    	
	case IN:
    		if (ArmButtonOut1 = true){
    			if(!Components.ArmExtended.get()){
    				Components.armMotor.set(1);
    				ArmState = MovingOut;
       			}
    		}
    	case Out:
    		if (ArmButtonIn1 = true){
    			if(!Components.ArmRetracted.get()){
    				Components.armMotor.set(-1);
    				ArmState = MovingIn;
       			}
    		}
    	case MovingOut:
    		if (ArmButtonOut1 = false){
    			if(!Components.ArmExtended.get()){
    				Components.armMotor.set(0);
    				ArmState = Middle;
    			}
    		}
    	case MovingIn:
    		if (ArmButtonIn1 = false){
    			if(!Components.ArmRetracted.get()){
    				Components.armMotor.set(0);
    				ArmState = Middle;
    			}
    		}
    	case Middle:
    		if (ArmButtonOut1 = true){
    			if(!Components.ArmExtended.get()){
    				Components.armMotor.set(1);
    				ArmState = MovingOut;
    		}else if (ArmButtonIn1 = true){
    			if(!Components.ArmRetracted.get()){
    				Components.armMotor.set(-1);
    				ArmState = MovingIn;
    				
    			
    			}
    			}
    		}
	}
	}
	

}