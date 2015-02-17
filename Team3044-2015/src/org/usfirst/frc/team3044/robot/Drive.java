package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.DriverStation.DriverController;
import org.usfirst.frc.team3044.utils.Components;
import org.usfirst.frc.team3044.utils.TalonEncoder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {
	Components components;
	DriverController DriveJoy = DriverController.getInstance();

	/*
	 * TalonEncoder LeftFrontEn; TalonEncoder RightFrontEn; TalonEncoder
	 * RightBackEn; TalonEncoder LeftBackEn;
	 */

	final int FULL_ROT = 418;
	final int HALF_ROT = 209;

	TalonEncoder LeftFrontEn;
	TalonEncoder RightFrontEn;
	TalonEncoder RightBackEn;
	TalonEncoder LeftBackEn;

	CANTalon LeftFrontTurn;
	CANTalon RightFrontTurn;
	CANTalon RightBackTurn;
	CANTalon LeftBackTurn;

	CANTalon LeftFrontDrive;
	CANTalon RightFrontDrive;
	CANTalon RightBackDrive;
	CANTalon LeftBackDrive;

	AnalogInput TopLightL;
	AnalogInput MidLightL;
	AnalogInput BotLightL;
	AnalogInput TopLightR;
	AnalogInput MidLightR;
	AnalogInput BotLightR;

	DigitalInput frontLeftMag;
	DigitalInput frontRightMag;
	DigitalInput backLeftMag;
	DigitalInput backRightMag;
	
	public void DriveInit() {
		components = Components.getInstance();
		LeftFrontEn = components.rotEncoderFL;
		RightFrontEn = components.rotEncoderFR;
		RightBackEn = components.rotEncoderBR;
		LeftBackEn = components.rotEncoderBL;

		LeftFrontTurn = components.frontLeftDriveRot;
		RightFrontTurn = components.frontRightDriveRot;
		RightBackTurn = components.backRightDriveRot;
		LeftBackTurn = components.backLeftDriveRot;

		LeftFrontDrive = components.frontLeftDrive;
		RightFrontDrive = components.frontRightDrive;
		RightBackDrive = components.backRightDrive;
		LeftBackDrive = components.backLeftDrive;

		TopLightL = components.LightSensorFrontLeft;
		MidLightL = components.LightSensorFrontMid;
		BotLightL = components.LightSensorFrontRight;
		TopLightR = components.LightSensorBackLeft;
		MidLightR = components.LightSensorBackMid;
		BotLightR = components.LightSensorBackRight;
		
		frontLeftMag = components.driveRotationFLMag;
		frontRightMag = components.driveRotationFRMag;
		backLeftMag = components.driveRotationBLMag;
		backRightMag = components.driveRotationBRMag;
	}

	double Distance;
	// Real Robot
	final double L = 30.875;
	final double W = 21.5;

	// Test Robot
	// final double L = 33.75;
	// final double W = 21.375;
	final double R = Math.sqrt(Math.pow(L, 2) + Math.pow(L, 2));
	final double LR = (L / R);
	final double WR = (W / R);
	final double Count = 1 / Math.PI;

	double Strafe;
	double Forward;
	double Rotate;
	double A;
	double B;
	double C;
	double D;

	double Drive;

	double WheelLFS;
	double WheelRFS;
	double WheelRBS;
	double WheelLBS;

	double WheelLFA;
	double WheelRFA;
	double WheelRBA;
	double WheelLBA;

	double ActDistanceLF = 0;
	double ActDistanceRF = 0;
	double ActDistanceRB = 0;
	double ActDistanceLB = 0;

	double Target;
	double TargetD;

	int On = 1;
	int OnLeft = 0;
	final double White = 2700;
	final double Val = .1;
	final double Speed = .05;
	int Something = 0;

	double LFSpeed = 0;
	double LBSpeed = 0;
	double RFSpeed = 0;
	double RBSpeed = 0;

	int CS;
	int MS;
	int RS;
	int Left;
	int Right;

	int driveState = 1;
	final int CALIBRATING = 0;
	final int RUNNING = 1;
	final int CALIB_INIT = 2;
	
	final int FR_TICKS_TO_STRAIGHT = 153 * 4;
	final int BR_TICKS_TO_STRAIGHT = 151 * 4;
	final int FL_TICKS_TO_STRAIGHT = 44 * 4;
	final int BL_TICKS_TO_STRAIGHT = 45 * 4;
	
	final double CALIB_SPEED = .3;

	public double Deadband(double value, double band) {
		if (Math.abs(value) < band) {
			value = 0;
		}
		return value;
	}

	public double Turn(double target, double val) {
		double MotorTurn = 0;
		double MS1 = .7;
		double MS2 = .25;
		double MS3 = .15;
		double Tol1 = .2;// .1;
		double Tol2 = .1;// .05;
		double Tol3 = .04;// .02;
		double Diff = Math.abs(target - val);
		SmartDashboard.putString("DB/String 5", String.valueOf(Diff));
		SmartDashboard.putString("DB/String 6", String.valueOf(target));
		SmartDashboard.putString("DB/String 7", String.valueOf(val));
		SmartDashboard.putString("DB/String 8", String.valueOf(this.RightBackEn.getDistance()));
		

		if (Diff <= 1) {
			if (val > target + Tol1) {
				MotorTurn = MS1;
			} else if (val < target - Tol1) {
				MotorTurn = -MS1;
			} else if (val > target + Tol2) {
				MotorTurn = MS2;
			} else if (val < target - Tol2) {
				MotorTurn = -MS2;
			} else if (val > target + Tol3) {
				MotorTurn = MS3;
			} else if (val < target - Tol3) {
				MotorTurn = -MS3;
			} else {
				MotorTurn = 0;
			}
		} else {
			if (val > target && Diff > Tol1) {
				MotorTurn = -MS1;
			} else if (val < target && Diff > Tol1) {
				MotorTurn = MS1;
			} else if (val > target && Diff > Tol2) {
				MotorTurn = -MS2;
			} else if (val < target && Diff > Tol2) {
				MotorTurn = MS2;
			} else if (val > target && Diff > Tol3) {
				MotorTurn = -MS3;
			} else if (val < target && Diff > Tol3) {
				MotorTurn = MS3;
			} else {
				MotorTurn = 0;
			}
		}
		return MotorTurn;
	}

	public double Speed(double MoveSpeed) {
		if (MoveSpeed > 1) {
			MoveSpeed = 1;
		}
		return -MoveSpeed;
	}

	public double Val(double Encoder) {
		Encoder += HALF_ROT;
		Encoder = (Encoder % FULL_ROT + FULL_ROT) % FULL_ROT;
		Encoder -= HALF_ROT;
		Encoder = Encoder / HALF_ROT;
		return -Encoder;
	}// 1662 - Expected: 1672

	public void robotInit() {
		LeftFrontEn.reset();
		RightFrontEn.reset();
		RightBackEn.reset();
		LeftBackEn.reset();
	}

	public void autonomousInit() {
		OnLeft = 0;
	}

	public void autonomousPeriodic() {
		UptoLineL();
		SmartDashboard.putString("DB/String 0", String.valueOf(CS));
		SmartDashboard.putString("DB/String 1", String.valueOf(MS));
		SmartDashboard.putString("DB/String 2", String.valueOf(RS));
		SmartDashboard.putString("DB/String 3", String.valueOf(L));
		SmartDashboard.putString("DB/String 4", String.valueOf(Something));
	}

	public void UptoLineL() {

		CS = TopLightL.getValue();
		MS = MidLightL.getValue();
		RS = BotLightL.getValue();

		switch (OnLeft) {
		case 0:
			LeftFrontDrive.set(.1);
			LeftBackDrive.set(.1);
			RightFrontDrive.set(.1);
			RightBackDrive.set(.1);
			OnLeft = 1;
			break;
		case 1:
			if (MS < White) {
				LeftFrontDrive.set(0);
				LeftBackDrive.set(0);
				RightFrontDrive.set(0);
				RightBackDrive.set(0);
				OnLeft = 2;
			}
			break;
		case 2:
			if (RS < White && MS > White) {
				LeftFrontDrive.set(-.1);
				LeftBackDrive.set(-.1);
				RightFrontDrive.set(-.1);
				RightBackDrive.set(-.1);
				OnLeft = 3;
			} else if (CS < White && MS > White) {
				LeftFrontDrive.set(.1);
				LeftBackDrive.set(.1);
				RightFrontDrive.set(.1);
				RightBackDrive.set(.1);
				OnLeft = 4;
			}
		case 3:
			if (MS < White) {
				LeftFrontDrive.set(0);
				LeftBackDrive.set(0);
				RightFrontDrive.set(0);
				RightBackDrive.set(0);
				OnLeft = 2;
			}
			break;
		case 4:
			if (MidLightL.getValue() < White) {
				LeftFrontDrive.set(0);
				LeftBackDrive.set(0);
				RightFrontDrive.set(0);
				RightBackDrive.set(0);
				OnLeft = 2;
			}
		}
	}

	public void teleopInit() {

	}
	
	public boolean calibFR(){
		if(frontRightMag.get()){
			this.RightFrontTurn.set(0);
			RightFrontEn.reset(this.FR_TICKS_TO_STRAIGHT);
			return true;
		}else{
			this.RightFrontTurn.set(CALIB_SPEED);
			return false;
		}
		
	}
	
	public boolean calibBR(){
		if(backRightMag.get()){
			this.RightBackTurn.set(0);
			RightBackEn.reset(this.BR_TICKS_TO_STRAIGHT);
			return true;
		}else{
			this.RightBackTurn.set(CALIB_SPEED);
			return false;
		}
	}
	
	public boolean calibBL(){
		if(backLeftMag.get()){
			this.LeftBackTurn.set(0);
			LeftBackEn.reset(this.BL_TICKS_TO_STRAIGHT);
			return true;
		}else{
			this.LeftBackTurn.set(CALIB_SPEED);
			return false;
		}
	}
	
	public boolean calibFL(){
		if(frontLeftMag.get()){
			this.LeftFrontTurn.set(0);
			LeftFrontEn.reset(this.FL_TICKS_TO_STRAIGHT);
			return true;
		}else{
			this.LeftFrontTurn.set(CALIB_SPEED);
			return false;
		}
	}
	

	public void teleopPeriodic() {

		Forward = -Deadband(DriveJoy.getRightY(), .2);
		Strafe = Deadband(DriveJoy.getRightX(), .2);
		Rotate = Deadband(DriveJoy.getLeftX(), .2);

		// System.out.println(LeftFrontEn == null);
		ActDistanceLF = Val(LeftFrontEn.getDistance());

		ActDistanceRF = Val(RightFrontEn.getDistance());
		ActDistanceRB = Val(RightBackEn.getDistance());
		ActDistanceLB = Val(LeftBackEn.getDistance());

		// Drive = DriveJoy.getTriggerRight() - DriveJoy.getTriggerLeft();

		A = Strafe - Rotate * LR;
		B = Strafe + Rotate * LR;
		C = Forward - Rotate * WR;
		D = Forward + Rotate * WR;

		A = Deadband(A, .0000001);
		B = Deadband(B, .0000001);
		C = Deadband(C, .0000001);
		D = Deadband(D, .0000001);

		WheelLFS = Math.sqrt((B * B) + (D * D));
		WheelRFS = Math.sqrt((B * B) + (C * C));
		WheelRBS = Math.sqrt((A * A) + (C * C));
		WheelLBS = Math.sqrt((A * A) + (D * D));

		WheelLFA = Math.atan2(B, D) * Count;
		WheelRFA = Math.atan2(B, C) * Count;
		WheelRBA = Math.atan2(A, C) * Count;
		WheelLBA = Math.atan2(A, D) * Count;

		
		
		SmartDashboard.putString("DB/String 0",
				
				String.valueOf(RightFrontEn.getDistance()));
		switch (driveState) {
		case RUNNING:
			LeftFrontTurn.set(Turn(WheelLFA, ActDistanceLF));
			RightFrontTurn.set(Turn(WheelRFA, ActDistanceRF));
			RightBackTurn.set(Turn(WheelRBA, ActDistanceRB));
			LeftBackTurn.set(Turn(WheelLBA, ActDistanceLB));

			if (DriveJoy.getTriggerRight() < .3) {
				if (DriveJoy.getRawButton(10)) {
					LeftFrontDrive.set(Speed(WheelLFS));
					RightFrontDrive.set(Speed(WheelRFS));
					RightBackDrive.set(Speed(WheelRBS));
					LeftBackDrive.set(Speed(WheelLBS));
				} else {
					LeftFrontDrive.set(Speed(WheelLFS) / 2);
					RightFrontDrive.set(Speed(WheelRFS) / 2);
					RightBackDrive.set(Speed(WheelRBS) / 2);
					LeftBackDrive.set(Speed(WheelLBS) / 2);
				}
			} else {
				LeftFrontDrive.set(0);
				RightFrontDrive.set(0);
				RightBackDrive.set(0);
				LeftBackDrive.set(0);
			}
			if(DriveJoy.getRawButton(DriveJoy.BUTTON_LT)){
				driveState = CALIB_INIT;
			}
			break;
		case CALIBRATING:
			if(calibFR() && calibBR() && calibFL() && calibBL()){
				driveState = RUNNING;
				
			}
			
			break;
		case CALIB_INIT:
			LeftFrontEn.reset(5000);
			RightFrontEn.reset(5000);
			RightBackEn.reset(5000);
			LeftBackEn.reset(5000);
			this.driveState = CALIBRATING;
			break;
		}

		SmartDashboard.putString("DB/String 0",
				String.valueOf(RightFrontEn.getDistance()));

	}

	boolean spin = true;
	public void testPeriodic() {
		SmartDashboard.putString("DB/String 0", String.valueOf(components.LightSensorBackLeft.getValue()));
		SmartDashboard.putString("DB/String 1", String.valueOf(components.LightSensorBackMid.getValue()));
		SmartDashboard.putString("DB/String 2", String.valueOf(components.LightSensorBackRight.getValue()));
		SmartDashboard.putString("DB/String 3", String.valueOf(components.LightSensorFrontLeft.getValue()));
		SmartDashboard.putString("DB/String 4", String.valueOf(components.LightSensorFrontMid.getValue()));
		SmartDashboard.putString("DB/String 5", String.valueOf(components.LightSensorFrontRight.getValue()));
		LeftBackEn.reset(400);
		/*if(backLeftMag.get() == false)
			spin = false;
		
		if(spin){
			LeftBackTurn.set(.3);
		}
		else{
			LeftBackTurn.set(0);
		}*/
		
		
	}

	public void disableInit() {

	}

}
