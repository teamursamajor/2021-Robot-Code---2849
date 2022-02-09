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
    LOGGER.log(CLIMB_SUBSYSTEM, "intialzied");
  }

  @Override
  public void execute() {
    LOGGER.log(CLIMB_SUBSYSTEM, "Execute");
  }

  @Override
  public void end(boolean interrupted) {
    LOGGER.log(CLIMB_SUBSYSTEM, "Done");
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
