package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

  public final TalonFX SHOOTER = new TalonFX(SHOOTER_PORT);

  /*
  */
  public ShooterSubsystem() {
    SHOOTER.configFactoryDefault();
    SHOOTER.setNeutralMode(NeutralMode.Coast);
    SHOOTER.config_kP(0, 0.5);
    SHOOTER.config_kD(0, 0.1);
    SHOOTER.config_kF(0, 0.059);
    SHOOTER.config_kI(0, 0);
    setName("Shooter");
  }

  // @Override
  public void periodic() {}

  // @Override
  public void simulationPeriodic() {
    // This method w

  }

  public void resetEncoder() {
    SHOOTER.configFactoryDefault();
  }
}
