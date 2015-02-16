package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.DriverStation.SecondaryController;
import org.usfirst.frc.team3044.utils.Components;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {
	SecondaryController ArmJoy = SecondaryController.getInstance();

	// BUTTONS

	boolean ButtonBothInUp = true;
	boolean ButtonBothOutDown = true;
	boolean ButtonZ = true;
	boolean ButtonA = true;
	boolean ButtonB = true;

	// FOR AUTO

	final int TransportMode = 1;
	final int PreparingWinchforPickUp = 2;
	final int PreparingScrewforPickUp = 3;
	final int PreparingBothforPickUp = 4;
	final int BothPreparedforPickUp = 5;
	final int PickUp = 6;
	final int DragWinch = 7;
	final int StoppedAfterDragging = 8;
	final int MovingScrewBack = 9;
	final int MovingWinchBack = 10;
	final int BothMovingBack = 11;

	// FOR TELEOP
	final int ScrewTransport = 1;
	final int ScrewMovingOut = 2;
	final int ScrewStopped = 3;
	final int ScrewMovingIn = 4;

	final int WinchTransport = 1;
	final int WinchMovingDown = 2;
	final int WinchStopped = 3;
	final int WinchMovingUp = 4;

	int WinchState = WinchTransport;
	int ScrewState = ScrewTransport;

	final double WINCH_SPEED = .25;
	final double SCREW_SPEED = .5;

	int BothState = TransportMode;
	
	SecondaryController joy = SecondaryController.getInstance();

	Components components = Components.getInstance();
	Encoder screwEncoder = components.encoderScrew;
	AnalogInput WinchPot = components.encoderWinch;
	DigitalInput screwLimitSwitch = components.armScrewOut;

	public void robotInit() {

		BothState = TransportMode;
		components.screwMotor.set(0);
		components.winchMotor.set(0);
	}

	public void teleopInit() {
		BothState = TransportMode;
		components.screwMotor.set(0);
		components.winchMotor.set(0);

	}

	public void autoInit() {
		// create a condition to check state where we left off so we can
		// continue
		BothState = TransportMode;
		components.screwMotor.set(0);
		components.winchMotor.set(0);
	}

	public void teleopPeriodic() {
		ButtonBothOutDown = ArmJoy.getRawButton(components.SCREW_OUT_BUTTON);
		ButtonBothInUp = ArmJoy.getRawButton(components.SCREW_IN_BUTTON);
		ButtonA = ArmJoy.getRawButton(components.WINCH_UP_BUTTON);
		ButtonB = ArmJoy.getRawButton(components.WINCH_DOWN_BUTTON);
//
//		switch (WinchState) {
//		case WinchTransport:
//			if (!components.ArmExtended.get()) {
//				if (ButtonB == true) {
//					components.winchMotor.set(-WINCH_SPEED);
//					WinchState = WinchMovingDown;
//				}
//			}
//			break;
//		case WinchMovingDown:
//			if (components.ArmExtended.get() || ButtonB == false) {
//				components.winchMotor.set(0);
//				WinchState = WinchStopped;
//			}
//			break;
//		case WinchStopped:
//			if (!components.ArmRetracted.get()) {
//				if (ButtonA == true) {
//					components.winchMotor.set(WINCH_SPEED);
//					WinchState = WinchMovingUp;
//				}
//				if (ButtonB == true && !components.ArmExtended.get()) {
//					if (ButtonB == true) {
//						components.winchMotor.set(-WINCH_SPEED);
//						WinchState = WinchMovingDown;
//
//					}
//				}
//
//			}break;
//		case WinchMovingUp:
//			if (components.ArmRetracted.get()) {
//				components.winchMotor.set(0);
//				WinchState = WinchTransport;
//			}
//			if (ButtonB == false) {
//				components.winchMotor.set(0);
//				WinchState = WinchStopped;
//			}break;
//		}
//		// //////////////SCREW TELEOP/////////////////////////
//		switch (ScrewState) {
//		case ScrewTransport:
//			if (!components.armScrewOut.get()) {
//				if (ButtonBothOutDown == true) {
//					
//					components.screwMotor.set(SCREW_SPEED);
//					ScrewState = ScrewMovingOut;
//				}
//
//			}
//		case ScrewMovingOut:
//			if (components.armScrewOut.get() || ButtonBothOutDown == false) {
//				
//				components.screwMotor.set(0);
//				ScrewState = ScrewStopped;
//			}
//			break;
//			
//		case ScrewStopped:
//			if (!components.armScrewIn.get()) {
//				if (ButtonBothInUp == true) {
//					
//					components.screwMotor.set(-SCREW_SPEED);
//					ScrewState = ScrewMovingIn;
//				}
//				if (ButtonBothOutDown == true && !components.armScrewOut.get()) {
//					if (ButtonBothOutDown == true) {
//						
//						components.screwMotor.set(SCREW_SPEED);
//						ScrewState = ScrewMovingOut;
//
//					}
//				}
//
//			}
//			break;
//		case ScrewMovingIn:
//			if (components.armScrewIn.get()) {
//				components.screwMotor.set(0);
//				ScrewState = ScrewTransport;
//			}
//			if (ButtonBothOutDown == false) {
//				components.winchMotor.set(0);
//				WinchState = WinchStopped;
//			}
//			break;
//		}

		switch (WinchState) {
		case WinchTransport:
			if (joy.getRawButton(joy.BUTTON_LT) == true) {
				//if (WinchPot.getVoltage() > this.winchLowerPotVal) {
					components.winchMotor.set(-WINCH_SPEED);
					WinchState = WinchMovingDown;
				//}
			}
			break;
		case WinchMovingDown:
			if (joy.getRawButton(joy.BUTTON_LT) == false) {
				components.winchMotor.set(0);
				WinchState = WinchStopped;
			}

			break;
		case WinchStopped:

			if (joy.getRawButton(joy.BUTTON_RT) == true) {
				components.winchMotor.set(WINCH_SPEED);
				WinchState = WinchMovingUp;

			}
			if (joy.getRawButton(joy.BUTTON_LT) == true) {
				components.winchMotor.set(-WINCH_SPEED);
				WinchState = WinchMovingDown;

			}

			break;
		case WinchMovingUp:
			if (joy.getRawButton(joy.BUTTON_RT) == false) {
				components.winchMotor.set(0);
				WinchState = WinchStopped;
			}
			break;
		}

		switch (ScrewState) {
		case ScrewTransport:
			if (screwLimitSwitch.get()) {
				if (joy.getRawButton(7) == true) {

					components.screwMotor.set(SCREW_SPEED);
					ScrewState = ScrewMovingOut;
				}

			}
			break;
		case ScrewMovingOut:
			if (screwLimitSwitch.get() || joy.getRawButton(7) == false) {

				components.screwMotor.set(0);
				ScrewState = ScrewStopped;
			}
			break;

		case ScrewStopped:

			if (joy.getRawButton(8) == true) {

				components.screwMotor.set(-SCREW_SPEED);
				ScrewState = ScrewMovingIn;
			}
			if (joy.getRawButton(7) == true && screwLimitSwitch.get()) {

				components.screwMotor.set(SCREW_SPEED);
				ScrewState = ScrewMovingOut;

			}

			break;
		case ScrewMovingIn:

			if (joy.getRawButton(8) == false) {
				components.screwMotor.set(0);
				ScrewState = ScrewStopped;
			}
			break;
		}

	}

	

	public void disabled() {
		BothState = TransportMode;
		components.screwMotor.set(0);
		components.winchMotor.set(0);
	}

	public void autonomousPeriodic() {
		SmartDashboard.putString("DB/String 0",
				String.valueOf(components.ArmExtended.get()));
		SmartDashboard.putString("DB/String 1",
				String.valueOf(components.ArmRetracted.get()));
		SmartDashboard.putString("DB/String 2",
				String.valueOf(components.armScrewOut.get()));
		SmartDashboard.putString("DB/String 3",
				String.valueOf(components.armScrewIn.get()));
		ButtonZ = ArmJoy.getRawButton(components.SCREW_IN_BUTTON);// rename
																	// for
																	// both
		// Buttonz
		// WinchButtonIn1 =
		// ArmJoy.getRawButton(components.SCREW_IN_BUTTON);//check which one not
		// used
		// WinchButtonOut1 = ArmJoy.getRawButton(components.SCREW_OUT_BUTTON);
		ButtonBothInUp = ArmJoy.getRawButton(components.BOTH_IN_UP_BUTTON);// ButtonY
		ButtonBothOutDown = ArmJoy.getRawButton(components.BOTH_OUT_DOWN_BUTTON);// ButtonX

		double voltX = 0;
		double voltY = 1; // need real voltage value
		double posX = 1;
		double posY = 2;
		double posZ = 3;

		// SliderButtonIn1 = ArmJoy.getRawButton(components.ARM_IN_BUTTON);

		switch (BothState) {
		case TransportMode: // ArmExtended is Winch LimitSwitch
			if (ButtonBothOutDown == true) {

				// switch
				if ((screwEncoder.getDistance() < posX)) {
					if (!components.armScrewOut.get()) {
						components.screwMotor.set(SCREW_SPEED);
						BothState = PreparingScrewforPickUp;
					}
				} // talk to electrical about high or low trigger
					// RENAME TO winch limit
				if ((WinchPot.getVoltage() < voltX)) {
					if (!components.ArmExtended.get()) {
						components.winchMotor.set(-WINCH_SPEED);
						if (BothState == PreparingScrewforPickUp) {
							BothState = PreparingBothforPickUp;
						} else {
							BothState = PreparingWinchforPickUp;
						}
					}

				}
			}

			break;

		case PreparingScrewforPickUp:
			if ((!components.armScrewOut.get())
					|| (screwEncoder.getDistance() >= posX)) {
				components.screwMotor.set(0);
				BothState = BothPreparedforPickUp;
			}
			break;

		case PreparingWinchforPickUp:
			if ((!components.ArmExtended.get())
					|| (WinchPot.getVoltage() >= voltX)) {
				components.winchMotor.set(0);
				BothState = BothPreparedforPickUp;
			}
			break;

		case PreparingBothforPickUp:
			if ((!components.armScrewOut.get())
					|| (screwEncoder.getDistance() >= posX)) {
				components.screwMotor.set(0);
				BothState = PreparingWinchforPickUp;

			}
			if ((!components.ArmExtended.get())
					|| (WinchPot.getVoltage() >= voltX)) {
				components.winchMotor.set(0);
				if (BothState == PreparingBothforPickUp)

					BothState = PreparingScrewforPickUp;
			} else {
				BothState = BothPreparedforPickUp;
			}
			break;

		case BothPreparedforPickUp:
			if (ButtonBothInUp == true) {
				if ((!components.ArmExtended.get())
						|| (((WinchPot.getVoltage() < voltY)))) {
					components.winchMotor.set(WINCH_SPEED);
					BothState = DragWinch;
				}
			}
			break;

		case DragWinch:
			if ((!components.ArmExtended.get())
					|| (((WinchPot.getVoltage() >= voltY)))) {
				components.winchMotor.set(WINCH_SPEED);
				if ((!components.armScrewIn.get())
						|| ((screwEncoder.getDistance() < posY))) {
					components.screwMotor.set(SCREW_SPEED);
					BothState = PickUp;
				}
			}

			break;

		case PickUp:
			if ((!components.armScrewIn.get())
					|| ((screwEncoder.getDistance() >= posY))) {
				components.screwMotor.set(0);
				BothState = StoppedAfterDragging;
			}
			break;

		case StoppedAfterDragging: // /check this
			if (ButtonBothOutDown == true) {
				if (!components.armScrewOut.get()) {
					if (!components.ArmExtended.get()) { // add winch limit
															// switch
						if (!(screwEncoder.getDistance() == posX)) {
							components.screwMotor.set(SCREW_SPEED);

						}
						if (!(WinchPot.getVoltage() == voltX)) {
							components.winchMotor.set(-WINCH_SPEED);
							BothState = PreparingBothforPickUp;
						}

					}

				}
			}
			if (ButtonZ == true) {
				if ((!components.armScrewIn.get())
						|| ((screwEncoder.getDistance() < posZ))) {
					components.screwMotor.set(SCREW_SPEED);
					BothState = MovingScrewBack;
				}

				if ((!(components.ArmRetracted.get()))
						|| ((WinchPot.getVoltage() < voltY))) {
					components.winchMotor.set(WINCH_SPEED);
					if (BothState == MovingScrewBack) {
						BothState = BothMovingBack;
					} else {
						BothState = MovingWinchBack;
					}
				}
			}

			break;

		case MovingScrewBack:
			if ((!components.armScrewIn.get())
					|| (screwEncoder.getDistance() >= posZ)) {
				components.screwMotor.set(0);
				BothState = BothMovingBack;
			}
			break;

		case MovingWinchBack:
			if ((!(components.ArmRetracted.get()))
					|| ((WinchPot.getVoltage() >= voltY))) {
				components.winchMotor.set(0);
				BothState = BothMovingBack;
			}

		case BothMovingBack:
			if ((!(components.armScrewIn.get()))
					|| screwEncoder.getDistance() < posZ) {
				components.screwMotor.set(-SCREW_SPEED);
				BothState = MovingScrewBack;
			}
			if (!(components.ArmRetracted.get())) {
				if (WinchPot.getVoltage() == voltY) {
					components.winchMotor.set(1);
					if (BothState == MovingScrewBack) {
						BothState = BothMovingBack;
					} else {
						BothState = MovingWinchBack;
					}
				}
				if (BothState == BothMovingBack) {
					BothState = TransportMode;
				}
			}

		}

	}
}