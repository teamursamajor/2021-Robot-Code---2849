package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShooterCommand extends CommandBase {
  public boolean isFinished = false;
  public double maxYValue = -10.0;
  public double minYValue = 10.0;
  public double maxMotorSpeed = 400; // find out what the rpm is when the motor speed is at 1
  public double minMotorSpeed = 300;
  public double maxMotorPower = 1.0;
  public int count;
  public int limeLightMissing = 5;

  public boolean isThereBallToShoot;
  public int time;

  private final ShooterSubsystem SHOOTER_SUBSYSTEM;

  private final IntakeSubsystem INTAKE_SUBSYSTEM;

  public AutoShooterCommand(ShooterSubsystem subsystem, IntakeSubsystem subsystem2) {
    System.out.println("construct");
    SHOOTER_SUBSYSTEM = subsystem;
    INTAKE_SUBSYSTEM = subsystem2;
    addRequirements(subsystem2);
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    System.out.println("initialized");
    count = 0;
    time = 0;
    if (INTAKE_SUBSYSTEM.topLineBroken) {
      isThereBallToShoot = true;
    } else {
      System.out.println("No ball at top");
      isThereBallToShoot = false;
      isFinished = true;
    }
  }

  public double getY() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    // NetworkTableEntry tv = table.getEntry("tv");
    double y;
    // double canDetectLimelight = tv.getDouble(Double.MIN_VALUE);
    y = ty.getDouble(Double.MIN_VALUE);
    SmartDashboard.putNumber("LimelightX", y);
    return y;
  }

  @Override
  public void execute() {
    System.out.println("The velocity: " + SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity());
    double y = getY();
    if (y == Double.MIN_VALUE) {
      count++;
      if (count == limeLightMissing) {
        System.out.println("Can't detect limelight");
        isFinished = true;
      }
    } else {
      if (isThereBallToShoot) {
        double speed = maxMotorSpeed * (y / maxYValue);
        SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.Velocity, speed);
        if ((SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity() >= speed - 150)
            && (SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity() <= speed + 150)) {
          INTAKE_SUBSYSTEM.beltSpark.set(.25);
          time++;
        }
      }
      if (time == 10) {
        INTAKE_SUBSYSTEM.beltSpark.set(0.0);
        INTAKE_SUBSYSTEM.ballCount--;
        isFinished = true;
      }
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
