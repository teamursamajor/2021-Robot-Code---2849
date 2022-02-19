package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

  public final TalonFX SHOOTER = new TalonFX(SHOOTER_PORT);

  public ShooterSubsystem() {
    SHOOTER.configFactoryDefault();
    setName("Shooter");
  }

  // @Override
  public void periodic() {}

  // @Override
  public void simulationPeriodic() {
    // This method w

  }

  public void resetEncoder() {}
}
