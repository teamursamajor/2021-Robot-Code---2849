package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private int extendedTickCount = 4096; // Fix

  private int retractedTickCount = 0;
  private double falconSpeed = 0.5;
  private boolean raisingArm;
  private boolean actuatorReady = false;
  private boolean isFinished = false;
  private final ClimbSubsystem CLIMB_SUBSYSTEM;

  public ClimbCommand(ClimbSubsystem subsystem, boolean raisingArm) {
    CLIMB_SUBSYSTEM = subsystem;
    this.raisingArm = raisingArm;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    log(CLIMB_SUBSYSTEM, "intialzied", INFO);
    CLIMB_SUBSYSTEM.setActuatorPosition(true);
  }

  public void execute() {

    if (CLIMB_SUBSYSTEM.climbActuator.getPosition() == CLIMB_SUBSYSTEM.desiredOpenActuatorPos) {
      actuatorReady = true;
    }

    if (actuatorReady) {
      if (raisingArm) {
        if (CLIMB_SUBSYSTEM.avgCurrentEncoderTicks >= extendedTickCount) isFinished = true;
        else CLIMB_SUBSYSTEM.setFalconPower(falconSpeed);
      } else {
        if (CLIMB_SUBSYSTEM.avgCurrentEncoderTicks <= retractedTickCount) isFinished = true;
        else CLIMB_SUBSYSTEM.setFalconPower(-falconSpeed);
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    CLIMB_SUBSYSTEM.setFalconPower(0);
    CLIMB_SUBSYSTEM.setActuatorPosition(false);
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
