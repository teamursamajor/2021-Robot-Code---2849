package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
//import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {

  double minPercentage = 0.18;
  double maxPercentage = 0.50;
  double minPos = -1.0 + (minPercentage * 2.0);
  double maxPos = -1.0 + (maxPercentage * 2.0);
  boolean actuatorExtended = false;
  public double avgCurrentEncoderTicks = 0;

  // private final ClimbSubsystem m_ClimbSubsystem;
  public Spark climber = new Spark(SPARK_CLIMB_PORT);
  public TalonFX climbOne = new TalonFX(FALCON_CLIMB1_PORT);
  public TalonFX climbTwo = new TalonFX(FALCON_CLIMB2_PORT);

  public Servo climbActuator = new Servo(CLIMB_ACTUATOR_PORT);

  public double desiredOpenActuatorPos = .50;
  public double desiredClosedActuatorPos = .18;

  // reset talon encoder
  public ClimbSubsystem() {
    climbOne.configFactoryDefault();
    climbTwo.configFactoryDefault();
    climbActuator.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
    climbOne.setNeutralMode(NeutralMode.Brake);
    climbTwo.setNeutralMode(NeutralMode.Brake);
    // setActuatorPosition(true);
  }

  // @Override
  public void periodic() {
    avgCurrentEncoderTicks =
        (climbOne.getSelectedSensorPosition() + climbTwo.getSelectedSensorPosition()) / 2.0;
    // System.out.println("Average Encoder Ticks: " + avgCurrentEncoderTicks);
  }

  // @Override
  public void SimulationPeriodic() {}

  public void setFalconPower(double speed) {
    climbOne.set(TalonFXControlMode.PercentOutput, speed);
    climbOne.set(TalonFXControlMode.PercentOutput, speed);
  }

  public void setActuatorPosition(boolean setToOpen) {
    if (setToOpen) climbActuator.setSpeed(maxPos);
    else climbActuator.setSpeed(minPos);
  }
}
