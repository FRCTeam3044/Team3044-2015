package org.usfirst.frc.team3044.utils;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
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
	
	 /* This class is intended to store all of the objects for components of the robot
	 ex. Sensors, motor controllers, etc,
	 */  
	
	public CANTalon frontRightDriveRot;
	public CANTalon frontLeftDriveRot;
	public CANTalon backRightDriveRot;
	public CANTalon backLeftDriveRot;
	
	public CANJaguar frontRightDrive;
	public CANJaguar frontLeftDrive;
	public CANJaguar backRightDrive;
	public CANJaguar backLeftDrive;
	
	public CANJaguar forkliftLeft1;
	public CANJaguar forkliftLeft2;
	public CANJaguar forkliftRight1;
	public CANJaguar forkliftRight2;
	
	public CANJaguar winchMotor;
	public CANJaguar screwMotor;
	
	public DigitalInput forkliftUp = new DigitalInput(0);
	public DigitalInput forkliftDown = new DigitalInput(1);
	
	
	public Solenoid forkliftClamp2 = new Solenoid(0);
	public Solenoid armSolenoid = new Solenoid(1);
	public Solenoid forkliftClamp = new Solenoid(3);
	
	public DigitalInput ArmExtended = new DigitalInput(2);
	public DigitalInput ArmRetracted = new DigitalInput(3);
	
	public DigitalInput armScrewOut = new DigitalInput(1);
	public DigitalInput armScrewIn = new DigitalInput(2);
	
	public DigitalInput forkliftTote = new DigitalInput(-1);

	public Encoder encoderScrew = new Encoder(0,0);
	public Encoder encoderWinch = new Encoder(0,0);

	
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
	
	
	
	public Encoder driveEncoderFR = new Encoder(1, 6);
	public Encoder driveEncoderBR = new Encoder(1, 7);
	public Encoder driveEncoderFL = new Encoder(1, 8);
	public Encoder driveEncoderBL = new Encoder(1, 9);
	
	public Encoder rotEncoderFR = new Encoder(1, 10);
	public Encoder rotEncoderBR = new Encoder(1, 11);
	public Encoder rotEncoderFL = new Encoder(1, 12);
	public Encoder rotEncoderBL = new Encoder(1, 13);
	
	public DigitalInput proximityFR = new DigitalInput(0);
	public DigitalInput proximityBR = new DigitalInput(0);
	public DigitalInput proximityAbsFL = new DigitalInput(0);
	public DigitalInput proximityAbsBL = new DigitalInput(0);	
	
	AnalogInput winchPot = new AnalogInput(-1);
	
	
	public void init(){
		frontRightDriveRot = new CANTalon(0);
		frontLeftDriveRot = new CANTalon(0);
		backRightDriveRot = new CANTalon(0);
		backLeftDriveRot = new CANTalon(0);
		
		frontRightDrive = new CANJaguar(0);
		frontLeftDrive = new CANJaguar(0);
		backRightDrive = new CANJaguar(0);
		backLeftDrive = new CANJaguar(0);
		
		forkliftLeft1 = new CANJaguar(0);
		forkliftLeft2 = new CANJaguar(0);
		forkliftRight1 = new CANJaguar(0);
		forkliftRight2 = new CANJaguar(0);
		screwMotor = new CANJaguar(0);
		winchMotor = new CANJaguar(0);
		
		
	}

}
