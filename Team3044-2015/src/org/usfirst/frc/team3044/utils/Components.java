package org.usfirst.frc.team3044.utils;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Jaguar;

public class Components {
	
	 /* This class is intended to store all of the objects for components of the robot
	 ex. Sensors, motor controllers, etc,
	 */

	public static CANJaguar jagExample;
	
	public static void init(){
		jagExample = new CANJaguar(1);
		
	}

}
