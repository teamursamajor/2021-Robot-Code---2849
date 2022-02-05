package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;

public class AutoAlignCommand extends CommandBase{
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final DriveSubsystem driveSubsystem;
    private boolean alignFinished = false;
    public double maxShooting;
    public double minShooting;

    /**
     * 
     * @param subsystem
     */

    public AutoAlignCommand(DriveSubsystem subsystem){
        driveSubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }


    @Override
    public void initialize() {
        System.out.println("initialized");
    }


    public void detectTarget() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry ts = table.getEntry("ts");

        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
        double skew = ts.getDouble(0.0);

        System.out.println(x + " " + y + " " + area);
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("LimelightSkew", skew);
        //min shooting range, 5 ft, max 30 ft
    }

    public void setRightPower(final double power) {
        driveSubsystem.BackRightDrive.set(power);
        driveSubsystem.FrontRightDrive.set(power);
        System.out.println("right speed: " + power);
      }

      public void setLeftPower(final double power) {
        driveSubsystem.BackLeftDrive.set(-power);
        driveSubsystem.FrontLeftDrive.set(-power);
        System.out.println("left speed: " + power);
      }
    
    public double getX() {
       NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
       NetworkTableEntry tx = table.getEntry("tx");

       double x = tx.getDouble(0.0);
       SmartDashboard.putNumber("LimelightX", x);

       return x;
    }
    public double getY() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry ty = table.getEntry("ty");
 
        double y = ty.getDouble(0.0);
        SmartDashboard.putNumber("LimelightX", y);
        return y;
     }
     public double getAngle() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry ts = table.getEntry("ts");
 
        double Angle = ts.getDouble(0.0);
        SmartDashboard.putNumber("LimelightX", Angle);
 
        return Angle;
     }
    
    
    
    
    public double getDistance(){
       
        double anglesAdded = 34.0+getY();
        
        double distance = (104-6)/Math.tan(anglesAdded);
        return distance;
    }

    @Override
    public void execute() {
        System.out.println("is ececuting");
        double max = 5;
        double min = -5;
        //detect target
        double x = this.getX();
        //if center, end
        System.out.println("x is "+ x);
        if(x <= max && x >= min){
            setLeftPower(0);
            setRightPower(0);
            System.out.println(getDistance());
            if(getDistance() >= minShooting && getDistance() <= maxShooting ){
                System.out.println(getDistance());
                //call shooter
                alignFinished = true;
            
            }else if(getDistance() < minShooting){
                setLeftPower(-.25);
                setRightPower(-.25);
            
            }else if(getDistance() > maxShooting){
                
                setLeftPower(.25);
                setRightPower(.25);
            }
        }
        //else move robot to make target in the center
        if(x > max){
            setLeftPower(.25);
            setRightPower(0);
        }else if(x > min){
            setRightPower(.25);
            setLeftPower(0);
        }
        //end
    }

    @Override
    public boolean isFinished() {
        
        return alignFinished;
    }
}
