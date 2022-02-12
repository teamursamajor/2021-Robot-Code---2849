// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.XBOXCONTROLLER;

/** An example command that uses an example subsystem. */
public class DriveCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
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
    leftStickY = XBOXCONTROLLER.getRawAxis(1);
    rightStickX = -XBOXCONTROLLER.getRawAxis(4);
    System.out.println("Left Stick: " + leftStickY);
    System.out.println("Right Stick: " + rightStickX);
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

    setLeftPower(leftSpeed);
    setRightPower(rightSpeed);
    // System.out.println("it works 2");
  }

  public void setLeftPower(final double power) {
    DRIVE_SUBSYSTEM.BACK_LEFT_DRIVE.set(-power);
    DRIVE_SUBSYSTEM.FRONT_LEFT_DRIVE.set(-power);
  }

  public void setRightPower(final double power) {
    DRIVE_SUBSYSTEM.BACK_RIGHT_DRIVE.set(power);
    DRIVE_SUBSYSTEM.FRONT_RIGHT_DRIVE.set(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    setLeftPower(0);
    setRightPower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
