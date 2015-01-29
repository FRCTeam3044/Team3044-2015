package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.utils.Utilities;

import org.usfirst.frc.team3044.DriverStation.DriverController;
import org.usfirst.frc.team3044.DriverStation.SecondaryController;
import org.usfirst.frc.team3044.utils.Components;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;

public class Arm {
	SecondaryController ArmJoy = SecondaryController.getInstance();
	boolean ArmButtonIn1 = true;
	boolean ArmButtonOut1 = true;
	boolean PneumaticsButton1 = true;
	final int IN = 1;
	final int MovingOut = 2;
	final int Middle = 3;
	final int Out = 4;
	final int MovingIn = 5;
	final int Off=0;
	final int On=1;
	int ArmState = IN;
	int PneumaticState = Off;
	Components components = Components.getInstance();
	Solenoid pneumaticHook = components.armSolenoid;

	public void robotInit() {
		ArmState = IN;
		PneumaticState = Off;
		components.armMotor.set(0);
		pneumaticHook.set(false);
	}

	public void teleopInit() {
	}

	public void autoInit() {
	}

	public void disabled() {
		components.armMotor.set(0);
		pneumaticHook.set(false);
		
	}

	public void armPeriodic() {

		ArmButtonIn1 = ArmJoy.getRawButton(components.ARM_IN_BUTTON);
		ArmButtonOut1 = ArmJoy.getRawButton(components.ARM_OUT_BUTTON);
		PneumaticsButton1 = ArmJoy.getRawButton(components.PNEUMATIC_BUTTON);
		int ArmState = IN;

		switch (ArmState) {

		case IN:
			if (ArmButtonOut1 = true) {
				if (!components.ArmExtended.get()) {
					components.armMotor.set(1);
					ArmState = MovingOut;
				}
			}
			if (PneumaticsButton1 = true) {
				PneumaticState =  On;
			}
		break;
		case Out:
			if (ArmButtonIn1 = true) {
				if (!components.ArmRetracted.get()) {
					components.armMotor.set(-1);
					ArmState = MovingIn;
				}
			}
			if (PneumaticsButton1 = true) {
				PneumaticState =  On;
			}
		break;
		
		
		case MovingOut:
			if (ArmButtonOut1 = false) {
				if (!components.ArmExtended.get()) {
					components.armMotor.set(0);
					ArmState = Middle;
				}
			}
		break;
		
		
		case MovingIn:
			if (ArmButtonIn1 = false) {
				if (!components.ArmRetracted.get()) {
					components.armMotor.set(0);
					ArmState = Middle;
				}
			}
		break;
		
		
		case Middle:
			if (ArmButtonOut1 = true) {
				if (!components.ArmExtended.get()) {
					components.armMotor.set(1);
					ArmState = MovingOut;
				} else if (ArmButtonIn1 = true) {
					if (!components.ArmRetracted.get()) {
						components.armMotor.set(-1);
						ArmState = MovingIn;
					}

				}
			}
			if (PneumaticsButton1 = true) {
				PneumaticState =  On;
			}
			break;
		}
		switch (PneumaticState){
		case Off:
			if (PneumaticsButton1=true){
				pneumaticHook.set(true);
				PneumaticState=On;
			}
			break;
			
		case On:
			if (PneumaticsButton1=false){
				pneumaticHook.set(false);
				PneumaticState=Off;
			}
			break;
		}
			
	}
    }
