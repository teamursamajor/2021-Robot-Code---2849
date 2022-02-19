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
<<<<<<< HEAD:src/main/java/frc/robot/commands/ClimbCommand.Java
=======

/**
 * Going to add a comment when sensors are recieved to allow for the driver to know if: 1.) an arm
 * is on the robot (left arm and right arm) 2.) both arms are on the robot 3.) the robot is ready to
 * move
 *
 * <p>Will say on Driver Station "Ready to go."
 */
>>>>>>> cf5a06fe2d98a9dcc01a4696c6fc6547c487896e:src/main/java/frc/robot/commands/ClimbCommand.java
public class ClimbCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private static int num = 0;

  private int count;
  private int falconMaxSensor = 7;
  private int falconNinSensor = 0;
  boolean touchRight;
  boolean touchLeft;
  private boolean isExtended;
  private final ClimbSubsystem CLIMB_SUBSYSTEM;

  public ClimbCommand(ClimbSubsystem subsystem) {
    System.out.println("construct");
    CLIMB_SUBSYSTEM = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    touchRight = false;
    touchLeft = false;
    isExtended = false;
    log(CLIMB_SUBSYSTEM, "intialzied", INFO);
  }

  public void execute() {
    log(CLIMB_SUBSYSTEM, "Execute", INFO);
  }

<<<<<<< HEAD:src/main/java/frc/robot/commands/ClimbCommand.Java
    }
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Done");
  }

  @Override
=======
  @Override
  public void end(boolean interrupted) {
    System.out.println("Done");
  }

  @Override
>>>>>>> cf5a06fe2d98a9dcc01a4696c6fc6547c487896e:src/main/java/frc/robot/commands/ClimbCommand.java
  public boolean isFinished() {
    return false;
  }
}
