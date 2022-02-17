package frc.robot.commands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {

  private final IntakeSubsystem INTAKE_SUBSYSTEM;

  public IntakeCommand(IntakeSubsystem intake) {
    INTAKE_SUBSYSTEM = intake;
    addRequirements(intake);
    setName("Intake (Command)");
  }

  @Override
  public void initialize() {
    log(INTAKE_SUBSYSTEM, "intialzied", INFO);
  }

  @Override
  public void execute() {
    log(INTAKE_SUBSYSTEM, "Executing", INFO);
  }

  @Override
  public void end(boolean interrupted) {
    log(INTAKE_SUBSYSTEM, "Done", INFO);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
