package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
  // private final ClimbSubsystem m_ClimbSubsystem;
  public Spark climber = new Spark(SPARK_CLIMB_PORT);
  public TalonFX climbOne = new TalonFX(FALCON_CLIMB1_PORT);
  public TalonFX climbTwo = new TalonFX(FALCON_CLIMB2_PORT);
  

  // reset talon encoder
  public ClimbSubsystem() {
    climbOne.configFactoryDefault();
    climbOne.setNeutralMode(NeutralMode.Brake);
    climbTwo.configFactoryDefault();
    climbTwo.setNeutralMode(NeutralMode.Brake);
  }

  // @Override
  public void periodic() {}

  // @Override
  public void SimulationPeriodic() {}

  public void moterMove(double speed) {
    climbOne.set(TalonFXControlMode.PercentOutput, speed);
    climbOne.set(TalonFXControlMode.PercentOutput, speed);
  }
}
