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
	boolean ButtonY = true;
	boolean ButtonX = true;
	boolean ButtonZ =  true;
	
	
	
	final int TransportMode = 1;
	final int PreparingWinchforPickUp = 2;
	final int PreparingScrewforPickUp = 3;
	final int PreparingBothforPickUp = 4;
	final int BothPreparedforPickUp = 5;
	final int PickUp = 6;
	final int DragWinch = 7;
	final int StoppedAfterDragging = 8;
	final int MovingScrewBack = 9;
	final int MovingWinchBack =10;	
	final int BothMovingBack = 11;

	int BothState = TransportMode;

	Components components = Components.getInstance();
	Encoder screwEncoder = components.encoderScrew;
	AnalogInput WinchPot = components.encoderWinch;

	public void robotInit() {
		BothState = TransportMode;
		components.screwMotor.set(0);
		components.winchMotor.set(0);

	}

	public void teleopInit() {

	}

	public void autoInit() {
		// create a condition to check state where we left off so we can
		// continue
		components.screwMotor.set(0);
		components.winchMotor.set(0);
	}

	public void disabled() {

		components.screwMotor.set(0);
		components.winchMotor.set(0);
	}

	public void armPeriodic() {
		ButtonZ = ArmJoy.getRawButton(components.SCREW_IN_BUTTON);// rename
																			// for
																			// both
		screwButtonOut1 = ArmJoy.getRawButton(components.SCREW_OUT_BUTTON);// Buttonz
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
						components.screwMotor.set(1);
						BothState = PreparingScrewforPickUp;
					}
				} // talk to electrical about high or low trigger
					// RENAME TO winch limit
				if ((WinchPot.getVoltage() < voltX)) {
					if (!components.ArmExtended.get()) {
						components.winchMotor.set(-1);
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
			if ((!components.armScrewOut.get()) || (screwEncoder.getDistance() >= posX)) {				
					components.screwMotor.set(0);
					BothState = BothPreparedforPickUp;
				}			
			break;
			
		case PreparingWinchforPickUp:
			if ((!components.ArmExtended.get()) || (WinchPot.getVoltage() >= voltX)) {
				components.winchMotor.set(0);
				BothState = BothPreparedforPickUp;				
			}
			break;
			
		case PreparingBothforPickUp:
			if ((!components.armScrewOut.get()) || (screwEncoder.getDistance() >= posX)) {
				components.screwMotor.set(0);
				BothState = PreparingWinchforPickUp;
				
			}
			if ((!components.ArmExtended.get()) || (WinchPot.getVoltage() >= voltX)) {
				components.winchMotor.set(0);
				if(BothState == PreparingBothforPickUp )
					
					BothState = PreparingScrewforPickUp;
				}else{					
					BothState = BothPreparedforPickUp;
				}
			break;

		case BothPreparedforPickUp:
			if (ButtonY == true) {
				if ((!components.ArmExtended.get()) || (((WinchPot.getVoltage() < voltY)))){
					components.winchMotor.set(1);
					BothState = DragWinch;
				}
				}			
			break;
			
		case DragWinch:
			if ((!components.ArmExtended.get()) || (((WinchPot.getVoltage() >= voltY)))){
				components.winchMotor.set(0);
				if ((!components.armScrewIn.get()) || ((screwEncoder.getDistance() < posY))){
					components.screwMotor.set(1);
					BothState = PickUp;
				}
			}
			
			break;
			
		case PickUp:	
			if ((!components.armScrewIn.get()) || ((screwEncoder.getDistance() >= posY))){
				components.screwMotor.set(0);
				BothState = StoppedAfterDragging;
			}
			break;
		
		
			
			
		
		case StoppedAfterDragging: ///check this
			if (ButtonX == true) {
				if (!components.armScrewOut.get()) {
					if (!components.ArmExtended.get()) { // add winch limit
															// switch
						if (!(screwEncoder.getDistance() == posX)) {
							components.screwMotor.set(1);

						}
						if (!(WinchPot.getVoltage() == voltX)) {
							components.winchMotor.set(-1);
							BothState = PreparingBothforPickUp;
						}

					}

				}
			}
			if (ButtonZ == true) {
				if ((!components.armScrewIn.get()) || ((screwEncoder.getDistance() < posZ))) {
						components.screwMotor.set(-1);
						BothState = MovingScrewBack;
					}
				
				if ((!(components.ArmRetracted.get())) || ((WinchPot.getVoltage() < voltY))){
						components.winchMotor.set(1);
				if(BothState == MovingScrewBack){
					BothState = BothMovingBack;
				}else{
				BothState = MovingWinchBack;
				}
			}
		}
		
			break;
			
		case MovingScrewBack:
			if((!components.armScrewIn.get()) || (screwEncoder.getDistance() >= posZ)){
				components.screwMotor.set(0);
				BothState =  BothMovingBack;
			}	
		break;
		
		
		case MovingWinchBack:
			if((!(components.ArmRetracted.get())) || ((WinchPot.getVoltage() >= voltY))){
				components.winchMotor.set(0);
				BothState = BothMovingBack;
			}
		
		
		case BothMovingBack:
			if ((!(components.armScrewIn.get())) || screwEncoder.getDistance() < posZ) {
					components.screwMotor.set(-1);
					BothState = MovingScrewBack;
				}		
			if (!(components.ArmRetracted.get())) {
				if (WinchPot.getVoltage() == voltY) {
					components.winchMotor.set(1);					
					if(BothState == MovingScrewBack){
						BothState = BothMovingBack;
					}else{
					BothState = MovingWinchBack;
					}
				}
				if (BothState == BothMovingBack){
					BothState = TransportMode;
				}
				}

			}

		}
	}
