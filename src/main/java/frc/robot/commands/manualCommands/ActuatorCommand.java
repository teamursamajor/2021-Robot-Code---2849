package frc.robot.commands.manualCommands;

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
public class ActuatorCommand extends CommandBase {

  private boolean opening;
  private boolean isFinished = false;
  private final ClimbSubsystem CLIMB_SUBSYSTEM;

  public ActuatorCommand(ClimbSubsystem subsystem, boolean opening) {
    CLIMB_SUBSYSTEM = subsystem;
    this.opening = opening;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    log(CLIMB_SUBSYSTEM, "intialzied", INFO);
    if (opening) CLIMB_SUBSYSTEM.setActuatorPosition(true);
    else CLIMB_SUBSYSTEM.setActuatorPosition(false);
  }

  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
