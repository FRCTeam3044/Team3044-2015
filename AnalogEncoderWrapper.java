package org.usfirst.frc.team3044.utils;

import edu.wpi.first.wpilibj.AnalogInput;

public class AnalogEncoderWrapper {
	
	private AnalogInput encoderInstance;
	private double oldValue = 0;
	private double value = 0;
	private final double MAX_VAL = 255;
	double distance = 0;
	
	public AnalogEncoderWrapper(AnalogInput encoderInstance){
		this.encoderInstance = encoderInstance;
		
	}
	
	public void step(){
		value = encoderInstance.getVoltage();
		if(value - oldValue > 4){
			distance += 1;
		}
		else if(value - oldValue < -4){
			distance -= 1;
		}
		oldValue  = encoderInstance.getVoltage();
		
		
	}
	
	public double getDistance(){
		if(distance >= 0){
			return distance + (1 - encoderInstance.getVoltage()/5.0);
		}else{
			return distance - (encoderInstance.getVoltage()/5.0);
		}
	}
	
	public void reset(){
		this.distance = 0;
	}
	
	public void reset(double value){
		this.distance = value;
	}

}
