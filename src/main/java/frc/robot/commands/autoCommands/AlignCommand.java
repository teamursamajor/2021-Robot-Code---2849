package frc.robot.commands.autoCommands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import static frc.robot.Constants.*;

public class AlignCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem DRIVE_SUBSYSTEM;

  private boolean alignFinished;
  public double maxShooting;
  public double minShooting;
  public int count;
  public int limeLightMissing = 10;
  public int correctCountNum = 4;
  public int correctCount;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tv = table.getEntry("tv");

  /** @param subsystem */
  public AlignCommand(DriveSubsystem subsystem) {
    table.getEntry("pipeline").setNumber(ALIGNING_PIPELINE);
    DRIVE_SUBSYSTEM = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    System.out.println("align initialized");
    alignFinished = false;
    count = 0;
    correctCount = 0;
  }

  public void detectTarget() {
    //NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    //NetworkTableEntry tx = table.getEntry("tx");
    //NetworkTableEntry ty = table.getEntry("ty");
    //NetworkTableEntry ta = table.getEntry("ta");
    

    //double x = tx.getDouble(0.0);
    //double y = ty.getDouble(0.0);
    //double area = ta.getDouble(0.0);
    //double skew = ts.getDouble(0.0);

    // System.out.println(x + " " + y + " " + area);
    // SmartDashboard.putNumber("LimelightX", x);
    // SmartDashboard.putNumber("LimelightY", y);
    // SmartDashboard.putNumber("LimelightArea", area);
    // SmartDashboard.putNumber("LimelightSkew", skew);
    // min shooting range, 5 ft, max 30 ft
  }

  public double getX() {
    NetworkTableEntry tx = table.getEntry("tx");
    double x;

    x = tx.getDouble(Double.MIN_VALUE);
    // SmartDashboard.putNumber("LimelightX", x);
    return x;
  }

  public double getAngle() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ts = table.getEntry("ts");

    double Angle = ts.getDouble(0.0);
    // SmartDashboard.putNumber("LimelightX", Angle);

    return Angle;
  }

  @Override
  public void execute() {
    if(correctCount == correctCountNum){
      alignFinished = true;
    } 
    double max = 1;
    double min = -1;
    double x;
    System.out.println("x value: "+ getX());
    if(tv.getDouble(0) == 0) /* Don't see vision tape */{
      count++;
      if (count == limeLightMissing)
        
      alignFinished = true;
    }
    else /* We see vision tape */{
      count = 0;
      x = table.getEntry("tx").getDouble(0);
      System.out.println("X value: " + x);
      if(x <= max && x >= min){
        DRIVE_SUBSYSTEM.setPower(0, 0);
        correctCount++;
      }
      else if(x > max){
        DRIVE_SUBSYSTEM.setPower(0, .5);
        correctCount = 0;
      }
      else if(x < min){
        DRIVE_SUBSYSTEM.setPower(.5, 0);
        correctCount = 0;
      }
    }
      /* KEEP THIS CODE
      } else if (x <= max && x >= min) {
        // System.out.println("We are alined");
        alignFinished = true;
      } else if (x > max) {
        DRIVE_SUBSYSTEM.setPower(0, .35);
        // System.out.println("4");
      } else if (x < min) {
        DRIVE_SUBSYSTEM.setPower(.35, 0);
        // System.out.println("5");
      }
    }
    
    // System.out.println(x);
    // if center, end

    // System.out.println("x is " + x);
    
    // end

    */
  }

  @Override
  public boolean isFinished() {

    return alignFinished;
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Align End");
    DRIVE_SUBSYSTEM.setPower(0, 0);
  }
}
