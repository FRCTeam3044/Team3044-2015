

package org.usfirst.frc.team3044.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
   
	Encoder LeftFrontEn = new Encoder(0,1);
	Encoder RightFrontEn = new Encoder(2,3);
	Encoder RightBackEn = new Encoder(4,5);
	Encoder LeftBackEn = new Encoder(6,7);
	
	CANTalon LeftFrontTurn = new CANTalon(12);
	CANTalon RightFrontTurn = new CANTalon(14);
	CANTalon RightBackTurn = new CANTalon(13);
	CANTalon LeftBackTurn = new CANTalon(11);
	
	Jaguar LeftFrontDrive = new Jaguar(0);
	Jaguar RightFrontDrive = new Jaguar(2);
	Jaguar RightBackDrive = new Jaguar(3);
	Jaguar LeftBackDrive = new Jaguar(1);
	
	Joystick DriveJoy = new Joystick(0);
	Joystick TurnJoy = new Joystick(1);
	
	double Distance;
	
	final double L = 33.75;
	final double W = 21.375;
	final double R = Math.sqrt(Math.pow(L,2) + Math.pow(L,2));
	final double LR = (L/R);
	final double WR = (W/R);
	final double Count = 1/Math.PI;
	
	double Strafe;
	double Forward;
	double Rotate;
	double A;
	double B;
	double C;
	double D;
	
	double WheelLFS;
	double WheelRFS;
	double WheelRBS;
	double WheelLBS;
	
	double WheelLFA;
	double WheelRFA;
	double WheelRBA;
	double WheelLBA;
	
	double ActDistanceLF;
	double ActDistanceRF;
	double ActDistanceRB;
	double ActDistanceLB;
	
	double Target;
	double TargetD;
	
	public double Deadband(double value, double band){
		if(Math.abs(value)<band){
			value = 0;
		}
		return value;
	}
	public double Turn(double target, double val){
		double MotorTurn;
		double Tol1 = .1;
		double Tol2 = .05;
		double Tol3 = .01;
		if (val > target+Tol1){
			MotorTurn=.8;
			}		
		else if (val < target-Tol1){
			MotorTurn=-.8;		
		}
		else if (val >target+Tol2){
			MotorTurn=.3;
			}		
		else if (val < target-Tol2){
			MotorTurn=-.3;	 
		}
		else if (val >target+Tol3){
			MotorTurn=.1;
			}		
		else if (val < target-Tol3){
			MotorTurn=-.1;	 
		}
			else {
			MotorTurn=0;
			}
		return MotorTurn;
	}
	
	public double Speed(double MoveSpeed){
		if(MoveSpeed>1){
			MoveSpeed = 1;
		} 	
		return -MoveSpeed;
	}
	
	public double Val(double Encoder){
		double value;
		value = Encoder/209;
		//value = Encoder-(int)Encoder;
		return -value;
	}
    public void robotInit() {
    	LeftFrontEn.reset();
 	    RightFrontEn.reset();
 	    RightBackEn.reset();
 	    LeftBackEn.reset();
    }

    
    public void autonomousPeriodic() {

    }

    
   public void teleopInit(){
	   
   }
   
    public void teleopPeriodic() {
    	
    	Forward = -Deadband(DriveJoy.getY(),.1);
    	Strafe = Deadband(DriveJoy.getX(),.1);
    	Rotate = Deadband(TurnJoy.getX(),.1);
    	
    	ActDistanceLF = Val(LeftFrontEn.getDistance());
    	ActDistanceRF = Val(RightFrontEn.getDistance());
    	ActDistanceRB = Val(RightBackEn.getDistance());
    	ActDistanceLB = Val(LeftBackEn.getDistance());
    	Distance = ActDistanceLF;
    	
    	A = Strafe - Rotate * LR;
    	B = Strafe + Rotate * LR;
    	C = Forward - Rotate * WR;
    	D = Forward + Rotate * WR;
    	
    	A = Deadband(A,.0000001);
    	B = Deadband(B,.0000001);
    	C = Deadband(C,.0000001);
    	D = Deadband(D,.0000001);
    	
    	//if(A >= 1){A=0.99999999;} else if(A <=-1){A=-.99999999;}
    	//if(B >= 1){B=0.99999999;} else if(B <=-1){B=-.99999999;}
    	//if(C >= 1){C=0.99999999;} else if(C <=-1){C=-.99999999;}
    	//if(D >= 1){D=0.99999999;} else if(D <=-1){D=-.99999999;}
    	
    	WheelLFS = Math.sqrt(B*B + D*D);
    	WheelRFS = Math.sqrt(B*B + C*C);
    	WheelRBS = Math.sqrt(A*A + C*C);
    	WheelLBS = Math.sqrt(A*A + D*D);
    	
    	WheelLFA = Math.atan2(B,D) * Count;
    	WheelRFA = Math.atan2(B,C) * Count;
    	WheelRBA = Math.atan2(A,C) * Count;
    	WheelLBA = Math.atan2(A,D) * Count;
    	
    	while (Distance >= 1){
    		Distance = Distance - 2;
    		}
    	while (Distance < -1){
    		Distance = Distance + 2;
    		}
    	
    	
    	LeftFrontTurn.set(Turn(WheelLFA,ActDistanceLF));
    	RightFrontTurn.set(Turn(WheelRFA,ActDistanceRF));
    	RightBackTurn.set(Turn(WheelRBA,ActDistanceRB));
    	LeftBackTurn.set(Turn(WheelLBA,ActDistanceLB));
    	
    	LeftFrontDrive.set(-Speed(WheelLFS)/2);
    	RightFrontDrive.set(-Speed(WheelRFS)/2);
    	RightBackDrive.set(-Speed(WheelRBS)/2);
    	LeftBackDrive.set(-Speed(WheelLBS)/2);
    	
    	SmartDashboard.putString("DB/String 0",String.valueOf(DriveJoy.getY()));
      	SmartDashboard.putString("DB/String 1",String.valueOf(DriveJoy.getX()));
      	SmartDashboard.putString("DB/String 2",String.valueOf(Speed(WheelRBS)));
      	SmartDashboard.putString("DB/String 3",String.valueOf(Speed(WheelLBS)));
      	SmartDashboard.putString("DB/String 4",String.valueOf(A*10000000));
      	SmartDashboard.putString("DB/String 5",String.valueOf(B*10000000));
      	SmartDashboard.putString("DB/String 6",String.valueOf(C*10000000));
      	SmartDashboard.putString("DB/String 7",String.valueOf(D*10000000));
      	SmartDashboard.putString("DB/String 8",String.valueOf(WheelLBA));
    	SmartDashboard.putString("DB/String 9",String.valueOf(Distance));
    	}
    /*
   public void teleopPeriodic() {
	   if(SmartDashboard.getBoolean("DB/Button 1")){
		   LeftFrontTurn.set(0);
		   RightFrontTurn.set(0);
		   RightBackTurn.set(0);
		   LeftBackTurn.set(0); 
		   
		   LeftFrontDrive.set(0);
		   RightFrontDrive.set(0);
		   RightBackDrive.set(0);
		   LeftBackDrive.set(0);
		   
		   LeftFrontEn.reset();
	 	   RightFrontEn.reset();
	 	   RightBackEn.reset();
	 	   LeftBackEn.reset();
	 	   
	   }else{
   	Target = (SmartDashboard.getDouble("DB/Slider 0")-2.5)/2.5;
   	TargetD = (SmartDashboard.getDouble("DB/Slider 1")-2.5)/2.5;
   	
   	ActDistanceLF = Val(LeftFrontEn.getDistance());
   	ActDistanceRF = Val(RightFrontEn.getDistance());
   	ActDistanceRB = Val(RightBackEn.getDistance());
   	ActDistanceLB = Val(LeftBackEn.getDistance());
   	
   	Distance = ActDistanceLF;
   	
   	LeftFrontTurn.set(Turn(Target,ActDistanceLF));
   	RightFrontTurn.set(Turn(Target,ActDistanceRF));
   	RightBackTurn.set(Turn(Target,ActDistanceRB));
   	LeftBackTurn.set(Turn(Target,ActDistanceLB));
   	
   	LeftFrontDrive.set(TargetD);
   	RightFrontDrive.set(TargetD);
   	RightBackDrive.set(TargetD);
   	LeftBackDrive.set(TargetD);
   	
   	   	
   	SmartDashboard.putString("DB/String 0",String.valueOf(ActDistanceLF));
    SmartDashboard.putString("DB/String 1",String.valueOf(ActDistanceRF));
    SmartDashboard.putString("DB/String 2",String.valueOf(ActDistanceRB));
    SmartDashboard.putString("DB/String 3",String.valueOf(ActDistanceLB));
   	SmartDashboard.putString("DB/String 4",String.valueOf(Target));
   	System.out.println(ActDistanceLF);
   	}
   }*/
    public void testPeriodic() {
    
    }
    public void disableInit(){
    	
    }
    
}
