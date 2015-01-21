package org.usfirst.frc.team3044.utils;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;

public class Components {
	
	 /* This class is intended to store all of the objects for components of the robot
	 ex. Sensors, motor controllers, etc,
	 */
	
	public static CANTalon frontRightDriveRot;
	public static CANTalon frontLeftDriveRot;
	public static CANTalon backRightDriveRot;
	public static CANTalon backLeftDriveRot;
	
	public static CANJaguar frontRightDrive;
	public static CANJaguar frontLeftDrive;
	public static CANJaguar backRightDrive;
	public static CANJaguar backLeftDrive;
	
	public static CANJaguar forkliftLeft1;
	public static CANJaguar forkliftLeft2;
	public static CANJaguar forkliftRight1;
	public static CANJaguar forkliftRight3;
	
	public static CANJaguar armMotor;
	
	public static DigitalInput forkliftUp = new DigitalInput(0);
	public static DigitalInput forkliftDown = new DigitalInput(1);
	
	public static Solenoid forkliftLift = new Solenoid(0);
	public static Solenoid armSolenoid = new Solenoid(1);
	
	public static DigitalInput ArmExtended = new DigitalInput(2);
	public static DigitalInput ArmRetracted = new DigitalInput(3);
	
	public final static int ARM_OUT = 1;
	public final static int ARM_IN = 5;
	
	
	public final static int FORK_OUT = 1;
	public final static int FORK_IN = 3;
	
	
	public final static int ArmButtonOut = 1;
	public final static int ArmButtonIn = 2;
	
	

	public static PowerDistributionPanel powerDistribution = new PowerDistributionPanel();
	public static AnalogInput LightSensorFrontLeft = new AnalogInput(0);
	public static AnalogInput LightSensorFrontMid = new AnalogInput(1);
	public static AnalogInput LightSensorFrontRight = new AnalogInput(2);
	public static AnalogInput LightSensorBackLeft = new AnalogInput(3);
	public static AnalogInput LightSensorBackMid = new AnalogInput(4);
	public static AnalogInput LightSensorBackRight = new AnalogInput(5);
	
	public static Encoder driveEncoderFR = new Encoder(1, 6);
	public static Encoder driveEncoderBR = new Encoder(1, 7);
	public static Encoder driveEncoderFL = new Encoder(1, 8);
	public static Encoder driveEncoderBL = new Encoder(1, 9);
	
	public static Encoder rotEncoderFR = new Encoder(1, 10);
	public static Encoder rotEncoderBR = new Encoder(1, 11);
	public static Encoder rotEncoderFL = new Encoder(1, 12);
	public static Encoder rotEncoderBL = new Encoder(1, 13);
	
	public static void init(){
		
		
	}

}
