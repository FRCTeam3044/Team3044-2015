package org.usfirst.frc.team3044.robot;
import org.usfirst.frc.team3044.DriverStation.DriverController;
import org.usfirst.frc.team3044.utils.Components;
import edu.wpi.first.wpilibj.*;
public class Drive {
	
	DriverController c = DriverController.getInstance();
	
	CANJaguar LeftFront = Components.frontLeftDrive;
	CANJaguar RightFront = Components.frontRightDrive;
	CANJaguar RightBack = Components.backRightDrive;
	CANJaguar LeftBack = Components.backLeftDrive;
	
	CANTalon LeftFrontTurn = Components.frontLeftDriveRot;
	CANTalon RightFrontTurn = Components.frontRightDriveRot;
	CANTalon RightBackTurn = Components.backRightDriveRot;
	CANTalon LeftBackTurn = Components.backLeftDriveRot;
	
	DriverController JoyRight = DriverController.getInstance();
	
	Encoder LeftFrontEn = Components.driveEncoderFL;
	Encoder RightFrontEn = Components.driveEncoderFR;
	Encoder RightBackEn = Components.driveEncoderBR;
	Encoder LeftBackEn = Components.driveEncoderBL;
	
	Encoder TLeftFrontEn = Components.rotEncoderFL;
	Encoder TRightFrontEn = Components.rotEncoderFR;
	Encoder TRightBackEn = Components.rotEncoderBR;
	Encoder TLeftBackEn = Components.driveEncoderBL;
	
	double WheelD;
	double MSpeed;
	double TTol = 2;
	double MTol = 5;
	
	double TLFE;
	double TRFE;
	double TRBE;
	double TLBE;
	
	double Trigger;
	double D45 = 31.875;
	double D315 = 223.125;
	
	double MT45LF;
	double MT45RB;    
	double MT315RF;
	double MT315LB;
	// Calc Ticks from Joystick position
	public double WheelDegree (double x, double y){ 
		double Ticks=0;
		double Degrees=0;
		if(Math.abs(x) > .1 && Math.abs(y) > .1){
		Degrees = Math.acos(Math.abs(y)/Math.sqrt(Math.pow(x,2)+Math.pow(y,2)));
			if(x < 0 && y > 0){
				Degrees=360-Degrees;
			} else if(x < 0 && y < 0){
				Degrees=180+Degrees;	
			} else if(x > 0 && y <0 ){
				Degrees=180-Degrees;
			}
		}
		Ticks = Degrees*(255/360);
		return Ticks;
	}
	// T or F from difference of is and want from tolerance
	public boolean TurnTolerance (double target, double value, double tolerance){ 
		return Math.abs(target - value)<tolerance; 
	}
	// Set Turn Speed 
	public double MotorTolerance (double target, double value, double tolerance){ 
		double TurnSpeed=0;
		double Dif = Math.abs(target-value);
		
		if (Dif > tolerance && Dif < 127.5){
			TurnSpeed=1;
		} else if (Dif > tolerance && Dif > 127.5){
			TurnSpeed=-1;
		} else if (Dif < tolerance && Dif > 2){
			TurnSpeed=.2;
		} else if (Dif < 2){
			TurnSpeed=0;
		} 
		//if (target<value){
			//TurnSpeed=-TurnSpeed;
		//} //Use if going wrong direction
		return TurnSpeed;
	}
	// Set Forward Speed
	public double MotorSpeed(double X, double Y, double Tolerance){ 
		double MotorSpeed = 0;
		double Distance = Math.sqrt(Math.pow(X,2)+Math.pow(Y,2));
		
		if (Math.abs(Distance) < Tolerance){
			MotorSpeed = 0;
		} else {
			MotorSpeed = Distance; 
		}
		return MotorSpeed;
	}

	public void drivePeriodic(){
		
		WheelD = WheelDegree(JoyRight.getRightX(),JoyRight.getRightY());
		MSpeed = MotorSpeed(JoyRight.getRightX(),JoyRight.getRightY(),.1);
		
		MT45LF = MotorTolerance(D45,TLFE,MTol);
		MT45RB = MotorTolerance(D45,TRBE,MTol);    
		MT315RF = MotorTolerance(D315,TRFE,MTol);
		MT315LB = MotorTolerance(D315,TLBE,MTol);
		
		Trigger = JoyRight.getTriggerLeft();
		
		TLFE = TLeftFrontEn.get();
		TRFE = TRightFrontEn.get();
		TRBE = TRightBackEn.get();
		TLBE = TLeftBackEn.get();
	
		LeftFront.set(MSpeed);
    	RightFront.set(MSpeed);
    	RightBack.set(MSpeed);
    	LeftBack.set(MSpeed);
    	
    	//LeftFrontTurn Wheel
    	if (TurnTolerance (WheelD,TLFE,TTol)){ 	// Call TurnTolerance (Tolerance from target to stop)
    		LeftFrontTurn.set(0);
    	} else if (TLFE < WheelD){				// Encoder Smaller than Target  
    		LeftFrontTurn.set(MotorTolerance(WheelD,TLFE,MTol));
    	} else if (TLFE > WheelD){				// Encoder Bigger than Target
    		LeftFrontTurn.set(-MotorTolerance(WheelD,TLFE,MTol));
    	} else { 								// Back up for Mess up
    		LeftFrontTurn.set(0);
    	}
    	
    	//RightFrontTurn Wheel
    	if (TurnTolerance (WheelD,TRFE,TTol)){ 		
    		RightFrontTurn.set(0);
    	} else if (TRFE < WheelD){				  
    		RightFrontTurn.set(MotorTolerance(WheelD,TRFE,MTol));
    	} else if (TRFE > WheelD){				
    		RightFrontTurn.set(-MotorTolerance(WheelD,TRFE,MTol));
    	} else { 								
    		RightFrontTurn.set(0);
    	}
    	
    	//RightBackTurn Wheel
    	if (TurnTolerance (WheelD,TRBE,TTol)){ 		
    		RightBackTurn.set(0);
    	} else if (TRBE < WheelD){				  
    		RightBackTurn.set(MotorTolerance(WheelD,TRBE,MTol));
    	} else if (TRBE > WheelD){				 
    		RightBackTurn.set(-MotorTolerance(WheelD,TRBE,MTol));
    	} else { 								
    		RightBackTurn.set(0);
    	}
    	
    	//LeftBackTurn Wheel
    	if (TurnTolerance (WheelD,TLBE,TTol)){ 		
    		LeftBackTurn.set(0);
    	} else if (TLBE < WheelD){				  
    		LeftBackTurn.set(MotorTolerance(WheelD,TLBE,MTol));
    	} else if (TLBE > WheelD){				 
    		LeftBackTurn.set(-MotorTolerance(WheelD,TLBE,MTol));
    	} else { 								
    		LeftBackTurn.set(0);
    	}
    	
    	//Rotating
    	if (JoyRight.getTriggerLeft()>.1 || JoyRight.getTriggerLeft()<-.1){//Needs to be changed when intergrated
    		
    		LeftFrontTurn.set(Trigger);
        	RightFrontTurn.set(Trigger);
        	RightBackTurn.set(Trigger);
        	LeftBackTurn.set(Trigger);
    		
    		if (TurnTolerance (D45,TLFE,TTol)){ 		
        		LeftFrontTurn.set(0);
        	} else if (TLFE < D45){				 
        		LeftFrontTurn.set(MT45LF);
        	} else if (TLFE > D45){				 
        		LeftFrontTurn.set(-MT45LF);
        	} else { 								
        		LeftFrontTurn.set(0);
        	}
    		if (TurnTolerance (D315,TRFE,TTol)){ 		
        		RightFrontTurn.set(0);
        	} else if (TRFE < D315){				 
        		RightFrontTurn.set(MT315RF);
        	} else if (TRFE > D315){				 
        		RightFrontTurn.set(-MT315RF);
        	} else { 								
        		RightFrontTurn.set(0);
        	}
    		if (TurnTolerance (D45,TRBE,TTol)){ 		
        		RightBackTurn.set(0);
        	} else if (TRBE < D45){				   
        		RightBackTurn.set(MT45RB);
        	} else if (TRBE > D45){				 
        		RightBackTurn.set(-MT45RB);
        	} else { 								
        		RightBackTurn.set(0);
        	}
    		if (TurnTolerance (D315,TLBE,TTol)){ 		
        		LeftBackTurn.set(0);
        	} else if (TLBE < D315){				   
        		LeftBackTurn.set(MT315LB);
        	} else if (TLBE > D315){				 
        		LeftBackTurn.set(-MT315LB);
        	} else { 								
        		LeftBackTurn.set(0);
        	}
    	}
    }
	public void teleopinit(){
		
		LeftFront = Components.frontLeftDrive;
		//Jaguar RightFront = Componentsonents.frontRightDrive;
		//Jaguar RightBack = Componentsonents.backRightDrive;
		//Jaguar LeftBack = Componentsonents.backLeftDrive;
		
		LeftFrontTurn = Components.frontLeftDriveRot;
		
		Encoder LeftFrontEn = Components.driveEncoderFL;
		Encoder RightFrontEn = Components.driveEncoderFR;
		Encoder RightBackEn = Components.driveEncoderBR;
		Encoder LeftBackEn = Components.driveEncoderBL;
		
		Encoder TLeftFrontEn = Components.rotEncoderFL;
		Encoder TRightFrontEn = Components.rotEncoderFR;
		Encoder TRightBackEn = Components.rotEncoderBR;
		Encoder TLeftBackEn = Components.driveEncoderBL;
		LeftFront.set(0);
    	//RightFront.set(0);
    	//RightBack.set(0);
    	//LeftBack.set(0);
    	LeftFrontTurn.set(0);
    	//RightFrontTurn.set(0);
    	//RightBackTurn.set(0);
    	//LeftBackTurn.set(0);
	}
	
	public void disabledinit(){
		LeftFront.set(0);
    	//RightFront.set(0);
    	//RightBack.set(0);
    	//LeftBack.set(0);
    	LeftFrontTurn.set(0);
    	//RightFrontTurn.set(0);
    	//RightBackTurn.set(0);
    	//LeftBackTurn.set(0);
	}

}