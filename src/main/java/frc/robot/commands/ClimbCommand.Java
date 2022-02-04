package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ClimbSubsystem CLIMB_SUBSYSTEM;

  public ClimbCommand(ClimbSubsystem subsystem) {
    CLIMB_SUBSYSTEM = subsystem;
    addRequirements(CLIMB_SUBSYSTEM);
  }

  @Override
  public void initialize() {
    System.out.println("intialzied");
  }

  @Override
  public void execute() {
    System.out.println("Execute");
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Done");
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
