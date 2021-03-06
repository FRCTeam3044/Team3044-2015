package org.usfirst.frc.team3044.utils;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.StatusFrameRate;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
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
	
	public final int PCM_ID = 25;
	
	public final int FLMAGPOS = 10;
	public final int BLMAGPOS = 13;
	
	
	 /* This class is intended to store all of the objects for components of the robot
	 ex. Sensors, motor controllers, etc,
	 */  
	
	//public Compressor compressor = new Compressor(PCM_ID);

	public CANTalon frontRightDriveRot;
	public CANTalon frontLeftDriveRot;
	public CANTalon backRightDriveRot;
	public CANTalon backLeftDriveRot;
	
	public CANTalon frontRightDrive;
	public CANTalon frontLeftDrive;
	public CANTalon backRightDrive;
	public CANTalon backLeftDrive;
	
	//*********************************************************
	/*
	public CANJaguar frontRightDriveRot;
	public CANJaguar frontLeftDriveRot;
	public CANJaguar backRightDriveRot;
	public CANJaguar backLeftDriveRot;
	
	public CANJaguar frontRightDrive;
	public CANJaguar frontLeftDrive;
	public CANJaguar backRightDrive;
	public CANJaguar backLeftDrive;
	*/
	public CANJaguar forkliftLeft1;
	public CANJaguar forkliftLeft2;
	public CANJaguar forkliftRight1;
	public CANJaguar forkliftRight2;
	/*
	public Encoder leftFrontTurn = new Encoder(6,7);
	public Encoder rightFrontTurn = new Encoder(8,9);
	public Encoder leftBackTurn = new Encoder(2,3);//(18,19);
	public Encoder rightBackTurn = new Encoder(20,21);
	*/
	//*********************************************************
	public Jaguar winchMotor;
	public Jaguar screwMotor;
	
	public DigitalInput forkliftUp = new DigitalInput(0);
	public DigitalInput forkliftDown = new DigitalInput(1);
	
	//public Solenoid forkliftClamp2 = new Solenoid(PCM_ID, 0);
	//public Solenoid forkliftClamp = new Solenoid(PCM_ID,1);
	//public Solenoid armSolenoid = new Solenoid(PCM_ID,3);
	
	public DigitalInput ArmExtended = new DigitalInput(18);
	public DigitalInput ArmRetracted = new DigitalInput(9);// = new DigitalInput(3);
	
	public DigitalInput armScrewOut = new DigitalInput(22);//= new DigitalInput(7);
	public DigitalInput armScrewIn;// = new DigitalInput(4);
	
	
	public DigitalInput forkliftTote = new DigitalInput(2);

	public Encoder encoderScrew = new Encoder(4,5);
	public AnalogInput winchPot = new AnalogInput(1);

	
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
	
	public AnalogInput LightSensorFrontLeft;/* = new AnalogInput(3);*/
	public AnalogInput LightSensorFrontMid;/* = new AnalogInput(2);*/
	public AnalogInput LightSensorFrontRight;/* = new AnalogInput(1);*/
	public AnalogInput LightSensorBackLeft;/* = new AnalogInput(6);*/
	public AnalogInput LightSensorBackMid;/* = new AnalogInput(5);*/
	public AnalogInput LightSensorBackRight;/* = new AnalogInput(4);*/
	
	public AnalogInput leadscrewEncoder = new AnalogInput(5);// = new AnalogInput(3);
	public AnalogInput ultrasonicSensor = new AnalogInput(3);
	
	public DigitalInput frontRightMag = new DigitalInput(15);//13// = new DigitalInput(7);
	public DigitalInput backRightMag = new DigitalInput(13);//12// = new DigitalInput(9);
	public DigitalInput frontLeftMag = new DigitalInput(14);//15// = new DigitalInput(6);
	public DigitalInput backLeftMag = new DigitalInput(12);//14// = new DigitalInput(8);
	
	/*
	public TalonEncoder rotEncoderFR;
	public TalonEncoder rotEncoderBR;
	public TalonEncoder rotEncoderFL;
	public TalonEncoder rotEncoderBL;
	*/
	
	public TalonEncoder rotEncoderFR;
	public TalonEncoder rotEncoderBR;
	public TalonEncoder rotEncoderFL;
	public TalonEncoder rotEncoderBL;
	
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
	
	//AnalogInput winchPot;/// = new AnalogInput(-1);
	
	
	public void init(){
		//Set CANTalonIDs
		
		frontRightDriveRot = /*new CANJaguar(13);//*/new CANTalon(19);
		frontLeftDriveRot = /*new CANJaguar(12);//*/new CANTalon(18);
		backRightDriveRot = /*new CANJaguar(3);//*/new CANTalon(21);
		backLeftDriveRot = /*new CANJaguar(4);//*/new CANTalon(20);
		
		frontRightDriveRot.setStatusFrameRateMs(StatusFrameRate.QuadEncoder, 10);
		backRightDriveRot.setStatusFrameRateMs(StatusFrameRate.QuadEncoder, 10);
		frontLeftDriveRot.setStatusFrameRateMs(StatusFrameRate.QuadEncoder, 10);
		backLeftDriveRot.setStatusFrameRateMs(StatusFrameRate.QuadEncoder, 10);
		
		frontRightDriveRot.setStatusFrameRateMs(StatusFrameRate.General, 10);
		backRightDriveRot.setStatusFrameRateMs(StatusFrameRate.General, 10);
		frontLeftDriveRot.setStatusFrameRateMs(StatusFrameRate.General, 10);
		backLeftDriveRot.setStatusFrameRateMs(StatusFrameRate.General, 10);
		
		frontRightDriveRot.setStatusFrameRateMs(StatusFrameRate.Feedback, 10);
		backRightDriveRot.setStatusFrameRateMs(StatusFrameRate.Feedback, 10);
		frontLeftDriveRot.setStatusFrameRateMs(StatusFrameRate.Feedback, 10);
		backLeftDriveRot.setStatusFrameRateMs(StatusFrameRate.Feedback, 10);
		
		
		frontRightDrive = /*new CANJaguar(15);//*/new CANTalon(15);
		frontLeftDrive =  /*new CANJaguar(14);//*/new CANTalon(14);
		backRightDrive = /*new CANJaguar(11);//*/new CANTalon(17);
		backLeftDrive = /*new CANJaguar(16);//*/new CANTalon(16);
		
		
		rotEncoderFR  = /*new TalonEncoder(this.rightFrontTurn);//*/new TalonEncoder(frontRightDriveRot);
		rotEncoderBR = /*new TalonEncoder(this.rightBackTurn);//*/new TalonEncoder(backRightDriveRot);
		rotEncoderFL = /*new TalonEncoder(this.leftFrontTurn);//*/new TalonEncoder(frontLeftDriveRot);
		rotEncoderBL = /*new TalonEncoder(this.leftBackTurn);//*/new TalonEncoder(backLeftDriveRot);
		System.out.println("initialized");
		
		/*
		frontRightDrive.setFeedbackDevice(FeedbackDevice.EncRising);
		frontRightDrive.changeControlMode(ControlMode.Speed);
		*/
		
		forkliftLeft1 = new CANJaguar(7);
		forkliftLeft2 = new CANJaguar(8);
		forkliftRight1 = new CANJaguar(9);
		forkliftRight2 = new CANJaguar(10);
		
		screwMotor = new Jaguar(1);
		winchMotor = new Jaguar(0);
		
	
		backLeftDriveRot.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontLeftDriveRot.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		backRightDriveRot.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontRightDriveRot.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		

		
	}

}
