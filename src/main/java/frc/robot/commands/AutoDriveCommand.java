// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

// import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

/** An example command that uses an example subsystem. */
public class AutoDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem DRIVE_SUBSYSTEM;

  private boolean finished = false;

  public AutoDriveCommand(DriveSubsystem subsystem) {
    DRIVE_SUBSYSTEM = subsystem;
    addRequirements(subsystem);
    setName("Auto Drive (Command)");
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Auto drive start");
    DRIVE_SUBSYSTEM.setPower(.45, .45);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    
    // Pseudocode below for Falcon Drive System (if using falcons to drive)
    // SET [Distance] TO AMOUNT OF TICKS
    // double temporarySpeedVariable = 1;
    // double distanceTravelledTotal = 0;
    // double distanceTravelledOnce = [Distance];
    // double originalDistance = [Distance];

    // while (distanceTravelledTotal < OriginalDistance) {
    //   DRIVE_SUBSYSTEM.setPower(temporarySpeedVariable, temporarySpeedVariable);
    //   distanceTravelledOnce = [GET THE TICKS FROM THE FALCON];
    //   distanceTravelledTotal = distanceTravelledTotal + (distanceTravelledOnce /
    // originalDistance);
    //   temporarySpeedVariable = 1 - distanceTravelledTotal;
    // }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("auto drive ends");
    DRIVE_SUBSYSTEM.setPower(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
