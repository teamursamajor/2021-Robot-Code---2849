package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {
  public boolean isShooterFinished = false;
  public double maxYValue = -10.0; //
  public double minYValue = 10.0;
  public double maxMotorSpeed = 400; // find out what the rpm is when the motor speed is at 1
  public double minMotorSpeed = 300;
  public double maxMotorPower = 1.0;

  private final ShooterSubsystem SHOOTER_SUBSYSTEM;

  public ShooterCommand(ShooterSubsystem subsystem) {
    System.out.println("construct");
    SHOOTER_SUBSYSTEM = subsystem;
    addRequirements(subsystem);
  }

  public double getY() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry tv = table.getEntry("tv");
    double y;
    // double canDetectLimelight = tv.getDouble(Double.MIN_VALUE);
    y = ty.getDouble(Double.MIN_VALUE);
    SmartDashboard.putNumber("LimelightX", y);
    return y;
  }

  @Override
  public void initialize() {
    isShooterFinished = false;
    System.out.println("nitlazed");
    SHOOTER_SUBSYSTEM.SHOOTER.configFactoryDefault();
    SHOOTER_SUBSYSTEM.SHOOTER.config_kP(0, 3);
    SHOOTER_SUBSYSTEM.SHOOTER.config_kD(0, 0.1);
    SHOOTER_SUBSYSTEM.SHOOTER.config_kF(0, 0);
    SHOOTER_SUBSYSTEM.SHOOTER.config_kI(0, 0.0001);
    SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.Velocity, -15000);
    // SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.PercentOutput, -1);
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Execute");
    isShooterFinished = false;
    // try {
    //  wait(500L);
    // } catch (InterruptedException e) {
    // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // finished = true;

    System.out.println("Motor speed at " + SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity());
    System.out.println("y = " + getY());
  }

  @Override
  public boolean isFinished() {
    return isShooterFinished;
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("end");
    SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.PercentOutput, 0.0);
  }
}
