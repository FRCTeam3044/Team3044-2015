
package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.DriverStation.DriverController;
import org.usfirst.frc.team3044.utils.Components;
import org.usfirst.frc.team3044.utils.Utilities;

import edu.wpi.first.wpilibj.IterativeRobot;


public class Robot extends IterativeRobot {
	//Arm arm = new Arm();
	Drive drive = new Drive();
	//Forklift forklift = new Forklift();
	Components components = Components.getInstance();
	Utilities utils = new Utilities();
	
	DriverController c = DriverController.getInstance();
	
	
    public void robotInit() {
    	components.init();
    	//arm.robotInit();
    	drive.DriveInit();
    	
    	//forklift.robotInit();
    	
    }

    public void autonomousInit(){
    	//arm.autoInit();
    	drive.teleopInit();
    	//components.init(); - test
    	//forklift.autoInit();
    }
    
    public void autonomousPeriodic() {
    	//arm.armPeriodic();
    	drive.autonomousPeriodic();
    	
    	//forklift.forkliftPeriodic();
    }


    public void teleopInit(){
    	//arm.teleopInit();
    	drive.teleopInit();
    	//forklift.teleopInit();
    }
    
    public void teleopPeriodic() {
    	//arm.armPeriodic();
    	drive.teleopPeriodic();
    	
    	//forklift.forkliftPeriodic();
    }
    

    public void testPeriodic() {
    
    }
    
    public void disabledInit(){
    	//arm.disabled();
    	drive.disableInit();
    	//forklift.disabled();
    }
    
}
