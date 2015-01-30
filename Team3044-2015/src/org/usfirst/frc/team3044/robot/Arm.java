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

	final int IN = 1;
	final int MovingOut = 2;
	final int Middle = 3;
	final int MovingIn = 5;
	final int Out = 4;

	final int SliderIn = 6;
	final int SliderMovingOut = 7;
	final int SliderMiddle = 8;
	final int SliderMovingIn = 9;
	final int SliderOut = 10;

	final int Off = 0;
	final int On = 1;

	int PullState = IN;
	int SliderState = SliderIn;
	int PneumaticState = Off;

	Components components = Components.getInstance();
	Solenoid pneumaticHook = components.armSolenoid;

	public void robotInit() {
		PullState = IN;
		PneumaticState = Off;
		components.armMotor.set(0);
		pneumaticHook.set(false);
	}

	public void teleopInit() {
		PullState = IN;
		PneumaticState = Off;
		components.armMotor.set(0);
		pneumaticHook.set(false);
	}

	public void autoInit() {
		PullState = IN;
		PneumaticState = Off;
		components.armMotor.set(0);
		pneumaticHook.set(false);
	}

	public void disabled() {
		PullState = IN;
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
																		// Buttons
		SliderButtonOut1 = ArmJoy.getRawButton(components.ARM_OUT_BUTTON);
		PneumaticsButton1 = ArmJoy.getRawButton(components.PNEUMATIC_BUTTON);
		int PullState = IN;

		switch (PullState) {

		case IN:
			if (ArmButtonOut1 == true) {
				if (!components.ArmExtended.get()) {
					components.armMotor.set(1);
					PullState = MovingOut;
				}
			}

			break;
		case Out:
			if (ArmButtonIn1 == true) {
				if (!components.ArmRetracted.get()) {
					components.armMotor.set(-1);
					PullState = MovingIn;
				}
			}
			break;

		case MovingOut:
			if (ArmButtonOut1 == false) {
				if (!components.ArmExtended.get()) {
					components.armMotor.set(0);
					PullState = Middle;
				}
			}
			break;

		case MovingIn:
			if (ArmButtonIn1 == false) {
				if (!components.ArmRetracted.get()) {
					components.armMotor.set(0);
					PullState = Middle;
				}
			}
			break;

		case Middle:
			if (ArmButtonOut1 == true) {
				if (!components.ArmExtended.get()) {
					components.armMotor.set(1);
					PullState = MovingOut;
				} else if (ArmButtonIn1 = true) {
					if (!components.ArmRetracted.get()) {
						components.armMotor.set(-1);
						PullState = MovingIn;
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

		}

	}

}
