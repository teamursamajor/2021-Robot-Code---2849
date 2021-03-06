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
  private boolean finished = false;
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
    // Set the autonomous distance here
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double leftSpeed, rightSpeed, leftStickY, rightStickX;
    leftStickY = XBOX_CONTROLLER.getRawAxis(1);
    rightStickX = -XBOX_CONTROLLER.getRawAxis(4);
    // log(DRIVE_SUBSYSTEM.getName(), "Left Stick: " + leftStickY);
    // log(DRIVE_SUBSYSTEM.getName(), "Right Stick: " + rightStickX);
    leftSpeed = leftStickY + rightStickX;
    rightSpeed = leftStickY - rightStickX;
    //log(DRIVE_SUBSYSTEM, "Initial Left Speed: " + leftSpeed, INFO);
    //log(DRIVE_SUBSYSTEM, "Initial Right Speed: " + rightSpeed, INFO);
    double max = Math.max(leftSpeed, rightSpeed); // the greater of the two values
    double min = Math.min(leftSpeed, rightSpeed); // the lesser of the two values
    //log(DRIVE_SUBSYSTEM, "max" + max, INFO);
    //log(DRIVE_SUBSYSTEM, "min" + min, INFO);

    if (max > 1) {
      leftSpeed /= max;
      rightSpeed /= max;
    } else if (min < -1) {
      leftSpeed /= -min;
      rightSpeed /= -min;
    }

    setLeftPower(leftSpeed);
    setRightPower(rightSpeed);
    // System.out.println("it works 2");
  }

  public void setLeftPower(final double power) {
    DRIVE_SUBSYSTEM.sparkBackLeftDrive.set(-power);
    DRIVE_SUBSYSTEM.sparkFrontLeftDrive.set(-power);
    // log(DRIVE_SUBSYSTEM.getName(), "left speed: " + power);
  }

  public void setRightPower(final double power) {
    DRIVE_SUBSYSTEM.sparkFrontRightDrive.set(power);
    DRIVE_SUBSYSTEM.sparkFrontLeftDrive.set(power);
    // log(DRIVE_SUBSYSTEM.getName(), "right speed: " + power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DRIVE_SUBSYSTEM.setPower(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
