// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  public final Spark FRONT_RIGHT_DRIVE = new Spark(FRONT_RIGHT_DRIVE_PORT);
  public final Spark FRONT_LEFT_DRIVE = new Spark(FRONT_LEFT_DRIVE_PORT);
  public final Spark BACK_RIGHT_DRIVE = new Spark(BACK_RIGHT_DRIVE_PORT);
  public final Spark BACK_LEFT_DRIVE = new Spark(BACK_LEFT_DRIVE_PORT);

  /** Creates a new ExampleSubsystem. */
  public DriveSubsystem() {
    setName("Drive");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void setLeftPower(final double power) {
    BACK_LEFT_DRIVE.set(-power);
    FRONT_LEFT_DRIVE.set(-power);
    log(this, "left speed: " + power, INFO);
  }

  public void setRightPower(final double power) {
    BACK_RIGHT_DRIVE.set(power);
    FRONT_RIGHT_DRIVE.set(power);
    log(this, "right speed: " + power, INFO);
  }
}
