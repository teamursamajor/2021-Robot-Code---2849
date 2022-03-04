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
public class ManualClimbCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private int extendedTickCount = 4096; // Fix

  private int retractedTickCount = 0;
  private double falconSpeed = 0.2;
  private boolean raisingArm;
  private boolean actuatorReady = false;
  private boolean isFinished = false;
  private int actuatorTickCount = 0;
  private int actuatorTickGood = 250;
  private final ClimbSubsystem CLIMB_SUBSYSTEM;

  public ManualClimbCommand(ClimbSubsystem subsystem, boolean raisingArm) {
    CLIMB_SUBSYSTEM = subsystem;
    this.raisingArm = raisingArm;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    log(CLIMB_SUBSYSTEM, "intialzied", INFO);

    if (raisingArm) CLIMB_SUBSYSTEM.setFalconPower(-falconSpeed);
      else CLIMB_SUBSYSTEM.setFalconPower(falconSpeed);
    
  }

  public void execute() {

    /*
    
    if(actuatorTickCount != actuatorTickGood && raisingArm == true)
      actuatorTickCount++;
    else{
      if (raisingArm) CLIMB_SUBSYSTEM.setFalconPower(-falconSpeed);
      else CLIMB_SUBSYSTEM.setFalconPower(falconSpeed);
    }
    */
    
  }

  @Override
  public void end(boolean interrupted) {
    CLIMB_SUBSYSTEM.setFalconPower(0);
    /*
    if(raisingArm == false){
      CLIMB_SUBSYSTEM.setActuatorPosition(false);
      System.out.println("Closed Actuator");
    }
    */
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
