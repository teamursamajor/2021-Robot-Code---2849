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
    setName("Auto Drive (Command)");
  }

  @Override
  public void initialize() {
    INTAKE_SUBSYSTEM.INTAKE.set(-0.4);
    INTAKE_SUBSYSTEM.beltSpark.set(1.0);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    INTAKE_SUBSYSTEM.INTAKE.set(0.0);
    INTAKE_SUBSYSTEM.beltSpark.set(0.0);
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
