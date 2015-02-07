package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.utils.Utilities;

import org.usfirst.frc.team3044.DriverStation.DriverController;
import org.usfirst.frc.team3044.DriverStation.SecondaryController;
import org.usfirst.frc.team3044.utils.Components;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogInput;

public class Arm {
	SecondaryController ArmJoy = SecondaryController.getInstance();
	boolean screwButtonIn1 = true;
	boolean screwButtonOut1 = true;
	boolean PneumaticsButton1 = true;
	boolean WinchButtonOut1 = true;
	boolean WinchButtonIn1 = true;
	boolean BothButtonIn1 = true;
	boolean BothButtonOut1 = true;

	final int TransportMode = 1;
	final int PreparingforPickUp = 2;
	final int BothPreparedforPickUp = 3;
	final int BothDragging = 4;
	final int StoppedAfterBothDrag = 5;

	final int TransportScrew = 1;
	final int ScrewMovingToPickUp = 2;
	final int ScrewStoppedforPickUp = 3;
	final int ScrewDraging = 4;
	final int ScrewStoppedDrag = 5;

	// final int ArmIN = 1;
	// final int ArmMovingOut = 2;
	// final int ArmMiddle = 3;
	// final int ArmMovingIn = 5;
	// final int ArmOut = 4;

	// final int SliderIn = 6;
	// final int SliderMovingOut = 7;
	// final int SliderMiddle = 8;
	// final int SliderMovingIn = 9;
	// final int SliderOut = 10;

	final int Off = 0;
	final int On = 1;

	int BothState = TransportMode;
	int ScrewState = TransportScrew;

	// int PullState = ArmIN;
	// int SliderState = SliderIn;
	int PneumaticState = Off;

	Components components = Components.getInstance();
	Encoder screwEncoder = components.encoderScrew;
	AnalogInput WinchPot = components.encoderWinch;

	public void robotInit() {

	}

	public void teleopInit() {

	}

	public void autoInit() {

	}

	public void disabled() {

	}

	public void armPeriodic() {
		screwButtonIn1 = ArmJoy.getRawButton(components.SCREW_IN_BUTTON);
		screwButtonOut1 = ArmJoy.getRawButton(components.SCREW_OUT_BUTTON);
		WinchButtonIn1 = ArmJoy.getRawButton(components.SCREW_IN_BUTTON);
		WinchButtonOut1 = ArmJoy.getRawButton(components.SCREW_OUT_BUTTON);
		BothButtonIn1 = ArmJoy.getRawButton(components.BOTH_IN_UP_BUTTON);
		BothButtonOut1 = ArmJoy.getRawButton(components.BOTH_OUT_DOWN_BUTTON);
		
		double voltX = 0;
		double voltY = 1;						// need real voltage value
		double posX = 1;
		double posY = 2;
		double posZ = 3;
		
		boolean ScrewButton1 = true;
		// SliderButtonIn1 = ArmJoy.getRawButton(components.ARM_IN_BUTTON);
		int BothState = TransportMode;
		int ScrewState = TransportScrew;

		switch (BothState) {
		case TransportMode: // ArmExtended is Winch LimitSwitch
			if (BothButtonOut1 == true) {
				if (!components.armScrewOut.get()) {
					if (!components.ArmExtended.get()) { // add winch limit
															// switch
						if (!(screwEncoder.getDistance() == posX)) {
							components.screwMotor.set(1);

						}
						if (!(WinchPot.getVoltage() == voltX)) {
							components.winchMotor.set(-1);
							BothState = PreparingforPickUp;
						}

					}

				}
			}
			break;
		case PreparingforPickUp:
			if (!components.ArmExtended.get()) {
				if (!components.armScrewOut.get()) {
					if (screwEncoder.getDistance() == posX) {
						components.screwMotor.set(0);
						ScrewState = ScrewStoppedforPickUp;
					}
					if (WinchPot.getVoltage() >= voltX) {
						components.winchMotor.set(0);
						BothState = BothPreparedforPickUp;
					}
				}
			}

			break;

		case BothPreparedforPickUp:
			if (screwButtonIn1 == true) {   								//ThisButtonIsToMakeTheScrewIn and ThePullthewinch
				if (!components.ArmExtended.get()) {
					if (!(screwEncoder.getDistance() == posY)) {
						components.screwMotor.set(-1);
						
					}if (!((WinchPot.getVoltage() == voltY))){
						components.winchMotor.set(1);
						BothState = BothDragging;
					}

				}

			}
			break;

		case BothDragging:
			if (!components.ArmExtended.get()) {
				if (screwEncoder.getDistance() == posY) {				//PosY is the screws position when coming back
					components.screwMotor.set(0);
				}
				if ((WinchPot.getVoltage()== voltY)) {
					components.winchMotor.set(0);
					BothState = StoppedAfterBothDrag;
					
				}
			}
			break;
		case StoppedAfterBothDrag:
			if (BothButtonOut1 == true) {
			
			
			if (screwButtonOut1 == true) {
				if (!(screwEncoder.getDistance() == posZ)) {
					components.screwMotor.set(1);

				}//
			}

		}
	}
}
}
/*
 * public void armPeriodic() {
 * 
 * ArmButtonIn1 = ArmJoy.getRawButton(components.ARM_IN_BUTTON); ArmButtonOut1 =
 * ArmJoy.getRawButton(components.ARM_OUT_BUTTON); SliderButtonIn1 =
 * ArmJoy.getRawButton(components.ARM_IN_BUTTON);// Please // Create // Slider
 * 
 * SliderButtonOut1 = ArmJoy.getRawButton(components.ARM_OUT_BUTTON);
 * PneumaticsButton1 = ArmJoy.getRawButton(components.PNEUMATIC_BUTTON); int
 * PullState = ArmIN;
 * 
 * switch (PullState) {
 * 
 * case ArmIN: if (ArmButtonOut1 == true) { if (!components.ArmExtended.get()) {
 * components.armMotor.set(1); PullState = ArmMovingOut; } }
 * 
 * break; case ArmOut: if (ArmButtonIn1 == true) { if
 * (!components.ArmRetracted.get()) { components.armMotor.set(-1); PullState =
 * ArmMovingIn; } } break;
 * 
 * case ArmMovingOut: if (ArmButtonOut1 == false) { if
 * (!components.ArmExtended.get()) { components.armMotor.set(0); PullState =
 * ArmMiddle; } } break;
 * 
 * case ArmMovingIn: if (ArmButtonIn1 == false) { if
 * (!components.ArmRetracted.get()) { components.armMotor.set(0); PullState =
 * ArmMiddle; } } break;
 * 
 * case ArmMiddle: if (ArmButtonOut1 == true) { if
 * (!components.ArmExtended.get()) { components.armMotor.set(1); PullState =
 * ArmMovingOut; } else if (ArmButtonIn1 = true) { if
 * (!components.ArmRetracted.get()) { components.armMotor.set(-1); PullState =
 * ArmMovingIn; }
 * 
 * } }
 * 
 * break; }
 * 
 * switch (PneumaticState) { case Off: if (PneumaticsButton1 = true) {
 * pneumaticHook.set(true); PneumaticState = On; } break;
 * 
 * case On: if (PneumaticsButton1 = false) { pneumaticHook.set(false);
 * PneumaticState = Off; } break; } switch (SliderState) {
 * 
 * case SliderIn: if (SliderButtonOut1 = true) { if
 * (!components.ArmSliderOut.get()) { components.sliderMotor.set(1); SliderState
 * = SliderMovingOut; } } break; case SliderMovingOut: if (SliderButtonOut1 =
 * false) { if (!components.ArmSliderIn.get()) { components.sliderMotor.set(0);
 * SliderState = SliderMiddle; } } break;
 * 
 * case SliderMiddle: if (SliderButtonIn1 = true) { if
 * (!components.ArmSliderIn.get()) { components.sliderMotor.set(-1); SliderState
 * = SliderMovingIn; } else if (SliderButtonOut1 = true) { if
 * (!components.ArmSliderOut.get()) {
 * 
 * 
 * } } } break;
 * 
 * case SliderMovingIn: if (SliderButtonIn1 = false) { if
 * (!components.ArmSliderIn.get()) { components.sliderMotor.set(0); SliderState
 * = SliderMiddle; } } break;
 * 
 * 
 * case SliderOut: if (SliderButtonIn1 = true) { if
 * (!components.ArmSliderIn.get()) { components.sliderMotor.set(-1); SliderState
 * = SliderMovingIn; } } break; }
 * 
 * }
 * 
 * }
 * 
 * 
 * // change name to // screwExtended// // set screw// // distances
 */

