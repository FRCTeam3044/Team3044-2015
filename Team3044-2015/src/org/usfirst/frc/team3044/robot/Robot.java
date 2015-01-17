
package org.usfirst.frc.team3044.robot;

import edu.wpi.first.wpilibj.IterativeRobot;


public class Robot extends IterativeRobot {
	Arm arm = new Arm();
    public void robotInit() {
    	arm.robotInit();
    }

    public void autonomousInit(){
    	arm.autoInit();
    }
    
    public void autonomousPeriodic() {
    	arm.armPeriodic();
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
    }
    
}
