package org.usfirst.frc.team3044.utils;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;

public class Components {
	private Components(){
		
	}
	
	private static Components instance;
	
	public static Components getInstance(){
		if(instance == null){
			instance = new Components();
		}
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
	
	public DigitalInput forkliftUp = new DigitalInput(0);
	public DigitalInput forkliftDown = new DigitalInput(1);
	
	public Solenoid forkliftClamp2 = new Solenoid(PCM_ID, 0);
	public Solenoid armSolenoid = new Solenoid(PCM_ID,1);
	public Solenoid forkliftClamp = new Solenoid(PCM_ID,3);
	
	public DigitalInput ArmExtended = new DigitalInput(2);
	public DigitalInput ArmRetracted = new DigitalInput(3);
	
	public DigitalInput armScrewOut = new DigitalInput(5);
	public DigitalInput armScrewIn = new DigitalInput(4);
	
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
	
	
	
	public DigitalInput driveRotationFRMag = new DigitalInput(7);
	public DigitalInput driveRotationBRMag = new DigitalInput(9);
	public DigitalInput driveRotationFLMag = new DigitalInput(6);
	public DigitalInput driveRotationBLMag = new DigitalInput(8);
	
	public Encoder rotEncoderFR = new Encoder(-1);
	public Encoder rotEncoderBR = new Encoder(-1);
	public Encoder rotEncoderFL = new Encoder(-1);
	public Encoder rotEncoderBL = new Encoder(-1);
	
	public DigitalInput proximityFR = new DigitalInput(0);
	public DigitalInput proximityBR = new DigitalInput(0);
	public DigitalInput proximityAbsFL = new DigitalInput(0);
	public DigitalInput proximityAbsBL = new DigitalInput(0);	
	
	AnalogInput winchPot = new AnalogInput(-1);
	
	
	public void init(){
		frontRightDriveRot = new CANTalon(12);
		frontLeftDriveRot = new CANTalon(11);
		backRightDriveRot = new CANTalon(14);
		backLeftDriveRot = new CANTalon(13);
		
		frontRightDrive = new CANTalon(8);
		frontLeftDrive = new CANTalon(7);
		backRightDrive = new CANTalon(10);
		backLeftDrive = new CANTalon(9);
		backLeftDriveRot.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		forkliftLeft1 = new CANJaguar(15);
		forkliftLeft2 = new CANJaguar(16);
		forkliftRight1 = new CANJaguar(17);
		forkliftRight2 = new CANJaguar(18);
		screwMotor = new CANJaguar(20);
		winchMotor = new CANJaguar(21);
		
		
	}

}
