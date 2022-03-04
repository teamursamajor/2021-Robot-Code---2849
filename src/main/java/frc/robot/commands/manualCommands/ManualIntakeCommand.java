package frc.robot.commands.manualCommands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class ManualIntakeCommand extends CommandBase {
  private final IntakeSubsystem INTAKE_SUBSYSTEM;

  public boolean isFinished = false;

  public ManualIntakeCommand(IntakeSubsystem intake) {
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
    INTAKE_SUBSYSTEM.INTAKE.set(0.40);
  }

  @Override
  public void end(boolean interrupted) {
    INTAKE_SUBSYSTEM.INTAKE.set(0.0);
    log(INTAKE_SUBSYSTEM, "Done", INFO);
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
