package frc.robot.subsystems;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class BeltSubsystem extends SubsystemBase {
  public DigitalInput lineSensor = new DigitalInput(LINE_BREAK);
  public boolean lineBroken;
  public Spark beltSpark = new Spark(BELT_PORT);

  public void periodic() {
    //lineBroken = lineSensor.get();
  }

  public void simulationPeriodic() {}

  public void balltoTopOfBelt() {
    if (lineBroken = false) {
      while (lineBroken == false) {
        beltSpark.set(.25);
      }
      beltSpark.set(0.0);
    }
  }

  public void ballToShooter() {

    if (lineBroken == true) {
      while (lineBroken == true) {
        beltSpark.set(.25);
        new WaitCommand(.5).schedule();
      }
      beltSpark.set(0.0);
    }
  }
}
