package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.DriverStation.DriverController;
import org.usfirst.frc.team3044.DriverStation.SecondaryController;
import org.usfirst.frc.team3044.utils.AnalogEncoderWrapper;
import org.usfirst.frc.team3044.utils.Components;
import org.usfirst.frc.team3044.utils.Utilities;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	Arm arm = new Arm();
	Drive drive = new Drive();
	Forklift forklift = new Forklift();
	Components components = Components.getInstance();
	Utilities utils = new Utilities();
	Image frame;
	DriverController driverController = DriverController.getInstance();
	SecondaryController secondaryController = SecondaryController.getInstance();

	DriverStation ds = DriverStation.getInstance();

	int session;

	double lastUpdateTime = 15;
	double currentTime = 0;

	int driveAutoState = 0;
	final int INIT_DRIVE = 0;
	final int DRIVE = 1;
	final int STOPPED = 2;

	double startTime = 0;

	int toteStackAutoState = 0;
	int armAutoState = 0;
	int count = 0;

	AnalogEncoderWrapper armEncoder;

	int autoState = 0;
	boolean turn = false;

	public void robotInit() {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		// the camera name (ex "cam0") can be found through the roborio web
		// interface
		session = NIVision.IMAQdxOpenCamera("cam1",
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(session);
		components.init();
		arm.robotInit();
		drive.DriveInit();
		drive.robotInit();
		forklift.robotInit();
		armEncoder = new AnalogEncoderWrapper(components.leadscrewEncoder);
	}

	public void autonomousInit() {
		this.autoState = 0;
		drive.Init();
		arm.autoInit();
		// drive.();
		// components.compressor.stop();
		driveAutoState = 0;
		armAutoState = 0;
		this.oneContainerAutoState = 0;
		// components.init(); - test
		forklift.autoInit();
		this.oneContainerAuto = SmartDashboard.getBoolean("DB/Button 1",true);
	}

	boolean driveAuto = true;
	private boolean oneContainerAuto;

	public void autonomousPeriodic() {
		currentTime = Timer.getMatchTime();
		NIVision.IMAQdxGrab(session, frame, 1);
		System.out.println("image put");
        
        CameraServer.getInstance().setImage(frame);

		count += 1;

		arm.teleopPeriodic();
		drive.teleopPeriodic();
		if (oneContainerAuto) {
			this.oneContainerNoDrop();
		} else {
			this.armAutonCenter();
		}

		if (SmartDashboard.getBoolean("DB/Button 2",true)) {
			this.turn = false;
		} else {
			this.turn = true;
		}
		if (SmartDashboard.getBoolean("DB/Button 3", false)) {
			this.driveAuto = true;
		} else {
			this.driveAuto = false;
		}

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
		// drive.teleopInit();
		forklift.teleopInit();
		drive.Init();

	}

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

	}

	boolean shouldTurn = true;

	public void testPeriodic() {

		System.out.println(components.leadscrewEncoder.getVoltage());
		armEncoder.step();
		arm.teleopPeriodic();/*
							 * if(!this.components.driveRotationBLMag.get()){
							 * shouldTurn = false;
							 * components.rotEncoderBL.reset(); } if
							 * (shouldTurn) {
							 * this.components.backLeftDriveRot.set(.4); }else{
							 * this.components.backLeftDriveRot.set(0); }
							 */
		SmartDashboard.putString("DB/String 0",
				String.valueOf(this.components.winchPot.getVoltage()));
		SmartDashboard.putString("DB/String 1",
				String.valueOf(components.ArmRetracted.get()));
		//this.armAutonCenter();

	}

	public void disabledInit() {
		// arm.disabled();
		drive.disableInit();
		forklift.disabled();
		
	}

	public void disabledPeriodic() {
		NIVision.IMAQdxGrab(session, frame, 1);
		System.out.println("image put");
        System.out.println(NetworkTable.getTable("Camera").getNumber("X",0));
        
        CameraServer.getInstance().setImage(frame);
		lastUpdateTime = currentTime;

	}

	int oneContainerAutoState = 0;
	double initTime = 0;

	public void oneContainerNoDrop() {
		oneContainerAutonomous(false);
	}

	public void oneContainerAuto() {
		oneContainerAutonomous(true);
	}

	public void oneContainerAutonomous(boolean drop) {
		switch (oneContainerAutoState) {
		case 0:
			driverController.buttonSix = true;
			this.oneContainerAutoState = 10;
			initTime = Timer.getMatchTime();
			driverController.buttonTwo = true;
			break;
		case 10:

			driverController.buttonTwo = false;
			this.oneContainerAutoState = 1;

			break;
		case 1:
			if (!components.forkliftTote.get()) {
				if (!this.driveAuto) {
					this.oneContainerAutoState = 20;
				} else {
					this.oneContainerAutoState = 2;
				}
				driverController.buttonSix = false;// secondaryController.buttonFour
													// = false;
			}

			break;
		case 2:
			if (drive.wheelsCalibrated) {
				this.oneContainerAutoState = 3;
				driverController.buttonTwo = false;
				drive.Forward = -.8;
				initTime = Timer.getFPGATimestamp();
				// Clutch
			}
			break;
		case 3:
			if (Timer.getFPGATimestamp() - initTime > 3) {
				drive.Forward = 0;
				this.oneContainerAutoState = 4;
				if (!turn) {
					drive.Rotate = -.5;
				} else {
					drive.Rotate = .5;
				}
				initTime = Timer.getFPGATimestamp();
			}
			break;
		case 4:
			if (Timer.getFPGATimestamp() - initTime > 2) {
				drive.Rotate = 0;

				this.oneContainerAutoState = 5;
				if (drop) {
					driverController.buttonFive = true;
				}
			}
			break;
		case 5:
			if (!this.components.forkliftDown.get() || !drop) {
				driverController.buttonFive = false;
				// drive.Forward = -.5;
				initTime = Timer.getFPGATimestamp();
				this.oneContainerAutoState = 6;
			}
			break;
		case 6:
			if (Timer.getFPGATimestamp() - initTime > 2) {
				drive.Forward = 0;

			}
			break;

		}

	}

	public void toteStackAuto() {
		switch (toteStackAutoState) {
		case 0:
			components.rotEncoderBL.reset();
			components.rotEncoderBR.reset();
			components.rotEncoderFL.reset();
			components.rotEncoderFR.reset();
			drive.Forward = .8;
			initTime = Timer.getFPGATimestamp();
			toteStackAutoState = 1;
			break;
		case 1:
			if (Timer.getFPGATimestamp() - initTime > 1) {
				drive.Forward = 0;
				driverController.buttonSix = true;
				toteStackAutoState = 2;
			}
			break;
		case 2:
			if (!components.forkliftTote.get()) {
				driverController.buttonSix = false;
				drive.Forward = -.5;
				initTime = Timer.getFPGATimestamp();
				toteStackAutoState = 3;
			}
			break;
		case 3:
			if (Timer.getFPGATimestamp() - initTime > 5) {
				drive.Forward = 0;

			}
			break;
		}

	}

	int oneToteState = 0;

	public void oneToteIntoZone() {
		switch (oneToteState) {
		case 0:
			drive.Strafe = .7;
			initTime = Timer.getFPGATimestamp();
			oneToteState = 0;
			break;
		case 1:
			if (initTime - Timer.getFPGATimestamp() > 2.7) {
				drive.Strafe = 0;

				break;
			}
		}

	}

	public void armAutonCenter() {
		switch (armAutoState) {
		case 0:
			secondaryController.buttonFive = true;
			// secondaryController.buttonSeven = true;
			armAutoState = 1;
			initTime = Timer.getFPGATimestamp();
			break;

		case 1:
			if (components.winchPot.getVoltage() < 2.45) {
				secondaryController.buttonFive = false;
				secondaryController.buttonSeven = true;
				this.armAutoState = 2;

			}

			break;

		case 2:
			if (!components.ArmExtended.get()) {
				if (driveAuto) {
					secondaryController.buttonSeven = false;
					secondaryController.buttonSix = true;
					this.armAutoState = 3;
				} else {
					secondaryController.buttonSeven = false;
					secondaryController.buttonSix = false;

					driverController.buttonTwo = true;
					this.armAutoState = 1234;
					initTime = Timer.getMatchTime();

					break;

				}

			}
			break;
		case 1234:
			driverController.buttonTwo = false;
			break;

		case 3:
			if (!components.ArmExtended.get()) {
				if (driveAuto) {
					secondaryController.buttonSeven = false;
					secondaryController.buttonSix = true;
					driverController.buttonTwo = true;
					this.armAutoState = 4;
				} else {
					secondaryController.buttonSeven = false;
					secondaryController.buttonSix = false;
					this.armAutoState = 1000;

				}

			}
			break;
		case 4:
			if (components.winchPot.getVoltage() > 2.95) {
				secondaryController.buttonSix = false;
				secondaryController.buttonEight = true;
				driverController.buttonTwo = false;
				initTime = Timer.getFPGATimestamp();
				this.armAutoState = 5;
			}
			break;
		case 5:
			if (!components.ArmRetracted.get()) {
				this.armAutoState = 8;
				secondaryController.buttonEight = false;

			}
			break;
		}
	}

	public void armAuto() {
		switch (armAutoState) {
		case 0:
			secondaryController.buttonFive = true;
			secondaryController.buttonSeven = true;
			armAutoState += 1;
			break;
		case 2:
			if (components.winchPot.getVoltage() < 2.68) {
				secondaryController.buttonFive = false;

			}
			if (components.encoderScrew.getRaw() < -8200) {
				secondaryController.buttonSeven = false;

			}

			if (components.winchPot.getVoltage() < 2.68
					&& components.encoderScrew.getRaw() < -8200) {
				secondaryController.buttonFive = true;
				armAutoState = 3;
			}
			break;
		case 1:
			if (components.winchPot.getVoltage() < 2.68) {
				secondaryController.buttonFive = false;

			}
			if (components.encoderScrew.getRaw() < -8200) {
				secondaryController.buttonSeven = false;

			}

			if (components.winchPot.getVoltage() < 2.68
					&& components.encoderScrew.getRaw() < -8200) {
				secondaryController.buttonFive = true;
				armAutoState = 3;
			}
			break;
		case 3:
			if (components.winchPot.getVoltage() < 2.4) {
				secondaryController.buttonFive = false;
				secondaryController.buttonSeven = true;
				armAutoState += 1;
			}
			break;
		case 4:
			if (components.encoderScrew.getRaw() < -13500) {
				secondaryController.buttonSeven = false;
				secondaryController.buttonSix = true;
				armAutoState += 1;
			}
			break;
		case 5:

			if (components.winchPot.getVoltage() > 2.65) {
				secondaryController.buttonSix = false;
				secondaryController.buttonEight = true;
				armAutoState += 1;
			}

			break;
		case 6:
			if (components.encoderScrew.getRaw() > -100) {
				secondaryController.buttonEight = false;
			}
		}
	}

}
