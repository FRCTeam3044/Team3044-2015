package org.usfirst.frc.team3044.utils;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Encoder;

public class TalonEncoder {

	private CANTalon talonInstance;
	private Encoder encoderInstance;
	boolean isTalon = false;
	double offsetValue = 0;

	// Is this even worth implementing

	public TalonEncoder(CANTalon talonInstance) {
		isTalon = true;
		this.talonInstance = talonInstance;
		this.talonInstance.setFeedbackDevice(FeedbackDevice.QuadEncoder);

	}

	public TalonEncoder(Encoder enc) {
		this.encoderInstance = enc;
		isTalon = false;
	}

	public void reset() {
		if (isTalon) {
			talonInstance.setPosition(0);
		} else {
			encoderInstance.reset();
		}
	}

	public void reset(double flOffset) {
		if (isTalon){
			talonInstance.setPosition(0);
			this.offsetValue = flOffset;
		}else{
			encoderInstance.reset();
			this.offsetValue = flOffset;
		}
	}

	public double getDistance() {
		if (isTalon) {
			return -(talonInstance.getPosition() / 4.0) - offsetValue;
		} else {
			return encoderInstance.getDistance() - offsetValue;
		}
	}

	public double getSpeed() {
		if(isTalon){
			return -talonInstance.getSpeed();
		}else{
			return encoderInstance.getRate();
			
		}
	}

}
