package org.usfirst.frc.team3044.utils;

public class Utilities {
	
	/*This class should be used for storing any functions and/or algorithms that could
	 * be used in most parts of the robot
	 */

	public static double deadband(int val, int cutoff){
		if(Math.abs(val) < cutoff){
			return 0;
		}else{
			return val;
		}
		
		
	}

}
