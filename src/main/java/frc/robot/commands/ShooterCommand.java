package frc.robot.commands;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/** An example command that uses an example subsystem. */
public class ShooterCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystem SHOOTER_SUBSYSTEM;

  boolean finished = false;

  /**
   * Creates a new ShooterCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterCommand(ShooterSubsystem subsystem) {
    SHOOTER_SUBSYSTEM = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(SHOOTER_SUBSYSTEM);
    setName("Shooter (Command)");
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
    // SmartDashboard.putNumber("LimelightX", y);
    return y;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SHOOTER_SUBSYSTEM.SHOOTER.configFactoryDefault();
    SHOOTER_SUBSYSTEM.SHOOTER.config_kP(0, 0.01);
    SHOOTER_SUBSYSTEM.SHOOTER.config_kD(0, 0);
    SHOOTER_SUBSYSTEM.SHOOTER.config_kF(0, 0);
    SHOOTER_SUBSYSTEM.SHOOTER.config_kI(0, 0.0001);
    SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.Velocity, -15000);
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Execute");
    // try {
    //  wait(500L);
    // } catch (InterruptedException e) {
    // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // finished = true;

    System.out.print("Motor speed at " + SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity());
    System.out.println(getY());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.PercentOutput, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
