package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoCommand1 extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystem SHOOTER_SUBSYSTEM;
  private final DriveSubsystem DRIVE_SUBSYSTEM;


  boolean finished = false;

  /**
   * Creates a new AutoCommand1 Command
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterCommand(ShooterSubsystem shooterSubsystem, DriveSubsystem driveSubsystem) {
    SHOOTER_SUBSYSTEM = subsystem;
    DRIVE_SUBSYSTEM = driveSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(SHOOTER_SUBSYSTEM, DRIVE_SUBSYSTEM);
    setName("AutoCommand1 (Command)");
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SHOOTER_SUBSYSTEM.SHOOTER.set(0.5);
    try {
      wait(500L);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    finished = true;
    LOGGER.log(SHOOTER_SUBSYSTEM, "Motor speed at " + SHOOTER_SUBSYSTEM.SHOOTER.get(), INFO);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SHOOTER_SUBSYSTEM.SHOOTER.set(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }

  // Waiting for the day that this line will be used...
  // Don't worry line56, you will be used someday.



}