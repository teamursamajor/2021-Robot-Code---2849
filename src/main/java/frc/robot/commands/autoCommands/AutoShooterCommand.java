package frc.robot.commands.autoCommands;

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
  public double maxMotorSpeed = -16000; // find out what the rpm is when the motor speed is at 1
  public double minMotorSpeed = -14000;
  public double maxMotorPower = 1.0;
  public int count;
  public int limeLightMissing = 5;

  public boolean isThereBallToShoot;
  public int time;
  public int highBallTime = 5;
  public int lowBallTime = 10;

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
    System.out.println("Shooter initialized");
    count = 0;
    time = 0;
    SmartDashboard.putBoolean("Currently AutoShooting: ", true);
  }

  public double getY() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    // NetworkTableEntry tv = table.getEntry("tv");
    double y;
    // double canDetectLimelight = tv.getDouble(Double.MIN_VALUE);
    y = ty.getDouble(Double.MIN_VALUE);
    // SmartDashboard.putNumber("LimelightX", y);
    return y;
  }

  @Override
  public void execute() {
    System.out.println("Shooter execute");
   /**  if (INTAKE_SUBSYSTEM.ballCount != 0) {
      isThereBallToShoot = true;
    } else {
      System.out.println("No ballz");
      isThereBallToShoot = false;
      isFinished = true;
    }*/
    isThereBallToShoot = true;

    if (isThereBallToShoot) {
      // System.out.println("The velocity: " +
      // SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity());
      double y = getY();
      if (y == Double.MIN_VALUE) {
        count++;
        if (count == limeLightMissing) {
          System.out.println("Can't detect limelight");
          isFinished = true;
        }
      } else {
        double speed = maxMotorSpeed * (y / maxYValue);
        System.out.println("Shooting ball");
        time++;
        SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.PercentOutput, -0.75);
        System.out.println("Velocity: " + SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity());
        if(time > 100){
          INTAKE_SUBSYSTEM.beltSpark.set(-1);
        }
        /**if ((SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity() >= -12000)
            && (SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity() <= -14000)) {
          INTAKE_SUBSYSTEM.beltSpark.set(-1);
          time++;
        }**/
       /**  if (INTAKE_SUBSYSTEM.ballCount == 2 && time == highBallTime) {
          time = 0;
          INTAKE_SUBSYSTEM.beltSpark.set(0.0);
          INTAKE_SUBSYSTEM.ballCount--;
        } else if (INTAKE_SUBSYSTEM.ballCount == 1 && time == lowBallTime) {
          time = 0;
          INTAKE_SUBSYSTEM.beltSpark.set(0.0);
          INTAKE_SUBSYSTEM.ballCount--;
        } */
      }
    }
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }

  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("Currently AutoShooting: ", true);
    SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.PercentOutput, 0.0);
    INTAKE_SUBSYSTEM.beltSpark.set(0);
    System.out.println("Shooter end");
  }
}
