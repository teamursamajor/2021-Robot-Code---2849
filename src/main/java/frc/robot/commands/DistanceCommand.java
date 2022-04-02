package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import static frc.robot.Constants.*;

public class DistanceCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem DRIVE_SUBSYSTEM;

  private boolean alignFinished = false;
  // This is for values on the cart
  // public double maxShooting = -17;
  // public double minShooting = -8;
  public double minShooting = 23.2;
  public double maxShooting = 21.8;
  public int count;
  public double speed;
  //max speed = .60 min speed = .35 
  //max y = -10     min  y = 23
  //speed = (current y/ max y) * .60
  //speed = -30 + .011(curr y + -30
  // (53 - (y + 30))*0.0113;
  

  public int limeLightMissing = 5;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  
  NetworkTableEntry tv = table.getEntry("tv");

  /** @param subsystem */
  public DistanceCommand(DriveSubsystem subsystem) {
    table.getEntry("pipeline").setNumber(ALIGNING_PIPELINE);
    DRIVE_SUBSYSTEM = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    count = 0;
    alignFinished = false;
    System.out.println("Distance Start");
  }

  public double getY() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    double y;
    y = ty.getDouble(Double.MIN_VALUE);
    // SmartDashboard.putNumber("LimelightX", y);
    return y;
  }

  public double getAngle() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ts = table.getEntry("ts");

    double Angle = ts.getDouble(0.0);
    // SmartDashboard.putNumber("LimelightX", Angle);

    return Angle;
  }

  public double getDistance() {
    double angleOfCamera = 30.0;
    double heightOfTarget = 95.0;
    double y = getY();
    double heightOfRobo = 33.5;
    // 9

    if (y == Double.MIN_VALUE) {
      System.out.println("Couldn't detect");
      return y;
    } else {
      // System.out.println("y = " + y);
      double anglesAdded = (double) angleOfCamera + y;
      anglesAdded = (anglesAdded * Math.PI) / 180;
      // System.out.println("angles added: " + anglesAdded);
      // System.out.println("tangent is: " + Math.tan(anglesAdded));
      double distance = ((double) heightOfTarget - (double) heightOfRobo) / Math.tan(anglesAdded);
      // System.out.println("Distance = " + distance);
      return distance;
    }
  }

  @Override
  public void execute() {

    if(tv.getDouble(0) == 0){
        count++;
        if (count == limeLightMissing) {
          // System.out.println("Couldn't detect limelight");
          alignFinished = true;
        }
    }
    else{
      count = 0;
      double y = getY();
      // System.out.println("is executing");
      System.out.println("y is: +" + y);
      // ((y+10)*-.00909)+.65
      //speed = 0.65 - (y+10)*0.010606;  KEEP THIS ONE
      speed = 0.65 - (y+10)*0.010606;
      if (y <= minShooting && y >= maxShooting) {
        // System.out.println("y = " + y);
        // call shooter
        alignFinished = true;

      } else if (y > minShooting) {
        DRIVE_SUBSYSTEM.setPower(speed, speed);
        // System.out.println("To close");
      } else if (y < maxShooting) {

        DRIVE_SUBSYSTEM.setPower(-speed, -speed);
        // System.out.println("to far");
      }
    }
    
  }

  @Override
  public boolean isFinished() {

    return alignFinished;
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Distance End");
    DRIVE_SUBSYSTEM.setPower(0, 0);
  }
}
