package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;

public class DistanceCommand extends CommandBase{
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final DriveSubsystem driveSubsystem;
    private boolean alignFinished = false;
    public double maxShooting;
    public double minShooting;

    /**
     * 
     * @param subsystem
     */

    public DistanceCommand(DriveSubsystem subsystem){
        driveSubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }


    @Override
    public void initialize() {
        System.out.println("initialized");
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
        System.out.println("is executing");
        System.out.println("The distance is "+ getDistance());
        System.out.println("distance is: " + getDistance());
        if(getDistance() >= minShooting && getDistance() <= maxShooting ){
            System.out.println("1 " + getDistance());
            //call shooter
            alignFinished = true;
        
        }else if(getDistance() < minShooting){
            setLeftPower(-.25);
            setRightPower(-.25);
            System.out.println("2");
        }else if(getDistance() > maxShooting){
            
            setLeftPower(.25);
            setRightPower(.25);
            System.out.println("3");
        }
    }

    @Override
    public boolean isFinished() {
        
        return alignFinished;
    }
    @Override
    public void end(boolean interrupted){
        setLeftPower(0);
        setRightPower(0);
    }
}


