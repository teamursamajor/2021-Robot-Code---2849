package frc.robot.commands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ClimbSubsystem CLIMB_SUBSYSTEM;

  public ClimbCommand(ClimbSubsystem subsystem) {
    CLIMB_SUBSYSTEM = subsystem;
    addRequirements(CLIMB_SUBSYSTEM);

    setName("Climb (Command)");
  }

  @Override
  public void initialize() {
    log(CLIMB_SUBSYSTEM, "intialzied", INFO);
  }

  @Override
  public void execute() {
    log(CLIMB_SUBSYSTEM, "Execute", INFO);
  }

  @Override
  public void end(boolean interrupted) {
    log(CLIMB_SUBSYSTEM, "Done", INFO);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
