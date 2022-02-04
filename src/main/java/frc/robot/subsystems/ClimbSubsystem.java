package frc.robot.subsystems;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
  // private final ClimbSubsystem m_ClimbSubsystem;
  public final Spark CLIMB = new Spark(CLIMB_PORT);

  public ClimbSubsystem() {
    setName("Climb");
  }

  // @Override
  public void periodic() {}

  // @Override
  public void SimulationPeriodic() {}
}
