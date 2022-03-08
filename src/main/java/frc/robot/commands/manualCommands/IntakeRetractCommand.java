package frc.robot.commands.manualCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeRetractCommand extends CommandBase {
  private boolean wantToRetract;
  private final IntakeSubsystem INTAKE_SUBSYSTEM;
  private boolean isFinished = false;

  public IntakeRetractCommand(IntakeSubsystem intakeSubsystem) {
    INTAKE_SUBSYSTEM = intakeSubsystem;
    addRequirements(intakeSubsystem);
    this.wantToRetract = wantToRetract;
    setName("Auto Drive (Command)");
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
