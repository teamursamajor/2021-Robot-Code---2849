package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
  // private final ClimbSubsystem m_ClimbSubsystem;
  // public Spark climber = new Spark(3);
  public TalonFX climbOne = new TalonFX(3);
  public TalonFX climbTwo = new TalonFX(0);

  public ClimbSubsystem() {
    setName("Climb");
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
