package frc.robot.commands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;

// import com.ctre.phoenix.motorcontrol.*;
// import com.ctre.phoenix.motorcontrol.can.*;
// import edu.wpi.first.wpilibj.*;

/**
 * Going to add a comment when sensors are recieved to allow for the driver to know if: 1.) an arm
 * is on the robot (left arm and right arm) 2.) both arms are on the robot 3.) the robot is ready to
 * move
 *
 * <p>Will say on Driver Station "Ready to go."
 */
public class ClimbCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private int extendedTickCount = -110000;
  private int retractedTickCount = -50000;
  private double extendingFalconSpeed = 0.5;
  private double retractingFalconSpeed = 0.2;

  private boolean raisingArm;
  private boolean actuatorReady = false;
  private boolean isFinished = false;
  private int actuatorTickCount = 0;
  private int actuatorTickGood = 250;
  private final ClimbSubsystem CLIMB_SUBSYSTEM;

  public ClimbCommand(ClimbSubsystem subsystem, boolean raisingArm) {
    CLIMB_SUBSYSTEM = subsystem;
    this.raisingArm = raisingArm;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    log(CLIMB_SUBSYSTEM, "intialzied", INFO);

    if (raisingArm) CLIMB_SUBSYSTEM.setActuatorPosition(true);
  }

  public void execute() {

    if (actuatorReady == false) {
      if (actuatorTickCount != actuatorTickGood && raisingArm) actuatorTickCount++;
      else {
        if (raisingArm) CLIMB_SUBSYSTEM.setFalconPower(-extendingFalconSpeed);
        else CLIMB_SUBSYSTEM.setFalconPower(retractingFalconSpeed);
        actuatorReady = true;
      }
    }

    if (actuatorReady) {
      if (raisingArm) {
        if (CLIMB_SUBSYSTEM.avgCurrentEncoderTicks <= extendedTickCount) isFinished = true;
      } else {
        if (CLIMB_SUBSYSTEM.avgCurrentEncoderTicks >= retractedTickCount) isFinished = true;
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    CLIMB_SUBSYSTEM.setFalconPower(0);
    if (raisingArm == false) CLIMB_SUBSYSTEM.setActuatorPosition(false);
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
