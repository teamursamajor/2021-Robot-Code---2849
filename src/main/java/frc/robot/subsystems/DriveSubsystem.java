// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  final Spark frontRight = new Spark(Constants.FrontRightDrivePort);
  final Spark frontLeft = new Spark(Constants.FrontLeftDrivePort);
  final Spark backRight = new Spark(Constants.BackRightDrivePort);
  final Spark backLeft = new Spark(Constants.BackLeftDrivePort);

  /** Creates a new ExampleSubsystem. */
  public DriveSubsystem() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
