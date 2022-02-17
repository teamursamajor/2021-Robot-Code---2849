package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoShooterCommand extends CommandBase {
    public boolean isFinished = false;
    public double maxYValue = -10.0;
    public double minYValue = 10.0;
    public double maxMotorSpeed = 1.0;
    public double minMotorSpeed = .25;
    @Override
    public void initialize() {
    System.out.println("initialized");

    }
    
    
    public double getY() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry tv = table.getEntry("tv");
        double y;
        double canDetectLimelight = tv.getDouble(Double.MIN_VALUE);
        if (canDetectLimelight == 0) {
          y = Double.MIN_VALUE;
        } else {
          y = ty.getDouble(Double.MIN_VALUE);
        }
        SmartDashboard.putNumber("LimelightX", y);
        return y;
    }
    
    @Override
    public void execute() {
        double y = getY();
        if(y == Double.MIN_VALUE){
            System.out.println("Can't detect limelight");
            return;
        }else{
            //double linearScale = (maxMotorSpeed-minMotorSpeed)/(minYValue-maxYValue);
            //double power = minMotorSpeed + (y-minYValue)*linearScale;
            //call the shooter the power
            //double power = maxMotorSpeed *((y-minYValue)/(maxYValue-minYValue));
            double power = maxMotorSpeed * (y/maxYValue);
        
        }

        
    }

    @Override
    public boolean isFinished() {
        return isFinished();

    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("end");


    }
    



}
