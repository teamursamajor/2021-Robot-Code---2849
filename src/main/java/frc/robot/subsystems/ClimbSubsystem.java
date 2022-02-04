package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
  // private final ClimbSubsystem m_ClimbSubsystem;
  public Spark climber = new Spark(3);

  public ClimbSubsystem() {}

  // @Override
  public void periodic() {}

  // @Override
  public void SimulationPeriodic() {}
}
