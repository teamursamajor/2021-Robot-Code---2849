package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  private final Spark LAUNCHER = new Spark(5);

  public ShooterSubsystem() {}

  // @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // @Override
  public void simulationPeriodic() {
    // This method w
  }
}
