package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
// import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {

  double minPercentage = 0.18;
  double maxPercentage = 0.50;
  double minPos = -1.0 + (minPercentage * 2.0);
  double maxPos = -1.0 + (maxPercentage * 2.0);
  public boolean actuatorOpen = false;
  public double avgCurrentEncoderTicks = 0;

  public double desiredSpeed = 12288;


  // private final ClimbSubsystem m_ClimbSubsystem;
  public Spark climber = new Spark(SPARK_CLIMB_PORT);
  public TalonFX climbOne = new TalonFX(FALCON_CLIMB1_PORT);
  public TalonFX climbTwo = new TalonFX(FALCON_CLIMB2_PORT);

  public Servo climbActuator = new Servo(CLIMB_ACTUATOR_PORT);

  public double desiredOpenActuatorPos = .50;
  public double desiredClosedActuatorPos = .18;

  private double climbOneInitialTicks, climbTwoInitialTicks;

  // reset talon encoder
  public ClimbSubsystem() {
    climbOne.configFactoryDefault();
    climbTwo.configFactoryDefault();
    climbOneInitialTicks = climbOne.getSelectedSensorPosition();
    climbTwoInitialTicks = climbTwo.getSelectedSensorPosition();
    climbActuator.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
    climbOne.setNeutralMode(NeutralMode.Brake);
    climbTwo.setNeutralMode(NeutralMode.Brake);


    climbOne.set(TalonFXControlMode.Velocity, -2000);
  }

  // @Override
  public void periodic() {
    avgCurrentEncoderTicks =
        (climbOne.getSelectedSensorPosition()
                - climbOneInitialTicks
                + climbTwo.getSelectedSensorPosition()
                - climbTwoInitialTicks)
            / 2.0;
    //System.out.println("Average Encoder Ticks New: " + avgCurrentEncoderTicks);

  }

  // @Override
  public void SimulationPeriodic() {}

  public void setFalconPower(double speed) {
    climbOne.set(TalonFXControlMode.PercentOutput, speed);
    climbOne.set(TalonFXControlMode.PercentOutput, speed);
  }

  public void setActuatorPosition(boolean setToOpen) {
    if (setToOpen){
      climbActuator.setSpeed(maxPos);
      actuatorOpen = true;
      SmartDashboard.putBoolean("Actuator Open", true);
    } 
    else{
      climbActuator.setSpeed(minPos);
      actuatorOpen = false;
      SmartDashboard.putBoolean("Actuator Open", false);
    } 
  }
}
