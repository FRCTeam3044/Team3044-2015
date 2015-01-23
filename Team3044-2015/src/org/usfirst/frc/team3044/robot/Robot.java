
package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.utils.Components;
import org.usfirst.frc.team3044.utils.Utilities;

import edu.wpi.first.wpilibj.IterativeRobot;


public class Robot extends IterativeRobot {
	Arm arm = new Arm();
	Drive drive = new Drive();
	Forklift forklift = new Forklift();
	Components components = new Components();
	Utilities utils = new Utilities();
	
	
    public void robotInit() {
    	arm.robotInit();
    	drive.disabledinit();
    	components.init();
    	forklift.robotInit();
    	
    }

    public void autonomousInit(){
    	arm.autoInit();
    	drive.teleopinit();;
    	//components.init();
    	forklift.autoInit();
    }
    
    public void autonomousPeriodic() {
    	arm.armPeriodic();
    	drive.drivePeriodic();
    	//forklift.teleopPeriodic();
    }


    public void teleopInit(){
    	arm.teleopInit();
    }
    
    public void teleopPeriodic() {
        arm.armPeriodic();
    }
    

    public void testPeriodic() {
    
    }
    
    public void disabledInit(){
    	arm.disabled();
    	drive.disabledinit();
    }
    
}
