package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DistanceCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem DRIVE_SUBSYSTEM;

  private boolean alignFinished = false;
  // This is for values on the cart
  // public double maxShooting = -17;
  // public double minShooting = -8;
  public double minShooting = 3.9;
  public double maxShooting = -5.8;
  public int count;
  public int limeLightMissing = 5;

  /** @param subsystem */
  public DistanceCommand(DriveSubsystem subsystem) {
    DRIVE_SUBSYSTEM = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    System.out.println("initialized");
    count = 0;
    alignFinished = false;
  }

  public double getY() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    double y;
    y = ty.getDouble(Double.MIN_VALUE);
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
      System.out.println("y = " + y);
      double anglesAdded = (double) angleOfCamera + y;
      anglesAdded = (anglesAdded * Math.PI) / 180;
      System.out.println("angles added: " + anglesAdded);
      System.out.println("tangent is: " + Math.tan(anglesAdded));
      double distance = ((double) heightOfTarget - (double) heightOfRobo) / Math.tan(anglesAdded);
      System.out.println("Distance = " + distance);
      return distance;
    }
  }

  @Override
  public void execute() {
    double y = getY();
    System.out.println("is executing");
    System.out.println("y is: +" + y);

    if (y == Double.MIN_VALUE) {
      count++;
      if (count == limeLightMissing) {
        System.out.println("Couldn't detect limelight");
        alignFinished = true;
      }
    } else if (y <= minShooting && y >= maxShooting) {
      System.out.println("y = " + y);
      // call shooter
      alignFinished = true;

    } else if (y > minShooting) {
      DRIVE_SUBSYSTEM.setPower(.25, .25);
      System.out.println("To close");
    } else if (y < maxShooting) {

      DRIVE_SUBSYSTEM.setPower(-.25, -.25);
      System.out.println("to far");
    }
  }

  @Override
  public boolean isFinished() {

    return alignFinished;
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("End");
    DRIVE_SUBSYSTEM.setPower(0, 0);
  }
}
