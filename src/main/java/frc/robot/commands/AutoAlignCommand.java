package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import static frc.robot.Constants.XBOXCONTROLLER;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoAlignCommand extends CommandBase{
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final DriveSubsystem driveSubsystem;


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


    
    public double getX() {
       NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
       NetworkTableEntry tx = table.getEntry("tx");

       double x = tx.getDouble(0.0);
       SmartDashboard.putNumber("LimelightX", x);

       return x;
    }

    @Override
    public void execute() {
        double center = 0;
        double xCord;
        //detect target
        
        //if center, end

        //else move robot to make target in the center

        //end
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
