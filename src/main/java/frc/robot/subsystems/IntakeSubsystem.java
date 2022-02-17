package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ColorRGB;

public class IntakeSubsystem extends SubsystemBase {
  public final Spark INTAKE = new Spark(INTAKE_PORT);

  public final ColorSensorV3 COLOR_SENSOR = new ColorSensorV3(I2C_PORT);
  public final ColorRGB BALL_RED = new ColorRGB(new Color(255, 0, 0));
  public final ColorRGB BALL_BLUE = new ColorRGB(new Color(0, 0, 255));

  public IntakeSubsystem() {
    setName("Intake");
  }

  @Override
  public void periodic() {
    ColorRGB c = new ColorRGB(COLOR_SENSOR.getColor());
    log(this, c.toString(), INFO);
   // log(this, COLOR_SENSOR.getColor().toString(), INFO);
  }

  @Override
  public void simulationPeriodic() {}
}
