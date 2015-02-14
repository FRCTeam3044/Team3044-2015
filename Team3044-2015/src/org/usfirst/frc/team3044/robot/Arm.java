package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.utils.Utilities;
import org.usfirst.frc.team3044.DriverStation.DriverController;
import org.usfirst.frc.team3044.DriverStation.SecondaryController;
import org.usfirst.frc.team3044.utils.Components;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {
	SecondaryController ArmJoy = SecondaryController.getInstance();

	// BUTTONS

	boolean ButtonY = true;
	boolean ButtonX = true;
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

	int WinchSpeed = 0;
	int ScrewSpeed = 0;

	int BothState = TransportMode;

	Components components = Components.getInstance();
	Encoder screwEncoder = components.encoderScrew;
	AnalogInput WinchPot = components.encoderWinch;

	public void robotInit() {
		WinchSpeed = 0;
		ScrewSpeed = 0;
		BothState = TransportMode;
		components.screwMotor.set(ScrewSpeed);
		components.winchMotor.set(WinchSpeed);
	}

	public void teleopInit() {
		WinchSpeed = 0;
		ScrewSpeed = 0;
		BothState = TransportMode;
		components.screwMotor.set(ScrewSpeed);
		components.winchMotor.set(WinchSpeed);

	}

	public void autoInit() {
		// create a condition to check state where we left off so we can
		// continue
		WinchSpeed = 0;
		ScrewSpeed = 0;
		BothState = TransportMode;
		components.screwMotor.set(ScrewSpeed);
		components.winchMotor.set(WinchSpeed);
	}

	public void teleopPeriodic() {
		ButtonX = ArmJoy.getRawButton(components.SCREW_OUT_BUTTON);
		ButtonY = ArmJoy.getRawButton(components.SCREW_IN_BUTTON);
		ButtonA = ArmJoy.getRawButton(components.WINCH_UP_BUTTON);
		ButtonB = ArmJoy.getRawButton(components.WINCH_DOWN_BUTTON);

		switch (WinchState) {
		case WinchTransport:
			if (!components.ArmExtended.get()) {
				if (ButtonB == true) {
					WinchSpeed = -1;
					components.winchMotor.set(WinchSpeed);
					WinchState = WinchMovingDown;
				}
			}
			break;
		case WinchMovingDown:
			if (components.ArmExtended.get() || ButtonB == false) {
				WinchSpeed = 0;
				components.winchMotor.set(WinchSpeed);
				WinchState = WinchStopped;
			}
			break;
		case WinchStopped:
			if (!components.ArmRetracted.get()) {
				if (ButtonA == true) {
					WinchSpeed = 1;
					components.winchMotor.set(WinchSpeed);
					WinchState = WinchMovingUp;
				}
				if (ButtonB == true && !components.ArmExtended.get()) {
					if (ButtonB == true) {
						WinchSpeed = -1;
						components.winchMotor.set(WinchSpeed);
						WinchState = WinchMovingDown;

					}
				}

			}break;
		case WinchMovingUp:
			if (components.ArmRetracted.get()) {
				WinchSpeed = 0;
				components.winchMotor.set(WinchSpeed);
				WinchState = WinchTransport;
			}
			if (ButtonB == false) {
				WinchSpeed = 0;
				components.winchMotor.set(WinchSpeed);
				WinchState = WinchStopped;
			}break;
		}
		// //////////////SCREW TELEOP/////////////////////////
		switch (ScrewState) {
		case ScrewTransport:
			if (!components.armScrewOut.get()) {
				if (ButtonX == true) {
					ScrewSpeed = 1;
					components.screwMotor.set(ScrewSpeed);
					ScrewState = ScrewMovingOut;
				}

			}
		case ScrewMovingOut:
			if (components.armScrewOut.get() || ButtonX == false) {
				ScrewSpeed = 0;
				components.screwMotor.set(ScrewSpeed);
				ScrewState = ScrewStopped;
			}
			break;
			
		case ScrewStopped:
			if (!components.armScrewIn.get()) {
				if (ButtonY == true) {
					ScrewSpeed = -1;
					components.screwMotor.set(ScrewSpeed);
					ScrewState = ScrewMovingIn;
				}
				if (ButtonX == true && !components.armScrewOut.get()) {
					if (ButtonX == true) {
						ScrewSpeed = 1;
						components.screwMotor.set(ScrewSpeed);
						ScrewState = ScrewMovingOut;

					}
				}

			}
			break;
		case ScrewMovingIn:
			if (components.armScrewIn.get()) {
				ScrewSpeed = 0;
				components.screwMotor.set(ScrewSpeed);
				ScrewState = ScrewTransport;
			}
			if (ButtonX == false) {
				WinchSpeed = 0;
				components.winchMotor.set(WinchSpeed);
				WinchState = WinchStopped;
			}
			break;
		}
	}

	public void disabled() {
		WinchSpeed = 0;
		ScrewSpeed = 0;
		BothState = TransportMode;
		components.screwMotor.set(0);
		components.winchMotor.set(0);
	}

	public void armPeriodic() {
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
		ButtonY = ArmJoy.getRawButton(components.BOTH_IN_UP_BUTTON);// ButtonY
		ButtonX = ArmJoy.getRawButton(components.BOTH_OUT_DOWN_BUTTON);// ButtonX

		double voltX = 0;
		double voltY = 1; // need real voltage value
		double posX = 1;
		double posY = 2;
		double posZ = 3;

		// SliderButtonIn1 = ArmJoy.getRawButton(components.ARM_IN_BUTTON);

		switch (BothState) {
		case TransportMode: // ArmExtended is Winch LimitSwitch
			if (ButtonX == true) {

				// switch
				if ((screwEncoder.getDistance() < posX)) {
					if (!components.armScrewOut.get()) {
						ScrewSpeed = 1;
						components.screwMotor.set(ScrewSpeed);
						BothState = PreparingScrewforPickUp;
					}
				} // talk to electrical about high or low trigger
					// RENAME TO winch limit
				if ((WinchPot.getVoltage() < voltX)) {
					if (!components.ArmExtended.get()) {
						WinchSpeed = -1;
						components.winchMotor.set(WinchSpeed);
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
				ScrewSpeed = 0;
				components.screwMotor.set(ScrewSpeed);
				BothState = BothPreparedforPickUp;
			}
			break;

		case PreparingWinchforPickUp:
			if ((!components.ArmExtended.get())
					|| (WinchPot.getVoltage() >= voltX)) {
				WinchSpeed = 0;
				components.winchMotor.set(WinchSpeed);
				BothState = BothPreparedforPickUp;
			}
			break;

		case PreparingBothforPickUp:
			if ((!components.armScrewOut.get())
					|| (screwEncoder.getDistance() >= posX)) {
				ScrewSpeed = 0;
				components.screwMotor.set(ScrewSpeed);
				BothState = PreparingWinchforPickUp;

			}
			if ((!components.ArmExtended.get())
					|| (WinchPot.getVoltage() >= voltX)) {
				WinchSpeed = 0;
				components.winchMotor.set(WinchSpeed);
				if (BothState == PreparingBothforPickUp)

					BothState = PreparingScrewforPickUp;
			} else {
				BothState = BothPreparedforPickUp;
			}
			break;

		case BothPreparedforPickUp:
			if (ButtonY == true) {
				if ((!components.ArmExtended.get())
						|| (((WinchPot.getVoltage() < voltY)))) {
					WinchSpeed = 1;
					components.winchMotor.set(WinchSpeed);
					BothState = DragWinch;
				}
			}
			break;

		case DragWinch:
			if ((!components.ArmExtended.get())
					|| (((WinchPot.getVoltage() >= voltY)))) {
				WinchSpeed = 0;
				components.winchMotor.set(WinchSpeed);
				if ((!components.armScrewIn.get())
						|| ((screwEncoder.getDistance() < posY))) {
					ScrewSpeed = 1;
					components.screwMotor.set(ScrewSpeed);
					BothState = PickUp;
				}
			}

			break;

		case PickUp:
			if ((!components.armScrewIn.get())
					|| ((screwEncoder.getDistance() >= posY))) {
				ScrewSpeed = 0;
				components.screwMotor.set(ScrewSpeed);
				BothState = StoppedAfterDragging;
			}
			break;

		case StoppedAfterDragging: // /check this
			if (ButtonX == true) {
				if (!components.armScrewOut.get()) {
					if (!components.ArmExtended.get()) { // add winch limit
															// switch
						if (!(screwEncoder.getDistance() == posX)) {
							ScrewSpeed = 1;
							components.screwMotor.set(ScrewSpeed);

						}
						if (!(WinchPot.getVoltage() == voltX)) {
							WinchSpeed = -1;
							components.winchMotor.set(WinchSpeed);
							BothState = PreparingBothforPickUp;
						}

					}

				}
			}
			if (ButtonZ == true) {
				if ((!components.armScrewIn.get())
						|| ((screwEncoder.getDistance() < posZ))) {
					ScrewSpeed = -1;
					components.screwMotor.set(ScrewSpeed);
					BothState = MovingScrewBack;
				}

				if ((!(components.ArmRetracted.get()))
						|| ((WinchPot.getVoltage() < voltY))) {
					WinchSpeed = 1;
					components.winchMotor.set(WinchSpeed);
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
				ScrewSpeed = 0;
				components.screwMotor.set(ScrewSpeed);
				BothState = BothMovingBack;
			}
			break;

		case MovingWinchBack:
			if ((!(components.ArmRetracted.get()))
					|| ((WinchPot.getVoltage() >= voltY))) {
				WinchSpeed = 0;
				components.winchMotor.set(WinchSpeed);
				BothState = BothMovingBack;
			}

		case BothMovingBack:
			if ((!(components.armScrewIn.get()))
					|| screwEncoder.getDistance() < posZ) {
				ScrewSpeed = -1;
				components.screwMotor.set(ScrewSpeed);
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