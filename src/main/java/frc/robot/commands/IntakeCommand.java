package frc.robot.commands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ColorRGB;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {

  private final IntakeSubsystem INTAKE_SUBSYSTEM;
  private final String TEAM_COLOR = "red";

  public boolean isFinished;

  public boolean readyToIntake;

  public IntakeCommand(IntakeSubsystem intake) {
    INTAKE_SUBSYSTEM = intake;
    addRequirements(intake);
    setName("Intake (Command)");
  }

  @Override
  public void initialize() {
    log(INTAKE_SUBSYSTEM, "intialzied", INFO);
    isFinished = false;
    readyToIntake = false;
  }

  @Override
  public void execute() {
    if (readyToIntake == false) {
      if (INTAKE_SUBSYSTEM.ballCount == 2) {
        System.out.println("There already are 2 balls");
        isFinished = true;
      } 
      else{
        readyToIntake = true;
      }
    }

    if (readyToIntake == true) {
      if (INTAKE_SUBSYSTEM
          .checkColor(new ColorRGB(INTAKE_SUBSYSTEM.COLOR_SENSOR.getColor()))
          .equals(TEAM_COLOR)) {
        INTAKE_SUBSYSTEM.INTAKE.set(1.0);
      }
      if(INTAKE_SUBSYSTEM.topLineBroken == true && INTAKE_SUBSYSTEM.ballCount == 0){
          INTAKE_SUBSYSTEM.ballCount++;
          isFinished = true;
      }
      else if (INTAKE_SUBSYSTEM.bottomLineBroken == true && INTAKE_SUBSYSTEM.ballCount == 1) {
          INTAKE_SUBSYSTEM.ballCount++;
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
