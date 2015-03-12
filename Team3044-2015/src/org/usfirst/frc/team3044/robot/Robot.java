package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.DriverStation.DriverController;
import org.usfirst.frc.team3044.DriverStation.SecondaryController;
import org.usfirst.frc.team3044.utils.Components;
import org.usfirst.frc.team3044.utils.Utilities;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	Arm arm = new Arm();
	Drive drive = new Drive();
	Forklift forklift = new Forklift();
	Components components = Components.getInstance();
	Utilities utils = new Utilities();

	DriverController driverController = DriverController.getInstance();
	SecondaryController secondaryController = SecondaryController.getInstance();
	
	DriverStation ds = DriverStation.getInstance();

	int session;
	

	double lastUpdateTime = 15;
	double currentTime = 0;

	int autoState = 0;

	public void robotInit() {


		components.init();
		arm.robotInit();
		drive.DriveInit();
		drive.robotInit();
		forklift.robotInit();

	}

	public void autonomousInit() {
		arm.autoInit();
		//drive.teleopInit();
		driveAutoState = 0;
		armAutoState = 0;
		// components.init(); - test
		forklift.autoInit();
	}

	public void autonomousPeriodic() {
		currentTime = Timer.getMatchTime();
		if (count > 3) {

			lastUpdateTime = currentTime;
			count = 0;
		}
		count += 1;

		arm.teleopPeriodic();
		// drive.teleopPeriodic();
		this.armAuto();
		forklift.forkliftPeriodic();
		/*
		 * switch (autoState) { case 0: driverController.buttonFour = true;
		 * autoState++; break; case 1: if (!components.forkliftTote.get()) {
		 * System.out.println("off"); driverController.buttonFour = false;
		 * autoState = -1; } break; }
		 */

	}

	public void teleopInit() {
		arm.teleopInit();
		//drive.teleopInit();
		forklift.teleopInit();

	}

	int count = 0;

	public void teleopPeriodic() {
		currentTime = Timer.getMatchTime();
		if (count > 3) {

			lastUpdateTime = currentTime;
			count = 0;

		}
		count += 1;

		arm.teleopPeriodic();
		drive.teleopPeriodic();
		forklift.forkliftPeriodic();
	}

	public void testInit() {
		components.rotEncoderBL.reset();
		components.rotEncoderBR.reset();
		components.rotEncoderFL.reset();
		components.rotEncoderFR.reset();
	}

	public void testPeriodic() {
		components.frontRightDriveRot.set(.5);
		components.frontLeftDriveRot.set(.5);
		/*
		 * if(!components.driveRotationBLMag.get()){
		 * components.backLeftDriveRot.set(0);
		 * SmartDashboard.putString("DB/String 6",
		 * String.valueOf(components.rotEncoderBL.getDistance())); }else{
		 * components.backLeftDriveRot.set(.3); }
		 * 
		 * if(!components.driveRotationFLMag.get()){
		 * components.frontLeftDriveRot.set(0);
		 * SmartDashboard.putString("DB/String 5",
		 * String.valueOf(components.rotEncoderFL.getDistance())); }else{
		 * components.frontLeftDriveRot.set(.3); }
		 * 
		 * if(!components.driveRotationBRMag.get()){
		 * components.backRightDriveRot.set(0);
		 * SmartDashboard.putString("DB/String 8",
		 * String.valueOf(components.rotEncoderBR.getDistance())); }else{
		 * components.backRightDriveRot.set(.3); }
		 * 
		 * if(!components.driveRotationFRMag.get()){
		 * components.frontRightDriveRot.set(0);
		 * SmartDashboard.putString("DB/String 7",
		 * String.valueOf(components.rotEncoderFR.getDistance())); }else{
		 * components.frontRightDriveRot.set(.3); }
		 */

	}

	public void disabledInit() {
		// arm.disabled();
		drive.disableInit();
		forklift.disabled();
	}

	public void disabledPeriodic() {

		lastUpdateTime = currentTime;

	}

	int driveAutoState = 0;
	final int INIT_DRIVE = 0;
	final int DRIVE = 1;
	final int STOPPED = 2;

	double startTime = 0;

	int armAutoState = 0;
	
	
	public void armAuto(){
		switch(armAutoState){
		case 0:
			secondaryController.buttonFive = true;
			secondaryController.buttonSeven = true;
			armAutoState += 1;
			break;
		case 1:
			if(components.winchPot.getVoltage() < 2.68){
				secondaryController.buttonFive = false;

			}
			if(components.encoderScrew.getRaw() < -8200){
				secondaryController.buttonSeven = false;

			}
			
			if(components.winchPot.getVoltage() < 2.68 && components.encoderScrew.getRaw() < -8200){
				secondaryController.buttonFive = true;
				armAutoState = 3;
			}
			break;
		case 3:
			if(components.winchPot.getVoltage() < 2.4){
				secondaryController.buttonFive = false;
				secondaryController.buttonSeven = true;
				armAutoState += 1;
			}
			break;
		case 4:
			if(components.encoderScrew.getRaw()< -13500){
				secondaryController.buttonSeven = false;
				secondaryController.buttonSix = true;
				armAutoState += 1;
			}
			break;
		case 5:

			if(components.winchPot.getVoltage() > 2.65){
				secondaryController.buttonSix = false;
				secondaryController.buttonEight = true;
				armAutoState +=1;
			}
			
			break;
		case 6:
			if(components.encoderScrew.getRaw() > -100){
				secondaryController.buttonEight = false;
			}
		}
	}
	/*
	public void driveIntoAutoZone() {
		drive.calculations();
		switch (driveAutoState) {
		case INIT_DRIVE:
			System.out.println("STart");
			/*
			 * driverController.buttonFive = true; drive.teleopPeriodic(); if
			 * (drive.driveState == 1) { driverController.buttonFive = false;
			 * driveAutoState += 1; startTime = Timer.getFPGATimestamp(); }
			 
			startTime = Timer.getFPGATimestamp();
			SmartDashboard.putString("DB/String 9", String.valueOf(startTime));
			driveAutoState += 1;

			break;
		case DRIVE:
			drive.LeftFrontTurn.set(drive.Turn(.5, drive.ActDistanceLF));
			drive.LeftBackTurn.set(drive.Turn(.6, drive.ActDistanceLF));
			drive.RightBackTurn.set(drive.Turn(.6, drive.ActDistanceLF));
			drive.RightFrontTurn.set(drive.Turn(.5, drive.ActDistanceLF));
			if (Timer.getFPGATimestamp() - startTime > 1) {
				

				driveAutoState += 1;

			}

			break;
		case STOPPED:
			drive.LeftFrontTurn.set(0);
			drive.LeftBackTurn.set(0);
			drive.RightBackTurn.set(0);
			drive.RightFrontTurn.set(0);
			if (Timer.getFPGATimestamp() - startTime > 13) {
				drive.LeftFrontDrive.set(0);
				drive.RightFrontDrive.set(0);
				drive.RightFrontDrive.set(0);
				drive.RightFrontDrive.set(0);
				SmartDashboard
						.putString("DB/String 9", String.valueOf("Hello"));

			} else {

				drive.LeftFrontDrive.set(-.5);
				drive.RightFrontDrive.set(-.5);
				drive.RightFrontDrive.set(-.5);
				drive.RightFrontDrive.set(-.5);
			}
			break;
		}
	}*/

}
