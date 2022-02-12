package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  public TalonFX SHOOTER = new TalonFX(4);

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
