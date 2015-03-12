package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.DriverStation.DriverController;
import org.usfirst.frc.team3044.utils.Components;
import org.usfirst.frc.team3044.utils.TalonEncoder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANJaguar;
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
	/*
	 * CANTalon LeftFrontTurn; CANTalon RightFrontTurn; CANTalon RightBackTurn;
	 * CANTalon LeftBackTurn;
	 * 
	 * CANTalon LeftFrontDrive; CANTalon RightFrontDrive; CANTalon
	 * RightBackDrive; CANTalon LeftBackDrive;
	 */

	int testCode = 1;

	CANJaguar LeftFrontTurn;
	CANJaguar RightFrontTurn;
	CANJaguar RightBackTurn;
	CANJaguar LeftBackTurn;

	CANJaguar LeftFrontDrive;
	CANJaguar RightFrontDrive;
	CANJaguar RightBackDrive;
	CANJaguar LeftBackDrive;

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
	final double Length = 30.875;
	final double Width = 21.5;

	// Test Robot
	// final double L = 33.75;
	// final double W = 21.375;
	final double Rad = Math.sqrt(Math.pow(Length, 2) + Math.pow(Width, 2));
	final double LR = (Length / Rad);
	final double WR = (Width / Rad);
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

	int L = 0;
	int R = 0;
	final double WhiteL = 1600;
	final double WhiteR = 1300;
	final double TOL1 = .1;
	int LF = 0;
	int RF = 0;
	int Follow = 0;
	double LFSpeed = 0;
	double LBSpeed = 0;
	double RFSpeed = 0;
	double RBSpeed = 0;
	double Angle;

	int CSL;
	int MSL;
	int RSL;
	int CSR;
	int MSR;
	int RSR;

	int driveState = 1;
	final int CALIBRATING = 0;
	final int RUNNING = 1;
	final int CALIB_INIT = 2;

	final int FR_TICKS_TO_STRAIGHT = 153 * 4;
	final int BR_TICKS_TO_STRAIGHT = 151 * 4;
	final int FL_TICKS_TO_STRAIGHT = 44 * 4;
	final int BL_TICKS_TO_STRAIGHT = 45 * 4;

	final double CALIB_SPEED = .3;
	public double lineFollowSpeed = .3;

	public double Deadband(double value, double band) {
		if (Math.abs(value) < band) {
			value = 0;
		}
		return value;
	}

	public double Turn(double target, double val) {
		double MotorTurn = 0;
		double MS1 = .75;// .7;
		double MS2 = .2;// .2;
		double MS3 = .1;// .15;
		double Tol1 = .2;// .1;
		double Tol2 = .1;// .05;
		double Tol3 = .04;// .02;
		double Diff = Math.abs(target - val);
		SmartDashboard.putString("DB/String 5", String.valueOf(Diff));
		SmartDashboard.putString("DB/String 6", String.valueOf(target));
		SmartDashboard.putString("DB/String 7", String.valueOf(val));
		SmartDashboard.putString("DB/String 8",
				String.valueOf(this.RightBackEn.getDistance()));

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

	public void UptoLineL() {
		switch (L) {
		case 0:
			LeftFrontDrive.set(.15);
			LeftBackDrive.set(.15);
			L = 1;
			break;
		case 1:
			if (MSL < WhiteL) {
				LeftFrontDrive.set(0);
				LeftBackDrive.set(0);
				L = 2;
			}
			break;
		case 2:
			if (RSL < WhiteL && MSL > WhiteL) {
				LeftFrontDrive.set(-.15);
				LeftBackDrive.set(-.15);
				L = 3;
			} else if (CSL < WhiteL && MSL > WhiteL) {
				LeftFrontDrive.set(.15);
				LeftBackDrive.set(.15);
				L = 4;
			}
		case 3:
			if (MSL < WhiteL) {
				LeftFrontDrive.set(0);
				LeftBackDrive.set(0);
				L = 2;
			}
			break;
		case 4:
			if (MSL < WhiteL) {
				LeftFrontDrive.set(0);
				LeftBackDrive.set(0);
				L = 2;
			}
		}
	}

	public void FollowLineL() {
		switch (LF) {
		case 0:
			LeftFrontTurn.set(Turn(Angle, ActDistanceLF));
			LeftBackTurn.set(Turn(Angle, ActDistanceLB));
			if (Direction()) {
				LeftFrontDrive.set(lineFollowSpeed);
				LeftBackDrive.set(lineFollowSpeed);
				LF = 1;
			}
			break;
		case 1:
			LeftFrontTurn.set(Turn(Angle, ActDistanceLF));
			LeftBackTurn.set(Turn(Angle, ActDistanceLB));
			if (RSL < WhiteL && MSL > WhiteL) {
				LF = 2;
			} else if (CSL < WhiteL && MSL > WhiteL) {
				LF = 3;
			}
			break;
		case 2:
			LeftFrontTurn.set(Turn(Angle - .1, ActDistanceLF));
			LeftBackTurn.set(Turn(Angle - .1, ActDistanceLB));
			if (MSL < WhiteL) {
				LF = 1;
			} else if (CSL < WhiteL && MSL > WhiteL) {
				LF = 3;
			}
			break;
		case 3:
			LeftFrontTurn.set(Turn(Angle + .1, ActDistanceLF));
			LeftBackTurn.set(Turn(Angle + .1, ActDistanceLB));
			if (MSL < WhiteL) {
				LF = 1;
			} else if (RSL < WhiteL && MSL > WhiteL) {
				LF = 2;
			}
			break;
		}
	}

	public void UptoLineR() {
		switch (R) {
		case 0:
			RightFrontDrive.set(.15);
			RightBackDrive.set(.15);
			R = 1;
			break;
		case 1:
			if (MSR < WhiteR) {
				RightFrontDrive.set(0);
				RightBackDrive.set(0);
				R = 2;
			}
			break;
		case 2:
			if (RSR < WhiteR && MSR > WhiteR) {
				RightFrontDrive.set(-.15);
				RightBackDrive.set(-.15);
				R = 3;
			} else if (CSR < WhiteR && MSR > WhiteR) {
				RightFrontDrive.set(.15);
				RightBackDrive.set(.15);
				R = 4;
			}
		case 3:
			if (MSR < WhiteR) {
				RightFrontDrive.set(0);
				RightBackDrive.set(0);
				R = 2;
			}
			break;
		case 4:
			if (MSR < WhiteR) {
				RightFrontDrive.set(0);
				RightBackDrive.set(0);
				R = 2;
			}
		}
	}

	public void FollowLineR() {
		switch (RF) {
		case 0:
			RightFrontTurn.set(Turn(Angle, ActDistanceRF));
			RightBackTurn.set(Turn(Angle, ActDistanceRB));
			if (Direction()) {
				RightFrontDrive.set(lineFollowSpeed);
				RightBackDrive.set(lineFollowSpeed);
				RF = 1;
			}
			break;
		case 1:
			RightFrontTurn.set(Turn(Angle, ActDistanceRF));
			RightBackTurn.set(Turn(Angle, ActDistanceRB));
			if (RSR < WhiteR && MSR > WhiteR) {
				RF = 2;
			} else if (CSR < WhiteR && MSR > WhiteR) {
				RF = 3;
			}
			break;
		case 2:
			RightFrontTurn.set(Turn(Angle - .1, ActDistanceRF));
			RightBackTurn.set(Turn(Angle - .1, ActDistanceRB));
			if (MSR < WhiteR) {
				RF = 1;
			} else if (CSR < WhiteR && MSR > WhiteR) {
				RF = 3;
			}
			break;
		case 3:
			RightFrontTurn.set(Turn(Angle + .1, ActDistanceRF));
			RightBackTurn.set(Turn(Angle + .1, ActDistanceRB));
			if (MSR < WhiteR) {
				RF = 1;
			} else if (RSR < WhiteR && MSR > WhiteR) {
				RF = 2;
			}
			break;
		}
	}

	public boolean Direction() {
		if (ActDistanceRF > Angle - TOL1 && ActDistanceRF < Angle + TOL1
				&& ActDistanceLF > Angle - TOL1 && ActDistanceLF < Angle + TOL1
				&& ActDistanceRB > Angle - TOL1 && ActDistanceRB < Angle + TOL1
				&& ActDistanceLB > Angle - TOL1 && ActDistanceLB < Angle + TOL1) {
			return true;
		} else {
			return false;
		}
	}

	public void robotInit() {
		LeftFrontEn.reset();
		RightFrontEn.reset();
		RightBackEn.reset();
		LeftBackEn.reset();
	}

	int flState = 0;
	int flOffset = 0;

	public boolean calibFL() {
		switch (flState) {
		case 0:
			if (!frontLeftMag.get()) {
				this.LeftFrontTurn.set(0);
				this.LeftFrontEn.reset(flOffset);
				flState += 1;
			}
			return false;
		case 1:
			return true;
		default:
			return false;
		}

	}

	int frState = 0;
	int frOfsett = 0;

	public boolean calibFR() {
		switch (frState) {
		case 0:
			if (!frontLeftMag.get()) {
				this.RightFrontTurn.set(0);
				this.RightFrontEn.reset(frOfsett);
				frState += 1;
			}
			return false;
		case 1:
			return true;
		default:
			return false;
		}

	}

	int blState = 0;
	int blOffset = 0;

	public boolean calibBL() {
		switch (blState) {
		case 0:
			if (!backLeftMag.get()) {
				this.LeftBackTurn.set(0);
				this.LeftBackEn.reset(blOffset);
				blState += 1;
			}
			return false;
		case 1:
			return true;
		default:
			return false;
		}

	}

	int brState = 0;
	int brOfsett = 0;

	public boolean calibBR() {
		switch (brState) {
		case 0:
			if (!backRightMag.get()) {
				this.RightBackTurn.set(0);
				this.RightBackEn.reset(brOfsett);
				brState += 1;
			}
			return false;
		case 1:
			return true;
		default:
			return false;
		}

	}

	public void calculations() {
		this.calculateTurnEcnValues();
	}

	public void calculateTurnEcnValues() {
		ActDistanceLF = Val(LeftFrontEn.getDistance());
		ActDistanceRF = Val(RightFrontEn.getDistance());
		ActDistanceRB = Val(RightBackEn.getDistance());
		ActDistanceLB = Val(LeftBackEn.getDistance());
	}

	double WheelLFAOld = 0;
	double WheelLBAOld = 0;
	double WheelRBAOld = 0;
	double WheelRFAOld = 0;

	double oldForward = 0;
	double oldStrafe = 0;
	double oldRotate = 0;

	public void teleopPeriodic() {

		Forward = -Deadband(DriveJoy.getRightY(), .01);
		Strafe = Deadband(DriveJoy.getRightX(), .01);
		Rotate = Deadband(DriveJoy.getLeftX(), .01);

		if (Forward == 0) {
			Forward = oldForward;
		}
		if (Strafe == 0) {
			Strafe = oldStrafe;
		}
		if (Rotate == 0) {
			Rotate = oldRotate;
		}

		// System.out.println(LeftFrontEn == null);

		calculateTurnEcnValues();
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

		switch (driveState) {
		case RUNNING:

			if (DriveJoy.getTriggerLeft() > .5) {
				/*
				 * WheelLFS = Deadband(DriveJoy.getLeftY(),.1); WheelRFS =
				 * Deadband(DriveJoy.getLeftY(),.1); WheelRBS =
				 * Deadband(DriveJoy.getRightY(),.1); WheelLBS =
				 * Deadband(DriveJoy.getRightY(),.1); LeftFrontTurn.set(Turn(.5,
				 * ActDistanceLF)); RightFrontTurn.set(Turn(.5, ActDistanceRF));
				 * RightBackTurn.set(Turn(.5, ActDistanceRB));
				 * LeftBackTurn.set(Turn(.5, ActDistanceLB));
				 */

				LeftFrontTurn.set(Turn(WheelLFA, ActDistanceLF));
				RightFrontTurn.set(Turn(WheelRFA, ActDistanceRF));
				RightBackTurn.set(Turn(WheelRBA, ActDistanceRB));
				LeftBackTurn.set(Turn(WheelLBA, ActDistanceLB));

			} else {
				WheelLFA = Math.atan2(B, D) * Count;
				WheelRFA = Math.atan2(B, C) * Count;
				WheelRBA = Math.atan2(A, C) * Count;
				WheelLBA = Math.atan2(A, D) * Count;
				LeftFrontTurn.set(Turn(WheelLFA, ActDistanceLF));
				RightFrontTurn.set(Turn(WheelRFA, ActDistanceRF));
				RightBackTurn.set(Turn(WheelRBA, ActDistanceRB));
				LeftBackTurn.set(Turn(WheelLBA, ActDistanceLB));

			}
			if (DriveJoy.getTriggerRight() < .3) {
				if (DriveJoy.getRawButton(10)) {
					LeftFrontDrive.set(Speed(WheelLFS));
					RightFrontDrive.set(Speed(WheelRFS));
					RightBackDrive.set(Speed(WheelRBS));
					LeftBackDrive.set(Speed(WheelLBS));
				} else if (DriveJoy.getRawButton(DriveJoy.BUTTON_RT)) {
					LeftFrontDrive.set(Speed(-.2));
					RightFrontDrive.set(Speed(-.2));
					RightBackDrive.set(Speed(-.2));
					LeftBackDrive.set(Speed(-.2));
					System.out.println("Button");
				} else if (DriveJoy.getRawButton(DriveJoy.BUTTON_LT)) {
					LeftFrontDrive.set(Speed(.2));
					RightFrontDrive.set(Speed(.2));
					RightBackDrive.set(Speed(.2));
					LeftBackDrive.set(Speed(.2));
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
			if (DriveJoy.getRawButton(DriveJoy.BUTTON_B)) {
				driveState = CALIB_INIT;
			}
			break;
		case CALIBRATING:
			
			if(this.calibFL()){
				driveState = RUNNING;
			}

			break;
		case CALIB_INIT:
			flState = 0;
			this.LeftFrontTurn.set(.4);
			this.driveState = CALIBRATING;
			
			break;
		}

		oldRotate = Rotate;
		oldStrafe = Strafe;
		oldForward = Forward;

		SmartDashboard.putString("DB/String 0",
				String.valueOf(RightFrontEn.getDistance()));

	}

	boolean spin = true;

	public void testPeriodic() {

		CSL = TopLightL.getValue();
		MSL = MidLightL.getValue();
		RSL = BotLightL.getValue();
		CSR = TopLightR.getValue();
		MSR = MidLightR.getValue();
		RSR = BotLightR.getValue();

		ActDistanceLF = Val(LeftFrontEn.getDistance());
		ActDistanceLB = Val(LeftBackEn.getDistance());
		ActDistanceRF = Val(RightFrontEn.getDistance());
		ActDistanceRB = Val(RightBackEn.getDistance());
		Angle = SmartDashboard.getDouble("DB/Slider 2");
		// FollowLine();

		SmartDashboard.putString("DB/String 0", "CSL " + String.valueOf(CSL));
		SmartDashboard.putString("DB/String 1", "MSL " + String.valueOf(MSL));
		SmartDashboard.putString("DB/String 2", "RSL " + String.valueOf(RSL));
		SmartDashboard.putString("DB/String 3", "CSR " + String.valueOf(CSR));
		SmartDashboard.putString("DB/String 4", "MSR " + String.valueOf(MSR));
		SmartDashboard.putString("DB/String 5", "RSR " + String.valueOf(RSR));
		SmartDashboard.putString("DB/String 6", "L " + String.valueOf(L));
		SmartDashboard.putString("DB/String 7", "R " + String.valueOf(R));
		SmartDashboard.putString("DB/String 9", "LF " + String.valueOf(LF));
		SmartDashboard.putString("DB/String 8", "RF " + String.valueOf(RF));
		/*
		 * SmartDashboard.putString("DB/String 3",
		 * String.valueOf(LeftFrontEn.getDistance()));
		 * if(this.frontLeftMag.get()){ this.LeftFrontTurn.set(.7); }else{
		 * this.LeftFrontEn.reset(); this.LeftFrontTurn.set(0); }
		 */
	}

	public void disableInit() {

	}

}
