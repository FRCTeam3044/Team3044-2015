package org.usfirst.frc.team3044.utils;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;

public class TalonEncoder {
	
	private CANTalon talonInstance;
	
	//Is this even worth implementing
	
	public TalonEncoder(CANTalon talonInstance){
		this.talonInstance = talonInstance;
		this.talonInstance.setFeedbackDevice(FeedbackDevice.QuadEncoder);
	}
	
	public void reset(){
		talonInstance.setPosition(0);
	}
	
	public double getDistance(){
		return talonInstance.getPosition();
	}
	
	public double getSpeed(){
		return talonInstance.getSpeed();
	}

}
