// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  public final Spark FRONT_RIGHT_DRIVE = new Spark(FRONT_RIGHT_DRIVE_PORT);
  public final Spark FRONT_LEFT_DRIVE = new Spark(FRONT_LEFT_DRIVE_PORT);
  public final Spark BACK_RIGHT_DRIVE = new Spark(BACK_RIGHT_DRIVE_PORT);
  public final Spark BACK_LEFT_DRIVE = new Spark(BACK_LEFT_DRIVE_PORT);
  // R.I.P. Sparks :(
  public VictorSPX FRONT_RIGHT_DRIVE1 = new VictorSPX(FRONT_RIGHT_DRIVE_PORT);
  public VictorSPX FRONT_LEFT_DRIVE1 = new VictorSPX(FRONT_LEFT_DRIVE_PORT);
  public VictorSPX BACK_RIGHT_DRIVE1 = new VictorSPX(BACK_RIGHT_DRIVE_PORT);
  public VictorSPX BACK_LEFT_DRIVE1 = new VictorSPX(BACK_LEFT_DRIVE_PORT);

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
    BACK_LEFT_DRIVE1.set(VictorSPXControlMode.PercentOutput, -power);
    FRONT_LEFT_DRIVE1.set(VictorSPXControlMode.PercentOutput, -power);
    
    log(this, "final left speed: " + power, INFO);
  }

  public void setRightPower(final double power) {
    BACK_RIGHT_DRIVE1.set(VictorSPXControlMode.PercentOutput, power);
    FRONT_RIGHT_DRIVE1.set(VictorSPXControlMode.PercentOutput, power);
    log(this, "final right speed: " + power, INFO);
  }
}
