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
	boolean SliderButtonOut1 = true;
	boolean SliderButtonIn1 = true;

	final int ArmIN = 1;
	final int ArmMovingOut = 2;
	final int ArmMiddle = 3;
	final int ArmMovingIn = 5;
	final int ArmOut = 4;

	final int SliderIn = 6;
	final int SliderMovingOut = 7;
	final int SliderMiddle = 8;
	final int SliderMovingIn = 9;
	final int SliderOut = 10;

	Encoder SliderEncoder = components.encoderArm;
	double SlideEncoder;
	
	final int Off = 0;
	final int On = 1;
	

	int PullState = ArmIN;
	int SliderState = SliderIn;
	int PneumaticState = Off;

	Components components = Components.getInstance();
	Solenoid pneumaticHook = components.armSolenoid;

	public void robotInit() {
		PullState = ArmIN;
		PneumaticState = Off;
		components.armMotor.set(0);
		pneumaticHook.set(false);
	}

	public void teleopInit() {
		PullState = ArmIN;
		PneumaticState = Off;
		components.armMotor.set(0);
		pneumaticHook.set(false);
	}

	public void autoInit() {
		PullState = ArmIN;
		PneumaticState = Off;
		components.armMotor.set(0);
		pneumaticHook.set(false);
	}

	public void disabled() {
		PullState = ArmIN;
		PneumaticState = Off;
		components.armMotor.set(0);
		pneumaticHook.set(false);

	}

	public void armPeriodic() {

		ArmButtonIn1 = ArmJoy.getRawButton(components.ARM_IN_BUTTON);
		ArmButtonOut1 = ArmJoy.getRawButton(components.ARM_OUT_BUTTON);
		SliderButtonIn1 = ArmJoy.getRawButton(components.ARM_IN_BUTTON);// Please
																		// Create
																		// Slider
			
		SliderButtonOut1 = ArmJoy.getRawButton(components.ARM_OUT_BUTTON);
		PneumaticsButton1 = ArmJoy.getRawButton(components.PNEUMATIC_BUTTON);
		int PullState = ArmIN;

		switch (PullState) {

		case ArmIN:
			if (ArmButtonOut1 == true) {
				if (!components.ArmExtended.get()) {
					components.armMotor.set(1);
					PullState = ArmMovingOut;
				}
			}

			break;
		case ArmOut:
			if (ArmButtonIn1 == true) {
				if (!components.ArmRetracted.get()) {
					components.armMotor.set(-1);
					PullState = ArmMovingIn;
				}
			}
			break;

		case ArmMovingOut:
			if (ArmButtonOut1 == false) {
				if (!components.ArmExtended.get()) {
					components.armMotor.set(0);
					PullState = ArmMiddle;
				}
			}
			break;

		case ArmMovingIn:
			if (ArmButtonIn1 == false) {
				if (!components.ArmRetracted.get()) {
					components.armMotor.set(0);
					PullState = ArmMiddle;
				}
			}
			break;

		case ArmMiddle:
			if (ArmButtonOut1 == true) {
				if (!components.ArmExtended.get()) {
					components.armMotor.set(1);
					PullState = ArmMovingOut;
				} else if (ArmButtonIn1 = true) {
					if (!components.ArmRetracted.get()) {
						components.armMotor.set(-1);
						PullState = ArmMovingIn;
					}

				}
			}

			break;
		}

		switch (PneumaticState) {
		case Off:
			if (PneumaticsButton1 = true) {
				pneumaticHook.set(true);
				PneumaticState = On;
			}
			break;

		case On:
			if (PneumaticsButton1 = false) {
				pneumaticHook.set(false);
				PneumaticState = Off;
			}
			break;
		}
		switch (SliderState) {

		case SliderIn:
			if (SliderButtonOut1 = true) {
				if (!components.ArmSliderOut.get()) {
					components.sliderMotor.set(1);
					SliderState = SliderMovingOut;
				}
			}
			break;
		case SliderMovingOut:
			if (SliderButtonOut1 = false) {
				if (!components.ArmSliderIn.get()) {
					components.sliderMotor.set(0);
					SliderState = SliderMiddle;
				}
			}
			break;

		case SliderMiddle:
			if (SliderButtonIn1 = true) {
				if (!components.ArmSliderIn.get()) {
					components.sliderMotor.set(-1);
					SliderState = SliderMovingIn;
				} else if (SliderButtonOut1 = true) {
					if (!components.ArmSliderOut.get()) {

						
					}
				}
			}
			break;
		
		case SliderMovingIn:
			if (SliderButtonIn1 = false) {
				if (!components.ArmSliderIn.get()) {
					components.sliderMotor.set(0);
					SliderState = SliderMiddle;
				}
			}
			break;
			
			
		case SliderOut:
			if (SliderButtonIn1 = true) {
				if (!components.ArmSliderIn.get()) {
					components.sliderMotor.set(-1);
					SliderState = SliderMovingIn;
				}
			}
			break;
		}

	}

}
