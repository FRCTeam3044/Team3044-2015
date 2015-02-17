package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.DriverStation.DriverController;
import org.usfirst.frc.team3044.utils.Components;
import org.usfirst.frc.team3044.utils.Utilities;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends IterativeRobot {
	Arm arm = new Arm();
	Drive drive = new Drive();
	Forklift forklift = new Forklift();
	Components components = Components.getInstance();
	Utilities utils = new Utilities();

	DriverController driverController = DriverController.getInstance();

	DriverStation ds = DriverStation.getInstance();

	int session;
	Image frame;

	double lastUpdateTime = 15;
	double currentTime = 0;
	
	int autoState = 0;
	

	public void robotInit() {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		session = NIVision.IMAQdxOpenCamera("cam1",
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(session);

		components.init();
		arm.robotInit();
		drive.DriveInit();
		drive.robotInit();
		components.compressor.setClosedLoopControl(true);

		forklift.robotInit();

	}

	public void autonomousInit() {
		arm.autoInit();
		drive.teleopInit();
		// components.init(); - test
		forklift.autoInit();
	}

	public void autonomousPeriodic() {
		currentTime = Timer.getMatchTime();
		if (Math.abs(currentTime - lastUpdateTime) > .066) {
			NIVision.IMAQdxGrab(session, frame, 1);
			CameraServer.getInstance().setImage(frame);
			lastUpdateTime = currentTime;
			
		}

		arm.teleopPeriodic();
		drive.teleopPeriodic();
		forklift.forkliftPeriodic();
		
		switch(autoState){
		case 0:
			driverController.buttonFour = true;
			autoState ++;
			break;
		case 1:
			if(!components.forkliftTote.get()){
				driverController.buttonFour = false;
				autoState = -1;
			}
			break;
		}

	}

	public void teleopInit() {
		arm.teleopInit();
		drive.teleopInit();
		forklift.teleopInit();
		components.compressor.stop();
	}

	public void teleopPeriodic() {
		currentTime = Timer.getMatchTime();
		if (Math.abs(currentTime - lastUpdateTime) > .066) {
			NIVision.IMAQdxGrab(session, frame, 1);
			CameraServer.getInstance().setImage(frame);
			lastUpdateTime = currentTime;
		}

		
		arm.teleopPeriodic();
		drive.teleopPeriodic();

		if (driverController.getRawButton(7)) {
			components.compressor.stop();
		} else if (driverController.getRawButton(8)) {
			components.compressor.setClosedLoopControl(true);
			components.compressor.start();
		}
		forklift.forkliftPeriodic();
	}

	public void testPeriodic() {
		components.compressor.stop();
		drive.testPeriodic();
	}

	public void disabledInit() {
		// arm.disabled();
		drive.disableInit();
		forklift.disabled();
	}

	public void disabledPeriodic() {

		NIVision.IMAQdxGrab(session, frame, 1);
		CameraServer.getInstance().setImage(frame);
		lastUpdateTime = currentTime;

	}

}
