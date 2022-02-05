package frc.robot.subsystems;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  public final Spark INTAKE = new Spark(INTAKE_PORT);

  public IntakeSubsystem() {
    setName("Intake");
  }

  @Override
  public void periodic() {}

  @Override
  public void simulationPeriodic() {}
}
