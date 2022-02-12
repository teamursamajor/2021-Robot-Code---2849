package frc.robot.commands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

/** An example command that uses an example subsystem. */
public class ShooterCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystem SHOOTER_SUBSYSTEM;

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

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SHOOTER_SUBSYSTEM.SHOOTER.set(10);
    // log(SHOOTER_SUBSYSTEM.getName(), "Motor speed at " + SHOOTER_SUBSYSTEM.SHOOTER.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SHOOTER_SUBSYSTEM.SHOOTER.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
