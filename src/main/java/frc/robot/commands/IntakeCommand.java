package frc.robot.commands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {

  private final IntakeSubsystem INTAKE_SUBSYSTEM;

  public IntakeCommand(IntakeSubsystem intake, ColorSubsystem color) {
    INTAKE_SUBSYSTEM = intake;
    addRequirements(intake, color);
    setName("Intake (Command)");
  }

  @Override
  public void initialize() {
    LOGGER.log(INTAKE_SUBSYSTEM, "intialzied", INFO);
  }

  @Override
  public void execute() {
    LOGGER.log(INTAKE_SUBSYSTEM, "Executing", INFO);
  }

  @Override
  public void end(boolean interrupted) {
    LOGGER.log(INTAKE_SUBSYSTEM, "Done", INFO);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
