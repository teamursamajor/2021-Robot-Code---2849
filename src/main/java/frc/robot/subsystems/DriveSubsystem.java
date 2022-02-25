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
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {

  public enum MotorType {
    SPARKS,
    FALCONS,
    VICTORS
  }

  // change this to set which motors are used
  public MotorType motorType = MotorType.SPARKS;

  public Spark sparkFrontRightDrive;
  public Spark sparkFrontLeftDrive;
  public Spark sparkBackRightDrive;
  public Spark sparkBackLeftDrive;

  public TalonFX falconFrontRightDrive;
  public TalonFX falconFrontLeftDrive;
  public TalonFX falconBackRightDrive;
  public TalonFX falconBackLeftDrive;

  public VictorSPX victorFrontRightDrive;
  public VictorSPX victorFrontLeftDrive;
  public VictorSPX victorBackRightDrive;
  public VictorSPX victorBackLeftDrive;

  public DriveSubsystem() {
    setName("Drive");
    switch (motorType) {
      case SPARKS:
        sparkFrontRightDrive = new Spark(SPARK_FRONT_RIGHT_DRIVE_PORT);
        sparkFrontLeftDrive = new Spark(SPARK_FRONT_LEFT_DRIVE_PORT);
        sparkBackRightDrive = new Spark(SPARK_BACK_RIGHT_DRIVE_PORT);
        sparkBackLeftDrive = new Spark(SPARK_BACK_LEFT_DRIVE_PORT);
        break;
      case FALCONS:
        falconFrontRightDrive = new TalonFX(FALCON_FRONT_RIGHT_DRIVE_PORT);
        falconFrontLeftDrive = new TalonFX(FALCON_FRONT_LEFT_DRIVE_PORT);
        falconBackRightDrive = new TalonFX(FALCON_BACK_RIGHT_DRIVE_PORT);
        falconBackLeftDrive = new TalonFX(FALCON_BACK_LEFT_DRIVE_PORT);
        break;
      case VICTORS:
        victorFrontRightDrive = new VictorSPX(VICTOR_FRONT_RIGHT_DRIVE_PORT);
        victorFrontLeftDrive = new VictorSPX(VICTOR_FRONT_LEFT_DRIVE_PORT);
        victorBackRightDrive = new VictorSPX(VICTOR_BACK_RIGHT_DRIVE_PORT);
        victorBackLeftDrive = new VictorSPX(VICTOR_BACK_LEFT_DRIVE_PORT);
        break;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void setPower(double leftSpeed, double rightSpeed) {
   
    switch (motorType) {
      case SPARKS:
        setLeftPowerSparks(leftSpeed);
        setRightPowerSparks(rightSpeed);
        break;
      case FALCONS:
        setLeftPowerFalcons(leftSpeed);
        setRightPowerFalcons(rightSpeed);
        break;
      case VICTORS:
        setLeftPowerVictors(leftSpeed);
        setRightPowerVictors(rightSpeed);
        break;
    }
  }

  public void setLeftPowerSparks(final double power) {
    sparkFrontLeftDrive.set(-power);
    sparkBackLeftDrive.set(-power);
    log(this, "left speed: " + power, INFO);
  }

  public void setRightPowerSparks(final double power) {
    sparkFrontRightDrive.set(power);
    sparkBackRightDrive.set(power);
    log(this, "right speed: " + power, INFO);
  }

  public void setLeftPowerFalcons(final double power) {
    falconFrontLeftDrive.set(TalonFXControlMode.PercentOutput, -power);
    falconBackLeftDrive.set(TalonFXControlMode.PercentOutput, -power);
    log(this, "left speed: " + power, INFO);
  }

  public void setRightPowerFalcons(final double power) {
    falconFrontRightDrive.set(TalonFXControlMode.PercentOutput, power);
    falconBackRightDrive.set(TalonFXControlMode.PercentOutput, power);
    log(this, "right speed: " + power, INFO);
  }

  public void setLeftPowerVictors(final double power) {
    victorFrontLeftDrive.set(VictorSPXControlMode.PercentOutput, -power);
    victorBackLeftDrive.set(VictorSPXControlMode.PercentOutput, -power);
    log(this, "left speed: " + power, INFO);
  }

  public void setRightPowerVictors(final double power) {
    victorFrontRightDrive.set(VictorSPXControlMode.PercentOutput, power);
    victorBackRightDrive.set(VictorSPXControlMode.PercentOutput, power);
    log(this, "right speed: " + power, INFO);
  }
}
