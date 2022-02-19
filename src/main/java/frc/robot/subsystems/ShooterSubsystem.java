package frc.robot.subsystems;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  public final Spark SHOOTER = new Spark(SHOOTER_PORT);

  public ShooterSubsystem() {
    setName("Shooter");
  }

  // @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // @Override
  public void simulationPeriodic() {
    // This method w
  }
}
