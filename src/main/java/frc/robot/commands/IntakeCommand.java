package frc.robot.commands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {

  private final IntakeSubsystem INTAKE_SUBSYSTEM;
  private boolean readyToIntake = false;
  private boolean isFinished = false;

  public IntakeCommand(IntakeSubsystem intake) {
    INTAKE_SUBSYSTEM = intake;
    addRequirements(intake);
    setName("Intake (Command)");
  }

  @Override
  public void initialize() {
    log(INTAKE_SUBSYSTEM, "intialzied", INFO);
    if (INTAKE_SUBSYSTEM.ballCount == 2) {
      System.out.println("There already are 2 balls");
      isFinished = true;
    } else {
      readyToIntake = true;
    }
  }

  @Override
  public void execute() {
    System.out.println("Ball count: " + INTAKE_SUBSYSTEM.ballCount);
    System.out.println("Ready To intake: " + readyToIntake);
    log(this, "Top Line Break Broken: " + INTAKE_SUBSYSTEM.topLineBroken, INFO);
    log(this, "Bottom Line Break Broken: " + INTAKE_SUBSYSTEM.bottomLineBroken, INFO);
    if (readyToIntake) {
      INTAKE_SUBSYSTEM.INTAKE.set(0.60);
      INTAKE_SUBSYSTEM.beltSpark.set(-1.0);
      if (INTAKE_SUBSYSTEM.bottomLineBroken) INTAKE_SUBSYSTEM.ballCount++;
      if (INTAKE_SUBSYSTEM.topLineBroken) {
        isFinished = true;
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    INTAKE_SUBSYSTEM.INTAKE.set(0.0);
    INTAKE_SUBSYSTEM.beltSpark.set(0.0);
    log(INTAKE_SUBSYSTEM, "Done", INFO);
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
