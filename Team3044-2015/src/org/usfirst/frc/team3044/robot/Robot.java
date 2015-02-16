
package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.DriverStation.DriverController;
import org.usfirst.frc.team3044.utils.Components;
import org.usfirst.frc.team3044.utils.Utilities;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;


public class Robot extends IterativeRobot {
	Arm arm = new Arm();
	Drive drive = new Drive();
	Forklift forklift = new Forklift();
	Components components = Components.getInstance();
	Utilities utils = new Utilities();
	
	DriverController secondaryController = DriverController.getInstance();
	CameraServer server = CameraServer.getInstance();
	
    public void robotInit() {
        
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam1");
    	components.init();
    	arm.robotInit();
    	drive.DriveInit();
    	drive.robotInit();
    	components.compressor.setClosedLoopControl(true);
    	
    	forklift.robotInit();
    	
    }

    public void autonomousInit(){
    	arm.autoInit();
    	drive.teleopInit();
    	//components.init(); - test
    	forklift.autoInit();
    }
    
    public void autonomousPeriodic() {
    	arm.teleopPeriodic();
    	drive.autonomousPeriodic();
    	
    	forklift.forkliftPeriodic();
    }


    public void teleopInit(){
    	arm.teleopInit();
    	drive.teleopInit();
    	forklift.teleopInit();
    	components.compressor.stop();
    }
    
    public void teleopPeriodic() {
    	arm.teleopPeriodic();
    	drive.teleopPeriodic();
    	
    	if(secondaryController.getRawButton(7)){
    		components.compressor.stop();
    	}else if(secondaryController.getRawButton(8)){
    		components.compressor.setClosedLoopControl(true);
    		components.compressor.start();
    	}
    	forklift.forkliftPeriodic();
    }
    

    public void testPeriodic() {
    	drive.testPeriodic();
    }
    
    public void disabledInit(){
    	//arm.disabled();
    	drive.disableInit();
    	forklift.disabled();
    }
    
}
