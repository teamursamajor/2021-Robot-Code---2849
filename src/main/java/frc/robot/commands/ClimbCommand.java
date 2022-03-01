package frc.robot.commands;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;

/**
 * Going to add a comment when sensors are recieved to allow for the driver to know if: 1.) an arm
 * is on the robot (left arm and right arm) 2.) both arms are on the robot 3.) the robot is ready to
 * move
 *
 * <p>Will say on Driver Station "Ready to go."
 */
public class ClimbCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private int extendedTickCount = 4096; // Fix

  private int retractedTickCount = 0;
  private double falconSpeed = 0.5;
  private boolean raisingArm;
  private boolean isFinished = false;
  private final ClimbSubsystem CLIMB_SUBSYSTEM;

  public ClimbCommand(ClimbSubsystem subsystem, boolean raisingArm) {
    System.out.println("construct");
    CLIMB_SUBSYSTEM = subsystem;
    this.raisingArm = raisingArm;
    addRequirements(subsystem);
  }

  public void raiseArm() {
    if (CLIMB_SUBSYSTEM.avgCurrentEncoderTicks >= extendedTickCount) {
      CLIMB_SUBSYSTEM.setFalconPower(0);
      isFinished = true;
    }
  }

  public void lowerArm() {
    if (CLIMB_SUBSYSTEM.avgCurrentEncoderTicks <= retractedTickCount) {
      CLIMB_SUBSYSTEM.setFalconPower(0);
      isFinished = true;
    }
  }

  @Override
  public void initialize() {
    log(CLIMB_SUBSYSTEM, "intialzied", INFO);
    if (raisingArm) {
      if (CLIMB_SUBSYSTEM.avgCurrentEncoderTicks >= extendedTickCount) isFinished = true;
      else CLIMB_SUBSYSTEM.setFalconPower(falconSpeed);
    } else {
      if (CLIMB_SUBSYSTEM.avgCurrentEncoderTicks <= retractedTickCount) isFinished = true;
      else CLIMB_SUBSYSTEM.setFalconPower(-falconSpeed);
    }
  }

  public void execute() {

    if (raisingArm) raiseArm();
    else lowerArm();
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Done");
  }

  @Override
  public boolean isFinished() {
    CLIMB_SUBSYSTEM.setFalconPower(0);
    return isFinished;
  }
}
