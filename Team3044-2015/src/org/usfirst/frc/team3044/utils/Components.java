package org.usfirst.frc.team3044.utils;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;

public class Components {
	
	private Components(){
		
	}
	public static int number = 0;
	private static Components instance;
	
	public static Components getInstance(){
		if(instance == null){
			instance = new Components();
		}
		number += 1;
		System.out.println(number);
		return instance;
	}
	
	public final int PCM_ID = 22;
	
	
	 /* This class is intended to store all of the objects for components of the robot
	 ex. Sensors, motor controllers, etc,
	 */  
	
	public CANTalon frontRightDriveRot;
	public CANTalon frontLeftDriveRot;
	public CANTalon backRightDriveRot;
	public CANTalon backLeftDriveRot;
	
	public CANTalon frontRightDrive;
	public CANTalon frontLeftDrive;
	public CANTalon backRightDrive;
	public CANTalon backLeftDrive;
	
	public CANJaguar forkliftLeft1;
	public CANJaguar forkliftLeft2;
	public CANJaguar forkliftRight1;
	public CANJaguar forkliftRight2;
	
	public CANJaguar winchMotor;
	public CANJaguar screwMotor;
	
	public DigitalInput forkliftUp;// = new DigitalInput(0);
	public DigitalInput forkliftDown;// = new DigitalInput(1);
	
	public Solenoid forkliftClamp2 = new Solenoid(PCM_ID, 0);
	public Solenoid armSolenoid = new Solenoid(PCM_ID,1);
	public Solenoid forkliftClamp = new Solenoid(PCM_ID,3);
	
	public DigitalInput ArmExtended;// = new DigitalInput(2);
	public DigitalInput ArmRetracted;// = new DigitalInput(3);
	
	public DigitalInput armScrewOut;// = new DigitalInput(5);
	public DigitalInput armScrewIn;// = new DigitalInput(4);
	
	public DigitalInput forkliftTote = new DigitalInput(24);

	public Encoder encoderScrew = new Encoder(22,23);
	public AnalogInput encoderWinch = new AnalogInput(7);

	
	public final int SCREW_OUT_BUTTON = 1;
	public final int SCREW_IN_BUTTON = 3;
	public final int PNEUMATIC_BUTTON = 2;
	public final int WINCH_UP_BUTTON = -1;
	public final int WINCH_DOWN_BUTTON = -1;
	public final int BOTH_IN_UP_BUTTON = -1;
	public final int BOTH_OUT_DOWN_BUTTON = -1;
	
	public final int FORK_OUT_BUTTON = 1;
	public final int FORK_IN_BUTTON = 3;
	public final int FORK_TOTE_BUTTON = -1;
	

	public PowerDistributionPanel powerDistribution = new PowerDistributionPanel();
	
	public AnalogInput LightSensorFrontLeft = new AnalogInput(0);
	public AnalogInput LightSensorFrontMid = new AnalogInput(1);
	public AnalogInput LightSensorFrontRight = new AnalogInput(2);
	public AnalogInput LightSensorBackLeft = new AnalogInput(3);
	public AnalogInput LightSensorBackMid = new AnalogInput(4);
	public AnalogInput LightSensorBackRight = new AnalogInput(5);
	
	
	
	public DigitalInput driveRotationFRMag;// = new DigitalInput(7);
	public DigitalInput driveRotationBRMag;// = new DigitalInput(9);
	public DigitalInput driveRotationFLMag;// = new DigitalInput(6);
	public DigitalInput driveRotationBLMag;// = new DigitalInput(8);
	
	/*
	public TalonEncoder rotEncoderFR;
	public TalonEncoder rotEncoderBR;
	public TalonEncoder rotEncoderFL;
	public TalonEncoder rotEncoderBL;
	*/
	
	public Encoder rotEncoderFR;
	public Encoder rotEncoderBR;
	public Encoder rotEncoderFL;
	public Encoder rotEncoderBL;
	
	/*
	 * 	Encoder LeftFrontEn = new Encoder(6,7);
	Encoder RightFrontEn = new Encoder(5,4);
	Encoder RightBackEn = new Encoder(2,3);
	Encoder LeftBackEn = new Encoder(0,1);
	 */
	
	public DigitalInput proximityFR;// = new DigitalInput(0);
	public DigitalInput proximityBR;// = new DigitalInput(0);
	public DigitalInput proximityAbsFL;// = new DigitalInput(0);
	public DigitalInput proximityAbsBL;// = new DigitalInput(0);	
	
	AnalogInput winchPot;/// = new AnalogInput(-1);
	
	
	public void init(){
		//Set CANTalonIDs
		frontRightDriveRot = new CANTalon(19);
		frontLeftDriveRot = new CANTalon(18);
		backRightDriveRot = new CANTalon(21);
		backLeftDriveRot = new CANTalon(20);
		
		
		frontRightDrive = new CANTalon(15);
		frontLeftDrive = new CANTalon(14);
		backRightDrive = new CANTalon(17);
		backLeftDrive = new CANTalon(18);
		
		
		rotEncoderFL  = new Encoder(5,4);
		rotEncoderBR = new Encoder(2,3);
		rotEncoderFL = new Encoder(6,7);
		rotEncoderBL = new Encoder(0,1);
		System.out.println("initialized");
		
		/*
		frontRightDrive.setFeedbackDevice(FeedbackDevice.EncRising);
		frontRightDrive.changeControlMode(ControlMode.Speed);
		*/
		
		//forkliftLeft1 = new CANJaguar(15);
		//forkliftLeft2 = new CANJaguar(16);
		//forkliftRight1 = new CANJaguar(17);
		//forkliftRight2 = new CANJaguar(18);
		
		//screwMotor = new CANJaguar(20);
		//winchMotor = new CANJaguar(21);
		
		/*
		backLeftDriveRot.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontLeftDriveRot.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		backRightDriveRot.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontRightDriveRot.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		*/
		
		/*
		rotEncoderFR = new TalonEncoder(frontRightDriveRot);
		rotEncoderFL = new TalonEncoder(frontLeftDriveRot);
		rotEncoderBR = new TalonEncoder(backRightDriveRot);
		rotEncoderBL = new TalonEncoder(backLeftDriveRot);		
		*/
	}

}
