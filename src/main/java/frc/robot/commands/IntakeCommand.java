package frc.robot.commands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.ColorRGB;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {

  private final IntakeSubsystem INTAKE_SUBSYSTEM;
  private final String TEAM_COLOR = "red";

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
    if (INTAKE_SUBSYSTEM.checkColor(new ColorRGB(INTAKE_SUBSYSTEM.COLOR_SENSOR.getColor())).equals(TEAM_COLOR)) {
      INTAKE_SUBSYSTEM.INTAKE.set(10);
    }
  }

  @Override
  public void end(boolean interrupted) {
    INTAKE_SUBSYSTEM.INTAKE.set(0.0);
    log(INTAKE_SUBSYSTEM, "Done", INFO);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
