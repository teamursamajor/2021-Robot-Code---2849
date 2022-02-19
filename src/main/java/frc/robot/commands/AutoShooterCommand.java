package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShooterCommand extends CommandBase {
  public boolean isFinished = false;
  public double maxYValue = -10.0;
  public double minYValue = 10.0;
  public double maxMotorSpeed = 400; // find out what the rpm is when the motor speed is at 1
  public double minMotorSpeed = 300;
  public double maxMotorPower = 1.0;
  public double minMotorPower =
      .25; // find the motor speed for when the robot is closest the target
  private final ShooterSubsystem SHOOTER_SUBSYSTEM;
  private final BeltSubsystem BELT_SUBSYSTEM;

  public AutoShooterCommand(ShooterSubsystem subsystem, BeltSubsystem subsystem2) {
    System.out.println("construct");
    SHOOTER_SUBSYSTEM = subsystem;
    BELT_SUBSYSTEM = subsystem2;
    addRequirements(subsystem2);
    addRequirements(subsystem);
  }

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
    System.out.println("The velocity: " + SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity());
    int time = 0;
    int count = 0;
    double y = getY();
    if (y == Double.MIN_VALUE) {
      System.out.println("Can't detect limelight");
      return;
    } else {
      if (BELT_SUBSYSTEM.lineBroken) {
        double speed = maxMotorSpeed * (y / maxYValue);
        double power = maxMotorPower * (y / maxYValue);
        SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.PercentOutput, power);
        if ((SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity() >= speed - 10)
            && (SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity() <= speed + 10)) {
          BELT_SUBSYSTEM.ballToShooter();
          count++;
        }
        if (count == 2) {
          isFinished = true;
        }

      } else {
        BELT_SUBSYSTEM.balltoTopOfBelt();
      }
    }
    time++;
    if (time >= 5) {
      isFinished = true;
    }
  }

  @Override
  public boolean isFinished() {
    return isFinished();
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("end");
    SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.PercentOutput, 0.0);
  }
}
