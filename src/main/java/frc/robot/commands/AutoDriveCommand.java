// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

/** An example command that uses an example subsystem. */
public class AutoDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem DRIVE_SUBSYSTEM;

  private Double driveDistance;
  private boolean finished = false;

  public AutoDriveCommand(DriveSubsystem subsystem, Double driveDistance) {
    DRIVE_SUBSYSTEM = subsystem;
    addRequirements(subsystem);
    this.driveDistance = driveDistance;
    setName("Auto Drive (Command)");
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    finished = true;

    // REMEMBER TO SET DISTANCE IN "initialize()"
    // double temporarySpeedVariable = 1.0;
    // double temporarySubtractValue = 1.0 / driveDistance;
    // while (temporarySpeedVariable != 0) {
    //   DRIVE_SUBSYSTEM.setLeftPower(temporarySpeedVariable);
    //   DRIVE_SUBSYSTEM.setRightPower(temporarySpeedVariable);
    //   temporarySpeedVariable = temporarySpeedVariable - temporarySubtractValue;
    //   driveDistance--;
    // }
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
