package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ColorRGB;

public class ColorSubsystem extends SubsystemBase {

  public final ColorSensorV3 COLOR_SENSOR = new ColorSensorV3(I2C_PORT);
  public final Color BALL_RED = new Color(255, 0, 0);
  public final Color BALL_BLUE = new Color(0, 0, 255);

  public ColorSubsystem() {
    setName("Intake");
  }

  @Override
  public void periodic() {
    ColorRGB c = new ColorRGB(COLOR_SENSOR.getColor());
    LOGGER.log(this, c.toString(), INFO);
  }

  @Override
  public void simulationPeriodic() {}
}