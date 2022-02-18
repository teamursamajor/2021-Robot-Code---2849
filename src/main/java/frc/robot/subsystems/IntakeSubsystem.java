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
  public final ColorRGB RED_MIN = new ColorRGB(78, 92, 34);
  public final ColorRGB RED_MAX = new ColorRGB(144, 121, 55);
  public final ColorRGB BLUE_MIN = new ColorRGB(37, 98, 71);
  public final ColorRGB BLUE_MAX = new ColorRGB(64, 119, 118);

  public IntakeSubsystem() {
    setName("Intake");
  }

  @Override
  public void periodic() {
    ColorRGB c = new ColorRGB(COLOR_SENSOR.getColor());
    log(this, checkColor(c), INFO);
   // log(this, COLOR_SENSOR.getColor().toString(), INFO);
  }

  @Override
  public void simulationPeriodic() {}

  public String checkColor(ColorRGB c) {
    if ((c.RED <= RED_MAX.RED && c.RED >= RED_MIN.RED) &&
        (c.GREEN <= RED_MAX.GREEN && c.GREEN >= RED_MIN.GREEN) &&
        (c.BLUE <= RED_MAX.BLUE && c.BLUE >= RED_MIN.BLUE)) {
      return "red";
    } else if ((c.RED <= BLUE_MAX.RED && c.RED >= BLUE_MIN.RED) &&
              (c.GREEN <= BLUE_MAX.GREEN && c.GREEN >= BLUE_MIN.GREEN) &&
              (c.BLUE <= BLUE_MAX.BLUE && c.BLUE >= BLUE_MIN.BLUE)) {
      return "blue";
    } else {
      return "neither";
    }
  }
}
