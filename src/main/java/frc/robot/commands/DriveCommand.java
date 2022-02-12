// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

/** An example command that uses an example subsystem. */
public class DriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem DRIVE_SUBSYSTEM;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveCommand(DriveSubsystem subsystem) {
    DRIVE_SUBSYSTEM = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);

    setName("Drive (Command)");
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("intialzied");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // System.out.println("it works");
    double leftSpeed, rightSpeed, leftStickY, rightStickX;
    leftStickY = XBOX_CONTROLLER.getRawAxis(1);
    rightStickX = -XBOX_CONTROLLER.getRawAxis(4);
    LOGGER.log(DRIVE_SUBSYSTEM, "Left Stick: " + leftStickY, INFO);
    LOGGER.log(DRIVE_SUBSYSTEM, "Right Stick: " + rightStickX, INFO);
    leftSpeed = leftStickY + rightStickX;
    rightSpeed = leftStickY - rightStickX;

    double max = Math.max(leftSpeed, rightSpeed); // the greater of the two values
    double min = Math.min(leftSpeed, rightSpeed); // the lesser of the two values

    if (max > 1) {
      leftSpeed /= max;
      rightSpeed /= max;
    } else if (min < -1) {
      leftSpeed /= -min;
      rightSpeed /= -min;
    }

    DRIVE_SUBSYSTEM.setLeftPower(leftSpeed);
    DRIVE_SUBSYSTEM.setRightPower(rightSpeed);
    // System.out.println("it works 2");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DRIVE_SUBSYSTEM.setLeftPower(0);
    DRIVE_SUBSYSTEM.setRightPower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
